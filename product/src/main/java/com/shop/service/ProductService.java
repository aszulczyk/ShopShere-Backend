package com.shop.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.controllers.ProductDTO;
import com.shop.model.ProductEntity;
import com.shop.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	public List<ProductDTO> getAllProducts() {
		List<ProductEntity> productEntities = productRepository.findAll();
		return productEntities.stream().map(ProductDTO::productEntityToProductDTO).collect(Collectors.toList());	
	}
	
	public ProductDTO getProductByID(int productId) {
		Optional<ProductEntity> ope = productRepository.findById(productId);
		return ProductDTO.productEntityToProductDTO(ope.orElse(new ProductEntity()));
	}
	
	public List<ProductDTO> getProductsByCategory(String category) {
		List<ProductEntity> productEntities = productRepository.findByCategory(category);
		return productEntities.stream().map(ProductDTO::productEntityToProductDTO).collect(Collectors.toList());	
	}

}
