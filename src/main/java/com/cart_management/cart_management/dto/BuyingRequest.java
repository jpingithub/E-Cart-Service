package com.cart_management.cart_management.dto;

import lombok.Data;

@Data
public class BuyingRequest {
	private Integer productId;
	private Integer userId;
	private Integer quantity;
	private String address;
	private Long mobileNumber;
	private Integer pincode;
}
