package com.cart_management.cart_management.client;

import java.util.List;
import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cart_management.cart_management.dto.Product;
import com.cart_management.cart_management.dto.ProductRequest;

@FeignClient(name = "product-management")
public interface ProductClient {

    @GetMapping("/products")
    List<Product> getAllProducts();

    @PostMapping("/products")
    Product saveProduct(@RequestBody ProductRequest req);
    
    @GetMapping("/products/{id}")
	Product byId(@PathVariable("id") Integer id);

    @GetMapping("/products/category/{category}")
    Set<Product> productsByCategory(@PathVariable("category") String category);

    @GetMapping("/products/{category}/{id}")
    Product productsByCategoryAndId(@PathVariable("category") String category, @PathVariable("id") Integer id);

    @DeleteMapping("/products/{id}")
    Product deleteById(@PathVariable("id") Integer id);

    @PatchMapping("/products/{id}/{type}")
    Product updateDescription(@PathVariable("id") Integer id,
                              @PathVariable("type") String type, @RequestBody Object newValue);
}
