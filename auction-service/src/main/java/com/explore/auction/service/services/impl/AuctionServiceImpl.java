package com.explore.auction.service.services.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.explore.auction.service.entity.Auction;
import com.explore.auction.service.entity.Bid;
import com.explore.auction.service.repository.AuctionRepository;
import com.explore.auction.service.repository.BidRepository;
import com.explore.auction.service.services.AuctionService;

@Service
public class AuctionServiceImpl implements AuctionService{
	
	@Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private BidRepository bidRepository;
    
    private final RestTemplate restTemplate = new RestTemplate();

    private final String USER_SERVICE_URL = "http://localhost:8081/users/validate";


	@Override
	public Auction createAuction(Auction auction, String sellerToken) {
		validateUserToken(sellerToken, "seller");

        auction.setSellerToken(sellerToken);
        return auctionRepository.save(auction);
	}

	@Override
	public List<Auction> getAllAuctions() {
		return auctionRepository.findAll()
				.stream()
				.filter(a -> "OPEN".equals(a.getStatus())).toList();
	}

	@Override
	public Bid placeBid(Long auctionId, Bid bid, String buyerToken) {
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

        return bidRepository.save(bid);
	}
	
	@Override
	public Bid endAuction(Long auctionId) {
        Optional<Auction> auctionOptional = auctionRepository.findById(auctionId);
        if (auctionOptional.isPresent()) {
            Auction auction = auctionOptional.get();
            auction.setStatus("CLOSED");
            auctionRepository.save(auction);
            
            return auction.getBids().stream()
                    .max((b1, b2) -> {
                        if (b1.getBidAmount() != b2.getBidAmount()) {
                            return Double.compare(b1.getBidAmount(), b2.getBidAmount());
                        }
                        return b1.getTimestamp().compareTo(b2.getTimestamp());
                    })
                    .orElseThrow(() -> new RuntimeException("No bids placed."));
        } else {
            throw new RuntimeException("Auction not found.");
        }
    }
	
	private void validateUserToken(String token, String userType) {
        try {
            String response = restTemplate.getForObject(
                    USER_SERVICE_URL + "?type=" + userType,
                    String.class,
                    token
            );
            if (!"Valid User".equals(response)) {
                throw new RuntimeException("Invalid user token");
            }
        } catch (Exception e) {
            throw new RuntimeException("User validation failed", e);
        }
    }

}
