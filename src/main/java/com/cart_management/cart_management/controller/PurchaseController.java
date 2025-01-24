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

import com.cart_management.cart_management.dto.BuyingRequest;
import com.cart_management.cart_management.dto.PurchaseDetailsResponse;
import com.cart_management.cart_management.entity.BuyingEntity;
import com.cart_management.cart_management.service.CartService;
import com.cart_management.cart_management.service.PurchaseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class PurchaseController {
	
	private final PurchaseService purchaseService;
	private final CartService cartService;
	
	@PostMapping
	ResponseEntity<BuyingEntity> buyProduct(@RequestBody BuyingRequest req) {
		cartService.deleteByProductIdAndUserId(req.getProductId(), req.getUserId());
		return ResponseEntity.ok().body(purchaseService.buyProduct(req));
	}
	@GetMapping
	ResponseEntity<List<BuyingEntity>> allPurchases(){
		return ResponseEntity.ok(purchaseService.getAllData());
	}
	@GetMapping("/{id}")
	ResponseEntity<List<PurchaseDetailsResponse>> boughtProducts(@PathVariable("id") Integer id){
		return ResponseEntity.ok().body(purchaseService.boughtProducts(id));
	}
	@DeleteMapping("/{productId}")
	void deleteByProductId(@PathVariable("productId") Integer productId) {
		purchaseService.deleteByProductId(productId);
	}
}
