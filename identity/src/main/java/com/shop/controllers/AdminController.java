package com.shop.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shop.model.UserEntity;
import com.shop.services.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@GetMapping("/users")
	public List<UserDTO> getUsers() {
		return adminService.getAllUsers().stream().map(UserDTO::userEntityToUserDTO).collect(Collectors.toList());
	}
	
	@PutMapping("/users/{id}")
	public UserDTO updateUser(@PathVariable("id") int id, @RequestBody UserDTO userDTO) {
		userDTO.setId(id);
		UserDTO newUserDTO = adminService.updateUser(userDTO, id);
		return newUserDTO;
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity deleteUser(@PathVariable("id") int id) {
		adminService.deleteUser(id);
		return ResponseEntity.ok().build();
	}
	

}
