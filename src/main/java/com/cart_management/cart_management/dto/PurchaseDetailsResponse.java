package com.cart_management.cart_management.dto;

import lombok.Data;

@Data
public class PurchaseDetailsResponse {
	private Integer id;
	private String image;
	private String productName;
	private Double price;
	private Integer quantity;
	private Double total;
	private String address;
}
