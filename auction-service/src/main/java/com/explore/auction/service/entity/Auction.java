package com.explore.auction.service.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "auction")
public class Auction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
    private String productDescription;
   
    /*
    @OneToOne(cascade = CascadeType.ALL)
    private Product product;
    */
    
    private double minBidPrice;
    
    private String sellerToken;
    private String status = "OPEN";  // Status can be "OPEN" or "CLOSED"

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Bid> bids;
    
    public Auction() {
		// TODO Auto-generated constructor stub
	}
    
    
    public Auction(String productDescription, double minBidPrice, String sellerToken) {
		this.productDescription = productDescription;
		this.minBidPrice = minBidPrice;
		this.sellerToken = sellerToken;
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

	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
    
}
