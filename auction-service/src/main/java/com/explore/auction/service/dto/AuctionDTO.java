package com.explore.auction.service.dto;

public class AuctionDTO {
	private Long id;
    private String productDescription;
    private double minBidPrice;
    private String sellerToken;
    private String status;
    
    public AuctionDTO(Long id, String productDescription, double minBidPrice, String sellerToken, String status) {
		this.id = id;
		this.productDescription = productDescription;
		this.minBidPrice = minBidPrice;
		this.sellerToken = sellerToken;
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public double getMinBidPrice() {
		return minBidPrice;
	}
	public void setMinBidPrice(double minBidPrice) {
		this.minBidPrice = minBidPrice;
	}
	public String getSellerToken() {
		return sellerToken;
	}
	public void setSellerToken(String sellerToken) {
		this.sellerToken = sellerToken;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
    
}
