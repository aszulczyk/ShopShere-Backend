package com.shop.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shop.model.CartEntity;
import com.shop.model.ProductEntity;

@Repository
public interface ProductsRepository extends JpaRepository<ProductEntity, Integer> {
	
	@Query(value = "FROM ProductEntity pe INNER JOIN CartEntity ce ON pe.cartEntity.id = ce.id "
			+ "WHERE ce.id = :cartId ")
	List<ProductEntity> findAllProductsByCartEntity(int cartId);
	

}
