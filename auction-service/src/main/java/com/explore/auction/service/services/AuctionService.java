package com.explore.auction.service.services;

import java.util.List;

import com.explore.auction.service.entity.Auction;
import com.explore.auction.service.entity.Bid;


public interface AuctionService {

	Auction createAuction(Auction auction, String sellerToken);

	List<Auction> getAllAuctions();

	Bid placeBid(Long auctionId, Bid bid, String buyerToken);

	Bid endAuction(Long auctionId);

}
