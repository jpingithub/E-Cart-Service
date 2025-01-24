package com.cart_management.cart_management.dto;

import lombok.Data;

@Data
public class Product {
	
	private int id;
	private String image;
	private String model;
	private String company;
	private String description;
	private Double price;
	private Integer stock;
	private Category category;
}