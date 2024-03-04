package com.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.services.IdentityClientService;
import com.shop.services.WishlistService;


@RestController
@RequestMapping("/wishlist")
public class WishlistController {
	
	@Autowired
	WishlistService wishlistService;
	
	@Autowired
	IdentityClientService identityClientService;
	
	@GetMapping("/test")
	public String test() {
		return "Working";
	}
	
	@PostMapping("/create/{userId}")
	public ResponseEntity<WishlistDTO> createNewWishlist(@PathVariable("userId") int userId, @RequestHeader("Authorization") String token) {
		String jwt = token.substring(7);
		if (Boolean.valueOf(identityClientService.validateJWTAndUserId(jwt, userId))) {
			return ResponseEntity.ok(wishlistService.createWishlist(userId));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<WishlistDTO> getCartByUserId(@PathVariable("userId") int userId, @RequestHeader("Authorization") String token) {
		String jwt = token.substring(7);
		if (Boolean.valueOf(identityClientService.validateJWTAndUserId(jwt, userId))) {
			return ResponseEntity.ok(wishlistService.getUserWishlist(userId));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
	
	@PostMapping("/{userId}/add/{productId}")
	public ResponseEntity<WishlistDTO> addProductById(@PathVariable("userId") int userId, @PathVariable("productId") int productId, @RequestHeader("Authorization") String token) {
		String jwt = token.substring(7);
		if (Boolean.valueOf(identityClientService.validateJWTAndUserId(jwt, userId))) {
			return ResponseEntity.ok(wishlistService.addProduct(userId, productId));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
	
	@DeleteMapping("/{userId}/remove/{productId}")
	public ResponseEntity<WishlistDTO> deleteProduct(@PathVariable("userId") int userId, @PathVariable("productId") int productId, @RequestHeader("Authorization") String token) {
		String jwt = token.substring(7);
		if (Boolean.valueOf(identityClientService.validateJWTAndUserId(jwt, userId))) {
			return ResponseEntity.ok(wishlistService.deleteProduct(userId, productId));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
	

}
