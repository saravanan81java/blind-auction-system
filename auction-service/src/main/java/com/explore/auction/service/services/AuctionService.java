package com.explore.auction.service.services;

import java.util.List;

import com.explore.auction.service.dto.AuctionDTO;
import com.explore.auction.service.entity.Auction;
import com.explore.auction.service.entity.Bid;

import auctionService.BidDTO;


public interface AuctionService {

	Auction createAuction(Auction auction, String sellerToken);

	List<AuctionDTO> getAllAuctions();

	BidDTO placeBid(Long auctionId, Bid bid, String buyerToken);

	BidDTO endAuction(Long auctionId);

}
