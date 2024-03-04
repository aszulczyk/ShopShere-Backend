package com.shop.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.model.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {
	
	Optional<UserEntity> findByUsername(String username);
	
	Optional<UserEntity> findByEmail(String email);
	
}
