package com.cart_management.cart_management.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cart_management.cart_management.entity.BuyingEntity;
import com.google.common.base.Optional;

@Repository
public interface PurchaseRepo extends CrudRepository<BuyingEntity, Integer> {

	Optional<List<BuyingEntity>> findByUserId(Integer id);
	void deleteByProductId(Integer productId);

}
