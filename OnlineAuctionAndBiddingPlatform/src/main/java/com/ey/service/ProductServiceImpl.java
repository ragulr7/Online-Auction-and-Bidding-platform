package com.ey.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ey.dto.request.ProductRequest;
import com.ey.dto.response.ProductResponse;
import com.ey.entity.Product;
import com.ey.mapper.ProductMapper;
import com.ey.repository.ProductRepository;

import jakarta.validation.Valid;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;
	private final ProductMapper productMapper;
	public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
		super();
		this.productRepository = productRepository;
		this.productMapper = productMapper;
	}
	@Override
	public ProductResponse createProduct(@Valid ProductRequest req) {
		Product product = productMapper.toEntity(req);
		Product savedProduct = productRepository.save(product);
		return productMapper.toResponse(savedProduct);
	}
	@Override
	public ProductResponse getProductById(Long productId) {
	    Product product = productRepository.findById(productId)
	            .orElseThrow(() -> new RuntimeException("Product not found"));
	    return productMapper.toResponse(product);
	}
	@Override
	public List<ProductResponse> getProductsBySeller(Long sellerId) {

	    return productRepository.findBySellerId(sellerId)
	            .stream()
	            .map(productMapper::toResponse)
	            .toList();
	}


	
	

}
