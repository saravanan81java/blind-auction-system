package com.explore.auction.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.explore.auction.service.entity.Auction;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

}
