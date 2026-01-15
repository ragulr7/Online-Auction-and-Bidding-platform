package com.ey.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Bid {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bidId;
	private Long buyerId;
	private Long auctionId;
	private Double amount;
	private LocalDateTime bidTime;
	public Long getBidId() {
		return bidId;
	}
	public void setBidId(Long bidId) {
		this.bidId = bidId;
	}
	public Long getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}
	public Long getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(Long auctionId) {
		this.auctionId = auctionId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public LocalDateTime getBidTime() {
		return bidTime;
	}
	public void setBidTime(LocalDateTime bidTime) {
		this.bidTime = bidTime;
	}

}
