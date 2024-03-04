package com.shop.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.model.CartEntity;

@Repository
public interface CartEntityRepository extends JpaRepository<CartEntity, Integer> {
	
	Optional<CartEntity> findByUserId(int userId);
}
