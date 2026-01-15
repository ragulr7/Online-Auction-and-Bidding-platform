package com.ey.entity;

import com.ey.enums.ApprovalStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	private String name;
	private String description;
	private Double startingPrice;
	private Double expectedPrice;
	
	@Enumerated(EnumType.STRING)
	private ApprovalStatus approvalStatus;
	private Long sellerId;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getStartingPrice() {
		return startingPrice;
	}
	public void setStartingPrice(Double startingPrice) {
		this.startingPrice = startingPrice;
	}
	public Double getExpectedPrice() {
		return expectedPrice;
	}
	public void setExpectedPrice(Double expectedPrice) {
		this.expectedPrice = expectedPrice;
	}
	public ApprovalStatus getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(ApprovalStatus approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

}
