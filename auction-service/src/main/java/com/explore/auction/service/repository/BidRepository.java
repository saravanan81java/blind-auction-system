package com.explore.auction.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.explore.auction.service.entity.Bid;

public interface BidRepository extends JpaRepository<Bid, Long> {
	//List<Bid> findByAuctionIdOrderByAmountDesc(Long auctionId);
}
