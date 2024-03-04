package com.shop.repositories;

import java.util.List;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.model.OrderEntity;
import com.shop.model.ProductEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
	
	List<OrderEntity> findByuserId(int userId);

}
