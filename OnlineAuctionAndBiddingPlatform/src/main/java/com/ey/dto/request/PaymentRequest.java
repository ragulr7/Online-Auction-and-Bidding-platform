package com.ey.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PaymentRequest {
	@NotNull(message ="Buyer ID is required")
	private Long buyerId;
	
	@NotNull(message = "Amount is required")
	@Positive(message = "Amount must be positive")
	private Double amount;
	
	@NotNull(message = "Payment method required")
	private String method;

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

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	

}
