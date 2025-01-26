package com.cart_management.cart_management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cart_management.cart_management.entity.CartItem;

@Repository
public interface CartRepository extends CrudRepository<CartItem, Integer> {
	Optional<CartItem> findByProductIdAndUserId(Integer productId,Integer userId);
	Optional<List<CartItem>> findByUserId(Integer userId);
	void deleteByProductIdAndUserId(Integer productId, Integer userId);
	void deleteByProductId(Integer productId);
}
