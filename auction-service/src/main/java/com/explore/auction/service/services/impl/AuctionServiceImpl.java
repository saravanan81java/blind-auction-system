package com.explore.auction.service.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.explore.auction.service.dto.AuctionDTO;
import com.explore.auction.service.entity.Auction;
import com.explore.auction.service.entity.Bid;
import com.explore.auction.service.repository.AuctionRepository;
import com.explore.auction.service.repository.BidRepository;
import com.explore.auction.service.services.AuctionService;

import auctionService.BidDTO;

@Service
public class AuctionServiceImpl implements AuctionService{
	
	@Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private BidRepository bidRepository;
    
    private final RestTemplate restTemplate = new RestTemplate();

    private final String USER_SERVICE_URL = "http://localhost:8083/users/validate";


	@Override
	public Auction createAuction(Auction auction, String sellerToken) {
		validateUserToken(sellerToken, "seller");

        auction.setSellerToken(sellerToken);
        return auctionRepository.save(auction);
	}

	@Override
	public List<AuctionDTO> getAllAuctions() {
		return  auctionRepository.findAll().stream().map(auct ->  new AuctionDTO(auct.getId(),
				auct.getProductDescription(),
				auct.getMinBidPrice(),
				auct.getSellerToken(),auct.getStatus())).collect(Collectors.toList());
	}

	@Override
	public BidDTO placeBid(Long auctionId, Bid bid, String buyerToken) {
		validateUserToken(buyerToken, "buyer");

        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new IllegalArgumentException("Auction not found"));

        if (!"OPEN".equals(auction.getStatus())) {
        	throw new IllegalArgumentException("Auction is closed");
        }
        
        if (bid.getBidAmount() < auction.getMinBidPrice()) {
            throw new IllegalArgumentException("Bid amount is below the minimum price");
        }

        bid.setBuyerToken(buyerToken);
        bid.setAuction(auction);
        BidDTO dto = convertBidDTO(bidRepository.save(bid));
        return dto;
	}
	
	private BidDTO convertBidDTO(Bid bid) {
		BidDTO dto = new BidDTO();
		if(bid!= null) {
			Auction auct = bid.getAuction();
			if(auct != null) {
				AuctionDTO aucDto = new AuctionDTO(auct.getId(),
					auct.getProductDescription(),
					auct.getMinBidPrice(),
					auct.getSellerToken(),auct.getStatus());
				dto.setAuction(aucDto);
			}
			dto.setBidAmount(bid.getBidAmount());
			dto.setBuyerToken(bid.getBuyerToken());
			dto.setId(bid.getId());
		}
		return dto;
	}

	@Override
	public BidDTO endAuction(Long auctionId) {
        Optional<Auction> auctionOptional = auctionRepository.findById(auctionId);
        if (auctionOptional.isPresent()) {
            Auction auction = auctionOptional.get();
            auction.setStatus("CLOSED");
            auctionRepository.save(auction);
            
            Bid bid = auction.getBids().stream()
                    .max((b1, b2) -> {
                        if (b1.getBidAmount() != b2.getBidAmount()) {
                            return Double.compare(b1.getBidAmount(), b2.getBidAmount());
                        }
                        return b1.getTimestamp().compareTo(b2.getTimestamp());
                    })
                    .orElseThrow(() -> new RuntimeException("No bids placed."));
            return convertBidDTO(bid);
            
        } else {
            throw new RuntimeException("Auction not found.");
        }
    }
	
	private void validateUserToken(String token, String userType) {
        try {
        	
        	HttpHeaders headers = new HttpHeaders();
        	headers.set("User-Token", token); // or "Buyer-Token" if required by your application
        	 
            // Create an HttpEntity with headers
            HttpEntity<String> entity = new HttpEntity<>(headers);

            
            ResponseEntity<String> response = restTemplate.exchange(
                    USER_SERVICE_URL + "?type=" + userType,
                    HttpMethod.POST,
                    entity,
                    String.class
            );
            if (!"Valid User".equals(response.getBody())) {
                throw new RuntimeException("Invalid user token");
            }
        } catch (Exception e) {
            throw new RuntimeException("User validation failed", e);
        }
    }

}
