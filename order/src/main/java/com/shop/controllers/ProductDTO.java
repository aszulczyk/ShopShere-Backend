package com.shop.controllers;

import java.util.List;

import com.shop.model.ProductEntity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class ProductDTO {
	
	private int productId;
	private int count;
	
	public static ProductDTO ProductEntityToProductDTO(ProductEntity productEntity) {
		return ProductDTO.builder()
				.productId(productEntity.getProductId())
				.count(productEntity.getCount())
				.build();
	}
	
	public static ProductEntity ProductDTOToProductEntity(ProductDTO productDTO) {
		return ProductEntity.builder()
				.productId(productDTO.getProductId())
				.count(productDTO.getCount())
				.build();
		
	}

}
