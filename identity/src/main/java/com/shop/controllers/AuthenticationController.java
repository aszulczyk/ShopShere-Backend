package com.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.model.Role;
import com.shop.model.UserEntity;
import com.shop.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	AuthenticationService authenticationService;
	
	@PostMapping("/register")
	public UserDTO registerUser(@RequestBody UserDTO userDTO) {
		userDTO.setRole("User");
		UserEntity userEntity = UserDTO.UserDTOToUserEntity(userDTO);
		
		return authenticationService.saveUser(userEntity);
	}
	
	@PostMapping("/register/admin")
	public UserDTO registerAdmin(@RequestBody UserDTO userDTO) {
		userDTO.setRole("ADMIN");
		UserEntity userEntity = UserDTO.UserDTOToUserEntity(userDTO);
		
		return authenticationService.saveAdmin(userEntity);
	}
	
	@PostMapping("/login")
	public UserDTO loginUser(@RequestBody LoginDTO loginDTO) {
		return authenticationService.loginUser(loginDTO);
	}
	
	@PostMapping("/validate-jwt")
	public String validateJWT(@RequestParam("jwt") String jwt) {
		return String.valueOf(authenticationService.validateJWT(jwt));
	}
	
	@PostMapping("/validate-jwt-userid")
	public String validateJWTAndUserId(@RequestParam("jwt") String jwt, @RequestParam("userid") int userid) {
		return String.valueOf(authenticationService.validateJWTAndUserId(jwt, userid));
	}
	
	@PostMapping("/validate-jwt-admin")
	public String validateJWTAndIsAdmin(@RequestParam("jwt") String jwt) {
		return String.valueOf(authenticationService.validateJWTAndIsAdmin(jwt));
	}
	

}
