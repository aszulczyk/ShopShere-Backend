package com.shop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.controllers.UserDTO;
import com.shop.model.UserEntity;
import com.shop.repositories.UserEntityRepository;

@Service
public class AdminService {
	
	@Autowired
	private UserEntityRepository userEntityRepository;
	
	public List<UserEntity> getAllUsers() {
		List<UserEntity> users = userEntityRepository.findAll();
		return users;
	}
	
	public UserDTO updateUser(UserDTO userDTO, int id) {
		userDTO.setId(id);
		UserEntity userEntity = userEntityRepository.save(UserDTO.UserDTOToUserEntity(userDTO));
		return UserDTO.userEntityToUserDTO(userEntity);
	}
	
	public void deleteUser(int userId) {
		userEntityRepository.delete(userEntityRepository.getReferenceById(userId));
	}

}
