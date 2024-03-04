package com.shop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.service.IdentityClientService;
import com.shop.service.OrderService;

import io.jsonwebtoken.security.Request;

import org.springframework.web.bind.annotation.RequestParam;


 
@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	IdentityClientService identityClientService;
	
	@GetMapping("/test")
	public String test() {
		return "Controller is working";
	}
	

	@GetMapping("/{userId}")
	public ResponseEntity<List<OrderDTO>> getAllOrdersByUserId(@PathVariable("userId") int userId, @RequestHeader("Authorization") String token) {
		String jwt = token.substring(7);
		if (Boolean.valueOf(identityClientService.validateJWTAndUserId(jwt, userId))) {
			return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
	
	@PostMapping("/{userId}")
	public ResponseEntity<OrderDTO> create(@PathVariable("userId") int userId, @RequestBody OrderDTO orderDTO, @RequestHeader("Authorization") String token) {
		String jwt = token.substring(7);
		if (Boolean.valueOf(identityClientService.validateJWTAndUserId(jwt, userId))) {
			return ResponseEntity.ok(orderService.createOrder(userId, orderDTO));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
	
	@GetMapping("/details/{orderId}")
	public OrderDTO getOrderDetails(@PathVariable("orderId") int orderId) {
		return orderService.getOrderDetails(orderId);
	}

}
