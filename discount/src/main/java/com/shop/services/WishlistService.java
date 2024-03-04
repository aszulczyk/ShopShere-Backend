package com.shop.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.controllers.WishlistDTO;
import com.shop.model.WishlistEntity;
import com.shop.repositories.WishlistEntityRepository;

@Service
public class WishlistService {

	@Autowired
	WishlistEntityRepository wishlistEntityRepository;
	
	public WishlistDTO createWishlist(int userId) {
		WishlistEntity cartEntity = WishlistEntity.builder()
				.userId(userId)
				.productIds(new HashSet<Integer>())
				.build();
		
		return WishlistDTO.wishlistEntityToWishlistDTO(wishlistEntityRepository.save(cartEntity));
	}

	public WishlistDTO getUserWishlist(int userId) {
		return WishlistDTO.wishlistEntityToWishlistDTO(wishlistEntityRepository.findByUserId(userId).orElse(new WishlistEntity()));
	}

	public WishlistDTO addProduct(int userId, int productId) {
		WishlistEntity wishListEntity = wishlistEntityRepository.findByUserId(userId).orElseThrow();
		wishListEntity.getProductIds().add(productId);
		return WishlistDTO.wishlistEntityToWishlistDTO(wishlistEntityRepository.save(wishListEntity));
	}
	
	public WishlistDTO deleteProduct(int userId, int productId) {
		WishlistEntity wishListEntity = wishlistEntityRepository.findByUserId(userId).orElseThrow();
		wishListEntity.getProductIds().remove(productId);
		return WishlistDTO.wishlistEntityToWishlistDTO(wishlistEntityRepository.save(wishListEntity));
	}

}
