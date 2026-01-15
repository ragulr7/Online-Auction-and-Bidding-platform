package com.ey.dto.response;

public class BidResponse {
	private Long bidId;
	private Double currentHighestBid;
	public Long getBidId() {
		return bidId;
	}
	public void setBidId(Long bidId) {
		this.bidId = bidId;
	}
	public Double getCurrentHighestBid() {
		return currentHighestBid;
	}
	public void setCurrentHighestBid(Double currentHighestBid) {
		this.currentHighestBid = currentHighestBid;
	}
	

}
