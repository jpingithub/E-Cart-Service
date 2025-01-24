package com.cart_management.cart_management.service;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cart_management.cart_management.client.ProductClient;
import com.cart_management.cart_management.dto.BuyingRequest;
import com.cart_management.cart_management.dto.Product;
import com.cart_management.cart_management.dto.PurchaseDetailsResponse;
import com.cart_management.cart_management.entity.BuyingEntity;
import com.cart_management.cart_management.exception.NoDataFoundException;
import com.cart_management.cart_management.repository.PurchaseRepo;
import com.google.common.base.Optional;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseService {
	
	private final ProductClient productClient;
	private final PurchaseRepo purchaseRepo;
	
	public BuyingEntity buyProduct(BuyingRequest request) {
		BuyingEntity buyingEntity = new BuyingEntity();
		buyingEntity.setAddress(request.getAddress());
		buyingEntity.setMobileNumber(request.getMobileNumber());
		buyingEntity.setProductId(request.getProductId());
		buyingEntity.setUserId(request.getUserId());
		buyingEntity.setPincode(request.getPincode());
		buyingEntity.setQuantity(request.getQuantity());
		Double price = productClient.byId(request.getProductId()).getPrice();
		buyingEntity.setPrice(price*request.getQuantity());
		buyingEntity.setTime(Instant.now());
		buyingEntity.setDeliveryDate(Instant.now().plusSeconds(86400*2));
		return purchaseRepo.save(buyingEntity);
	}
	
	public List<BuyingEntity> getAllData(){
		return (List<BuyingEntity>) purchaseRepo.findAll();
	}
	
	public List<PurchaseDetailsResponse> boughtProducts(Integer id){
		Optional<List<BuyingEntity>> optionalPurchases = purchaseRepo.findByUserId(id);
		if(optionalPurchases.isPresent()) {
			PurchaseService purchaseService = new PurchaseService(productClient, purchaseRepo);
			return optionalPurchases.get().stream()
				.map(purchaseService::getActualPurchaseData).toList();
		}
		else throw new NoDataFoundException("No purchases yet...");
	}
	private PurchaseDetailsResponse getActualPurchaseData(BuyingEntity entity) {
		Integer productId = entity.getProductId();
		Product product = productClient.byId(productId);
		PurchaseDetailsResponse response = new PurchaseDetailsResponse();
		response.setId(entity.getId());
		response.setImage(product.getImage());
		response.setAddress(entity.getAddress());
		response.setPrice(product.getPrice());
		response.setProductName(product.getCompany()+" "+product.getModel());
		response.setTotal(entity.getPrice());
		response.setQuantity(entity.getQuantity());
		return response;
	}
	
	@Transactional
	public void deleteByProductId(Integer productId) {
		purchaseRepo.deleteByProductId(productId);
	}
}
