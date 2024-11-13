package com.explore.auction.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.explore.auction.service.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
