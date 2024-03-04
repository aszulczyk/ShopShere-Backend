package com.shop.controllers;

import org.apache.hc.client5.http.auth.AuthStateCacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shop.service.AdminProductService;
import com.shop.service.IdentityClientService;

@RestController
@RequestMapping("/admin/products")
public class AdminController {

	@Autowired
	private AdminProductService adminProductService;

	@Autowired
	private IdentityClientService identityClientService;
	
	@PostMapping("/test")
	public ResponseEntity<String> addProduct(@RequestHeader("Authorization") String token) {
		String jwt = token.substring(7);
		if (Boolean.valueOf(identityClientService.validateJWTAndIsAdmin(jwt))) {
			return ResponseEntity.ok("admin test is working");
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@PostMapping("/add")
	public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO,
			@RequestHeader("Authorization") String token) {
		String jwt = token.substring(7);
		if (Boolean.valueOf(identityClientService.validateJWTAndIsAdmin(jwt))) {
			return ResponseEntity.ok(adminProductService.addNewProduct(productDTO));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}

	@PutMapping("/{productId}")
	public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO,
			@PathVariable("productId") int productId, @RequestHeader("Authorization") String token) {
		String jwt = token.substring(7);
		if (Boolean.valueOf(identityClientService.validateJWTAndIsAdmin(jwt))) {
			return ResponseEntity.ok(adminProductService.updateProduct(productDTO, productId));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity deleteProduct(@PathVariable("productId") int productId, @RequestHeader("Authorization") String token) {
		String jwt = token.substring(7);
		if (Boolean.valueOf(identityClientService.validateJWTAndIsAdmin(jwt))) {
			if (adminProductService.deleteProductById(productId)) {
				return ResponseEntity.ok().build();
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}

}
