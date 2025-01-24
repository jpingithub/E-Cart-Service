package com.cart_management.cart_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cart_management.cart_management.client.ProductClient;
import com.cart_management.cart_management.dto.AddToCartRequest;
import com.cart_management.cart_management.dto.AddToCartResponse;
import com.cart_management.cart_management.dto.Product;
import com.cart_management.cart_management.entity.CartItem;
import com.cart_management.cart_management.exception.NoDataFoundException;
import com.cart_management.cart_management.repository.CartRepository;
import com.google.common.base.Optional;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {

	private final CartRepository cartRepo;
	private final ProductClient productClient;

	public AddToCartResponse addToCart(AddToCartRequest req) {
		CartItem response = new CartItem();
		Optional<CartItem> optional = cartRepo.findByProductIdAndUserId(req.getProductId(), req.getUserId());
		if (optional.isPresent()) {
			CartItem cartItem = optional.get();
			Double price =  cartItem.getTotal()/cartItem.getQuantity();
			cartItem.setQuantity(cartItem.getQuantity()+req.getQuantity());
			cartItem.setTotal(cartItem.getQuantity()*price);
			response = cartRepo.save(cartItem);
		} else {
			Double price = productClient.byId(req.getProductId()).getPrice();
			CartItem cartItem = new CartItem();
			cartItem.setProductId(req.getProductId());
			cartItem.setQuantity(req.getQuantity());
			cartItem.setTotal(req.getQuantity() * price);
			cartItem.setUserId(req.getUserId());
			response = cartRepo.save(cartItem);
		}
		return covertActualResponse(response);
	}

	private AddToCartResponse covertActualResponse(CartItem cart) {
		Product product = productClient.byId(cart.getProductId());
		AddToCartResponse response = new AddToCartResponse();
		response.setId(cart.getProductId());
		response.setTotal(cart.getTotal());
		response.setImage(product.getImage());
		response.setName(product.getCompany() + " " + product.getModel());
		response.setQuantity(cart.getQuantity());
		response.setPrice(product.getPrice());
		return response;
	}
	
	public List<AddToCartResponse> allItemsByUserId(Integer userId){
		Optional<List<CartItem>> optionalData = cartRepo.findByUserId(userId);
		if(optionalData.isPresent()) {
			CartService cartService = new CartService(cartRepo, productClient);
			return optionalData.get().stream().map(cartService::covertActualResponse).toList();
		}else throw new NoDataFoundException("No products added by user : "+userId);
	}

	@Transactional
	public void deleteByProductIdAndUserId(Integer productId, Integer userId) {
			cartRepo.deleteByProductIdAndUserId(productId, userId);
	}
	
	@Transactional
	public void deleteByProductId(Integer productId) {
		cartRepo.deleteByProductId(productId);
	}

}
