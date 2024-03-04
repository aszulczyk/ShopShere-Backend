 package com.shop.controllers;

import com.shop.model.ProductEntity;
import com.shop.model.Rating;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
	
	private int id;
	private String title;
	private double price;
	private String description;
	private String category;
	private String image;
	
	private Rating rating;
	
	public static ProductDTO productEntityToProductDTO(ProductEntity productEntity) {
		ProductDTO productDTO = ProductDTO.builder()
				.id(productEntity.getId())
				.title(productEntity.getTitle())
				.price(productEntity.getPrice())
				.description(productEntity.getDescription())
				.category(productEntity.getCategory())
				.image(productEntity.getImage())
				.rating(productEntity.getRating())
				.build();
		return productDTO;
	}
	
	public static ProductEntity productDTOToProductEntity(ProductDTO productDTO) {
		ProductEntity productEntity = ProductEntity.builder()
				.title(productDTO.getTitle())
				.price(productDTO.getPrice())
				.description(productDTO.getDescription())
				.category(productDTO.getCategory())
				.image(productDTO.getImage())
				.rating(productDTO.getRating())
				.build();
		return productEntity;
	}

}
