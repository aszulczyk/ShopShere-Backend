package com.shop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.model.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
	
	List<ProductEntity> findByCategory(String category);

}
