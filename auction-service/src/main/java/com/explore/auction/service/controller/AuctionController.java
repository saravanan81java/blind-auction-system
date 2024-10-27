package com.explore.auction.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.explore.auction.service.entity.Auction;
import com.explore.auction.service.entity.Bid;
import com.explore.auction.service.services.AuctionService;

@RestController
@RequestMapping("/auctions")
public class AuctionController {
	
	@Autowired
	private AuctionService auctionService;

	@GetMapping("/ping")
	public String getPing() {
		return "Hello Auction Service!!!";
	}

	@PostMapping
    public ResponseEntity<Auction> createAuction(@RequestBody Auction auction, @RequestHeader("Seller-Token") String sellerToken) {
        return ResponseEntity.ok(auctionService.createAuction(auction, sellerToken));
    }
    
    @PostMapping("/{auctionId}/bid")
    public ResponseEntity<Bid> placeBid(@PathVariable Long auctionId, @RequestBody Bid bid, @RequestHeader("Buyer-Token") String buyerToken) {
        return ResponseEntity.ok(auctionService.placeBid(auctionId, bid, buyerToken));
    }

    @PostMapping("/{auctionId}/end")
    public ResponseEntity<Bid> endAuction(@PathVariable Long auctionId) {
        return ResponseEntity.ok(auctionService.endAuction(auctionId));
    }

    @GetMapping
    public List<Auction> listAuctions() {
        return auctionService.getAllAuctions();
    }

}
