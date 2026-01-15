package com.ey.dto.response;

import com.ey.enums.AuctionStatus;

public class AuctionResponse {
	private Long auctionId;
	private AuctionStatus status;
	public Long getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(Long auctionId) {
		this.auctionId = auctionId;
	}
	public AuctionStatus getStatus() {
		return status;
	}
	public void setStatus(AuctionStatus status) {
		this.status = status;
	}

}
