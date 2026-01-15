package com.ey.mapper;

import org.springframework.stereotype.Component;

import com.ey.dto.request.ProductRequest;
import com.ey.dto.response.ProductResponse;
import com.ey.entity.Product;
import com.ey.enums.ApprovalStatus;

@Component
public class ProductMapper {
	public Product toEntity(ProductRequest req) {
		Product product = new Product();
		product.setName(req.getName());
		product.setDescription(req.getDescription());
		product.setStartingPrice(req.getStartingPrice());
		product.setExpectedPrice(req.getExpectedPrice());
		product.setSellerId(req.getSellerId());
		product.setApprovalStatus(ApprovalStatus.PENDING);
		return product;
	}
	public ProductResponse toResponse(Product product) {
		ProductResponse res = new ProductResponse();
		res.setProductId(product.getProductId());
		res.setName(product.getName());
		res.setApprovalStatus(product.getApprovalStatus());
		return res;
	}
	

}
