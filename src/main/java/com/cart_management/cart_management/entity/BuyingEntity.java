package com.cart_management.cart_management.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "purchases")
@Data
public class BuyingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer productId;
	private Integer userId;
	private String address;
	private Long mobileNumber;
	private Integer pincode;
	private Instant time;
	private Integer quantity;
	private Double price;
	private Instant deliveryDate;
}
