package com.ey.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class BidRequest {
	
	@NotNull(message ="Buyer ID is required")
	private Long buyerId;
	
	@NotNull(message = "Bid amount is required")
	@Positive(message = "Bid amount must be greater than zero")
	private Double amount;

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	

}
