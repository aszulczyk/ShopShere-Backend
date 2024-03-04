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

import com.shop.services.CartService;
import com.shop.services.IdentityClientService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartService cartService;

	@Autowired
	IdentityClientService identityClientService;

	@GetMapping("/test")
	public String test() {
		return "Working";
	}

	@PostMapping("/create/{userId}")
	public ResponseEntity<CartDTO> createNewCart(@PathVariable("userId") int userId, @RequestHeader("Authorization") String token) {
		String jwt = token.substring(7);
		if (Boolean.valueOf(identityClientService.validateJWTAndUserId(jwt, userId))) {
			return ResponseEntity.ok(cartService.createUserCart(userId));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}

	@GetMapping("/{userId}")
	public ResponseEntity<CartDTO> getCartByUserId(@PathVariable("userId") int userId, @RequestHeader("Authorization") String token) {
		String jwt = token.substring(7);
		if (Boolean.valueOf(identityClientService.validateJWTAndUserId(jwt, userId))) {
			return ResponseEntity.ok(cartService.getUserCart(userId));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}

	@PostMapping("/{userId}/add")
	public ResponseEntity<CartDTO> addProductById(@PathVariable("userId") int userId, @RequestBody ProductDTO productDTO, @RequestHeader("Authorization") String token) {
		String jwt = token.substring(7);
		if (Boolean.valueOf(identityClientService.validateJWTAndUserId(jwt, userId))) {
			return ResponseEntity.ok(cartService.addProduct(userId, productDTO));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
	
	@PostMapping("/{userId}/refresh")
	public ResponseEntity<CartDTO> refreshCart(@PathVariable("userId") int userId, @RequestHeader("Authorization") String token) {
		String jwt = token.substring(7);
		if (Boolean.valueOf(identityClientService.validateJWTAndUserId(jwt, userId))) {
			return ResponseEntity.ok(cartService.refreshCart(userId));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}

	@DeleteMapping("/{userId}/remove/{productId}")
	public ResponseEntity<CartDTO> deleteProduct(@PathVariable("userId") int userId,
			@PathVariable("productId") int productId, @RequestHeader("Authorization") String token) {
		String jwt = token.substring(7);
		if (Boolean.valueOf(identityClientService.validateJWTAndUserId(jwt, userId))) {
			return ResponseEntity.ok(cartService.deleteProduct(userId, productId));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}

	@PutMapping("/cart/{userId}/update")
	public ResponseEntity<CartDTO> updateProduct(@PathVariable("userId") int userId,
			@RequestBody ProductDTO productDTO, @RequestHeader("Authorization") String token) {
		String jwt = token.substring(7);
		if (Boolean.valueOf(identityClientService.validateJWTAndUserId(jwt, userId))) {
			return ResponseEntity.ok(cartService.updateProduct(userId, productDTO));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}

}
