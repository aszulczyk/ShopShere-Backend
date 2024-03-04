package com.shop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.controllers.ProductDTO;
import com.shop.model.ProductEntity;
import com.shop.repositories.ProductRepository;

@Service
public class AdminProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	public ProductDTO addNewProduct(ProductDTO productDTO) {
		ProductEntity productEntity = ProductDTO.productDTOToProductEntity(productDTO);
		return ProductDTO.productEntityToProductDTO(productRepository.save(productEntity));
	}
	
	public ProductDTO updateProduct(ProductDTO productDTO, int productId) {
		ProductEntity productEntity = ProductDTO.productDTOToProductEntity(productDTO);
		productEntity.setId(productId);
		return ProductDTO.productEntityToProductDTO(productRepository.save(productEntity));
	}
	
	public boolean deleteProductById(int productId) {
		Optional<ProductEntity> ope = productRepository.findById(productId);
		if (ope.isEmpty()) return false;
		productRepository.delete(ope.get());
		return true;
	}

}
