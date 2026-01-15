package com.ey.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductRequest {
	@NotNull (message ="Seller ID is required")
	private Long sellerId;
	
	@NotBlank(message ="Product name is required")
	private String name;
	
	@NotBlank(message ="Description is required")
	private String description;

	@NotNull
	@Positive(message = "Price must be positive")
	private Double startingPrice;
	
	@NotNull
	@Positive(message = "Price must be positive")
	private Double expectedPrice;

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
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

}
