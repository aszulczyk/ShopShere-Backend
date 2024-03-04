package com.shop.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.model.WishlistEntity;

@Repository
public interface WishlistEntityRepository extends JpaRepository<WishlistEntity, Integer> {
	
	Optional<WishlistEntity> findByUserId(int userId);
}
