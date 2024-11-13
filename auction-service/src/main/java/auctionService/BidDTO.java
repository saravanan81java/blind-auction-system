package auctionService;

import com.explore.auction.service.dto.AuctionDTO;


public class BidDTO {

	 private Long id;
		
	 private double bidAmount;
	    
	 private String buyerToken;
	    
	 AuctionDTO auction;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(double bidAmount) {
		this.bidAmount = bidAmount;
	}

	public String getBuyerToken() {
		return buyerToken;
	}

	public void setBuyerToken(String buyerToken) {
		this.buyerToken = buyerToken;
	}

	public AuctionDTO getAuction() {
		return auction;
	}

	public void setAuction(AuctionDTO auction) {
		this.auction = auction;
	}
	 
	 
}


