package com.ey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.ProductRequest;
import com.ey.dto.response.ProductResponse;
import com.ey.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private  ProductService productService;
	
	@PostMapping
	public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody
			ProductRequest req){
		return new ResponseEntity<>(productService.createProduct(req),HttpStatus.CREATED);
	}
	@GetMapping("/{productId}")
	public ResponseEntity<ProductResponse> getProductById
	(@PathVariable Long productId) {

	    return ResponseEntity.ok(productService.getProductById(productId)
	    ); 
	}
	@GetMapping("/seller/{sellerId}")
	public ResponseEntity<List<ProductResponse>> getProductsBySeller(
	        @PathVariable Long sellerId) {

	    return ResponseEntity.ok(productService.getProductsBySeller(sellerId)
	    );
	}
	@GetMapping
	public ResponseEntity<List<ProductResponse>> getAllProducts() {
	    return ResponseEntity.ok(productService.getAllProducts());
	}

	@PutMapping("/admin/approve/{productId}")
	public ResponseEntity<ProductResponse> approveProduct(@PathVariable Long productId){
		return ResponseEntity.ok(productService.approveProduct(productId));
	}
	@PutMapping("admin/reject/{productId}")
	public ResponseEntity<ProductResponse> rejectProduct(@PathVariable Long productId){
		return ResponseEntity.ok(productService.rejectProduct(productId));
	}



}
