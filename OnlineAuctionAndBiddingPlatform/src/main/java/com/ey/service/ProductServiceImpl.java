package com.ey.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dto.request.ProductRequest;
import com.ey.dto.response.ProductResponse;
import com.ey.entity.Product;
import com.ey.entity.User;
import com.ey.enums.ApprovalStatus;
import com.ey.enums.Role;
import com.ey.exception.ProductNotFoundException;
import com.ey.exception.UserNotFoundException;
import com.ey.mapper.ProductMapper;
import com.ey.repository.ProductRepository;
import com.ey.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
    private  UserRepository userRepository;
	@Autowired
	private  ProductRepository productRepository;
	@Autowired
	private  ProductMapper productMapper;
	
	Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Override
	public ProductResponse createProduct(@Valid ProductRequest req) {
		User user = userRepository.findById(req.getSellerId())
		        .orElseThrow(() -> {
		        log.error("User not found");
		        return new UserNotFoundException("User not found");});

		if (user.getRole() != Role.SELLER) {
			log.error("Only Seller can create products");
		    throw new RuntimeException("Only SELLER can create products");
		}

		Product product = productMapper.toEntity(req);
		Product savedProduct = productRepository.save(product);
		return productMapper.toResponse(savedProduct);
	}
	@Override
	public ProductResponse getProductById(Long productId) {
	    Product product = productRepository.findById(productId)
	            .orElseThrow(() -> {
	            log.error("Product not found");
	            return new ProductNotFoundException("Product not found");});
	    return productMapper.toResponse(product);
	}
	@Override
	public List<ProductResponse> getProductsBySeller(Long sellerId) {

	    return productRepository.findBySellerId(sellerId)
	            .stream()
	            .map(productMapper::toResponse)
	            .toList();
	}
	
	@Override
	public ProductResponse approveProduct(Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> {
				log.error("Product not found");
				return new ProductNotFoundException("Product not found");});
		if(product.getApprovalStatus() == ApprovalStatus.APPROVED) {
			log.error("Product already approved");
			throw new RuntimeException("Product already approved");
		}
		product.setApprovalStatus(ApprovalStatus.APPROVED);
		Product savedProduct = productRepository.save(product);
		return productMapper.toResponse(savedProduct);
	}
	@Override
	public ProductResponse rejectProduct(Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> {
				log.error("Product not found");
				return new ProductNotFoundException("Product not found");});
		product.setApprovalStatus(ApprovalStatus.REJECTED);
		Product savedProduct = productRepository.save(product);
		return productMapper.toResponse(savedProduct);
	}
	@Override
	public List<ProductResponse> getAllProducts() {
		return productRepository.findAll()
	            .stream()
	            .map(productMapper::toResponse)
	            .toList();
	}


	
	

}
