package com.ey.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;

public class AuctionRequest {
	@NotNull(message = "Product ID is required")
	private Long productId;
	
	@NotNull(message = "Auction date is required")
	private LocalDate auctionDate;
	
	@NotNull(message = "Start time is required")
	private LocalTime startTime;
	
	@NotNull(message = "End time is required")
	private LocalTime endTime;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public LocalDate getAuctionDate() {
		return auctionDate;
	}

	public void setAuctionDate(LocalDate auctionDate) {
		this.auctionDate = auctionDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

}
