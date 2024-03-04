package com.shop.controllers;

import java.util.List;
import java.util.Set;

import com.shop.model.WishlistEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import com.shop.model.IntegerSetConverter;
             
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
public class WishlistDTO {
	

	private int id;
	private int userId;
	private Set<Integer> productIds;

	
	public static WishlistEntity wishlistDTOToWishlistEntity(WishlistDTO wishlistDTO) {
		return WishlistEntity.builder()
		.userId(wishlistDTO.getUserId())
		.productIds(wishlistDTO.getProductIds())
		.build();
	}
	
	public static WishlistDTO wishlistEntityToWishlistDTO(WishlistEntity wishlistEntity) {
		return WishlistDTO.builder()
				.id(wishlistEntity.getId())
				.userId(wishlistEntity.getUserId())
				.productIds(wishlistEntity.getProductIds())
				.build();
	}

}
