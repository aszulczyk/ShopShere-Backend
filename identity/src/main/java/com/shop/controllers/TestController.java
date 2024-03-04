package com.shop.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.model.UserEntity;

@RestController
@RequestMapping("/auth")
public class TestController {
	
	@GetMapping("/test")
	public String registerUser() {
		return "You are logined";
	}
	

}
