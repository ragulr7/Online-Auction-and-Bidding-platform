package com.ey.service;

import java.util.List;

import com.ey.dto.request.ProductRequest;
import com.ey.dto.response.ProductResponse;

import jakarta.validation.Valid;

public interface ProductService {

	ProductResponse createProduct(@Valid ProductRequest req);

	ProductResponse getProductById(Long productId);

	List<ProductResponse> getProductsBySeller(Long sellerId); 

}
