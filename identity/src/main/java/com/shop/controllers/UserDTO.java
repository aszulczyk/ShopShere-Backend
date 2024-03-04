package com.shop.controllers;

import com.shop.model.Role;
import com.shop.model.UserEntity;
import com.shop.model.UserInfo;

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
public class UserDTO {
	
	private int id;
	private String username;
	private String email;
	private String password;
	private String role;
	private String jwt;
	private UserInfo userInfo;
	
	public static UserEntity UserDTOToUserEntity(UserDTO userDTO) {
		UserEntity userEntity = UserEntity.builder()
				.username(userDTO.getUsername())
				.email(userDTO.getEmail())
				.password(userDTO.getPassword())
				.role(Role.valueOf(userDTO.getRole().toUpperCase()))
				.userInfo(userDTO.getUserInfo())
				.build();
		return userEntity;
	}
	
	public static UserDTO userEntityToUserDTO(UserEntity userEntity) {
		UserDTO userDTO = UserDTO.builder()
				.id(userEntity.getId())
				.username(userEntity.getUsername())
				.email(userEntity.getEmail())
				.password(userEntity.getPassword())
				.role(userEntity.getRole().toString())
				.userInfo(userEntity.getUserInfo())
				.build();
		return userDTO;
	}

}
