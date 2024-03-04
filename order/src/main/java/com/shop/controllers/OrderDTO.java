package com.shop.controllers;

import java.util.List;

import com.shop.model.OrderEntity;
import com.shop.model.ProductEntity;

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
public class OrderDTO {
	
	private int id;
	private int userId;
	private boolean delivered;
	private List<ProductDTO> products;
	
	public static OrderEntity orderDTOToOrderEntity(OrderDTO orderDTO) {
		List<ProductEntity> productEntities = orderDTO.getProducts().stream().map(ProductDTO::ProductDTOToProductEntity).toList();
		return OrderEntity.builder()
		.userId(orderDTO.getUserId())
		.delivered(orderDTO.isDelivered())
		.products(productEntities)
		.build();
	}
	
	public static OrderDTO orderEntityToOrderDTO(OrderEntity orderEntity) {
		List<ProductDTO> productDTOs = orderEntity.getProducts().stream().map(ProductDTO::ProductEntityToProductDTO).toList();
		return OrderDTO.builder()
				.id(orderEntity.getId())
				.userId(orderEntity.getUserId())
				.delivered(orderEntity.isDelivered())
				.products(productDTOs)
				.build();
	}
	
	

}
