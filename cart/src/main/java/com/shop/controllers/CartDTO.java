package com.shop.controllers;

import java.util.List;
import java.util.Set;

import com.shop.model.CartEntity;
import com.shop.model.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartDTO {
	
	private int id;
	private int userId;
	private List<ProductDTO> products;
	
	public static CartEntity cartDTOToUserEntity(CartDTO userDTO) {
		List<ProductEntity> productEntities = userDTO.getProducts().stream().map(ProductDTO::ProductDTOToProductEntity).toList();
		return CartEntity.builder()
		.userId(userDTO.getUserId())
		.products(productEntities)
		.build();
	}
	
	public static CartDTO cartEntityToCartDTO(CartEntity cartEntity) {
		List<ProductDTO> ProductDTOs = cartEntity.getProducts().stream().map(ProductDTO::ProductEntityToProductDTO).toList();
		return CartDTO.builder()
				.id(cartEntity.getId())
				.userId(cartEntity.getUserId())
				.products(ProductDTOs)
				.build();
	}

}
