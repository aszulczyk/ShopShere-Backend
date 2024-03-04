package com.shop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.service.ProductService;

import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/test")
	public String test() {
		return "Controller is working";
	}

	@GetMapping("/all")
	public List<ProductDTO> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/{productId}")
	public ProductDTO getProductById(@PathVariable("productId") int productId) {
		return productService.getProductByID(productId);
	}
	
	@GetMapping("/categories/{categoryName}")
	public List<ProductDTO> getProductsByCategory(@PathVariable("categoryName") String categoryName) {
		return productService.getProductsByCategory(categoryName);
	}
	

}
