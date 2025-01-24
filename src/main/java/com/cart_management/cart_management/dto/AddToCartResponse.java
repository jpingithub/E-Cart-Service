package com.cart_management.cart_management.dto;

import lombok.Data;

@Data
public class AddToCartResponse {
	private Integer id;
	private String image;
	private String name;
	private Double price;
	private Integer quantity;
	private Double total;
}
