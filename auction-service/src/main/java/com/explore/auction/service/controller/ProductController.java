package com.explore.auction.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.explore.auction.service.entity.Product;
import com.explore.auction.service.services.ProductService;


@RestController
@RequestMapping("/products")
public class ProductController {

	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/ping")
    public String getPing() {
        return "Hello Product Service!!!";
    }
	
	@GetMapping("/list")
    public List<Product> listAuctions() {
        return productService.getAllProducts();
    }
}
