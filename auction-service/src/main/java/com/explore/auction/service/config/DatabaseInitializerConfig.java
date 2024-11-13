package com.explore.auction.service.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.explore.auction.service.entity.Auction;
import com.explore.auction.service.entity.Product;
import com.explore.auction.service.repository.AuctionRepository;
import com.explore.auction.service.repository.ProductRepository;

@Configuration
public class DatabaseInitializerConfig {

	@Bean
    CommandLineRunner initDatabase(ProductRepository productRepository, AuctionRepository auctionRepository) {
		return args -> {
			
			List<Product> listOfProducts = new ArrayList<>();
			listOfProducts.add(new Product("Product A"));
			listOfProducts.add(new Product("Product B"));
			listOfProducts.add(new Product("Product C"));
			
			productRepository.saveAll(listOfProducts); 
			
			auctionRepository.save(new Auction("Product A", 100.0, "seller-token-123"));
		};
	}
}
