package com.ey.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.ey.enums.AuctionStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Auction {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long auctionId;
	private Long productId;
	private LocalDate auctionDate;
	private LocalTime startTime;
	private LocalTime endTime;
	
	@Enumerated(EnumType.STRING)
	private AuctionStatus status;
	private Double highestBid;
	private Long winnerId;
	public Long getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(Long auctionId) {
		this.auctionId = auctionId;
	}
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
	public AuctionStatus getStatus() {
		return status;
	}
	public void setStatus(AuctionStatus status) {
		this.status = status;
	}
	public Double getHighestBid() {
		return highestBid;
	}
	public void setHighestBid(Double highestBid) {
		this.highestBid = highestBid;
	}
	public Long getWinnerId() {
		return winnerId;
	}
	public void setWinnerId(Long winnerId) {
		this.winnerId = winnerId;
	}

}
