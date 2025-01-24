package com.cart_management.cart_management.dto;

import lombok.Data;

@Data
public class AddToCartRequest {
	private Integer userId;
	private Integer productId;
	private Integer quantity;
}
