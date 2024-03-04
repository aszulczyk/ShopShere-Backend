package com.shop.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.controllers.CartDTO;
import com.shop.controllers.ProductDTO;
import com.shop.model.CartEntity;
import com.shop.model.ProductEntity;
import com.shop.repositories.CartEntityRepository;
import com.shop.repositories.ProductsRepository;

@Service
public class CartService {

	@Autowired
	CartEntityRepository cartEntityRepository;

	@Autowired
	ProductsRepository productsRepository;
	
	public CartDTO createUserCart(int userId) {
		CartEntity cartEntity = CartEntity.builder()
				.userId(userId)
				.products(new ArrayList<ProductEntity>())
				.build();
		
		return CartDTO.cartEntityToCartDTO(cartEntityRepository.save(cartEntity));
	}

	public CartDTO getUserCart(int userId) {
		return CartDTO.cartEntityToCartDTO(cartEntityRepository.findByUserId(userId).orElse(new CartEntity()));
	}

	public CartDTO addProduct(int userId, ProductDTO productDTO) {
		CartEntity cartEntity = cartEntityRepository.findByUserId(userId).orElseThrow();
		List<ProductEntity> productEntities = productsRepository.findAllProductsByCartEntity(cartEntity.getId());
		boolean productExists = false;
		for (ProductEntity product : productEntities) {
			if (product.getProductId() == productDTO.getProductId()) {
				product.setCount(product.getCount() + productDTO.getCount());
				productExists = true;
				productsRepository.save(product);
			}
		}
		
		if (!productExists) {
			ProductEntity productEntity = ProductEntity.builder()
			.productId(productDTO.getProductId())
			.count(productDTO.getCount())
			.build();
			productEntities.add(productEntity);
			productsRepository.save(productEntity);
		}
		return CartDTO.cartEntityToCartDTO(cartEntity);
	}
	
	public CartDTO deleteProduct(int userId, int productId) {
		CartEntity cartEntity = cartEntityRepository.findByUserId(userId).orElseThrow();
		List<ProductEntity> productEntities = productsRepository.findAllProductsByCartEntity(cartEntity.getId());
		for (ProductEntity product : productEntities) {
			if (product.getProductId() == productId) {
				productsRepository.delete(product);
			}
		}
		return CartDTO.cartEntityToCartDTO(cartEntityRepository.findByUserId(userId).orElseThrow());
	}
	
	public CartDTO updateProduct(int userId, ProductDTO productDTO) {
		CartEntity cartEntity = cartEntityRepository.findByUserId(userId).orElseThrow();
		List<ProductEntity> productEntities = productsRepository.findAllProductsByCartEntity(cartEntity.getId());
		boolean productExists = false;
		for (ProductEntity product : productEntities) {
			if (product.getProductId() == productDTO.getProductId()) {
				product.setCount(productDTO.getCount());
				productExists = true;
				productsRepository.save(product);
			}
		}
		
		if (!productExists) {
			ProductEntity productEntity = ProductEntity.builder()
			.productId(productDTO.getProductId())
			.count(productDTO.getCount())
			.build();
			productEntities.add(productEntity);
			productsRepository.save(productEntity);
		}
		return CartDTO.cartEntityToCartDTO(cartEntityRepository.findByUserId(userId).orElseThrow());
	}
	
	public CartDTO refreshCart(int userId) {
		CartEntity cartEntity = cartEntityRepository.findByUserId(userId).orElseThrow();
		List<ProductEntity> productEntities = productsRepository.findAllProductsByCartEntity(cartEntity.getId());
		for (ProductEntity product : productEntities) {
			productsRepository.delete(product);
		}
		return CartDTO.cartEntityToCartDTO(cartEntityRepository.findByUserId(userId).orElseThrow());
	}


}
