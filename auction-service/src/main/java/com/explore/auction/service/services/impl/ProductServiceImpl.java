package com.explore.auction.service.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.explore.auction.service.entity.Product;
import com.explore.auction.service.repository.ProductRepository;
import com.explore.auction.service.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	private ProductRepository productRepository;
	
	@Autowired  // Its optional @Autowired annotation default Spring will automatically detect and Constructor injection
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

}
