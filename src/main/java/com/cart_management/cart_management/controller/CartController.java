package com.cart_management.cart_management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cart_management.cart_management.dto.AddToCartRequest;
import com.cart_management.cart_management.dto.AddToCartResponse;
import com.cart_management.cart_management.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CartController {
	private final CartService cartService;
	@PostMapping
	ResponseEntity<AddToCartResponse> addToCart(@RequestBody AddToCartRequest req) {
		return ResponseEntity.ok().body(cartService.addToCart(req));
	}
	@DeleteMapping("/{productId}/{userId}")
	void deleteByProductIdAndUserId(@PathVariable("productId") Integer productId,@PathVariable("userId") Integer userId) {
		cartService.deleteByProductIdAndUserId(productId,userId);
	}
	@GetMapping("/{userId}")
	ResponseEntity<List<AddToCartResponse>> cartItemsByUserId(@PathVariable("userId")Integer userId){
		return ResponseEntity.ok().body(cartService.allItemsByUserId(userId));
	}
	@DeleteMapping("/{productId}")
	void deleteByProductId(@PathVariable("productId") Integer productId) {
		cartService.deleteByProductId(productId);
	}
}
