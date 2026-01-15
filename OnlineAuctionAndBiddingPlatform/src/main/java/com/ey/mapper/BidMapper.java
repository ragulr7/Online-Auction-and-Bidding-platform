package com.ey.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.ey.dto.request.BidRequest;
import com.ey.entity.Bid;

@Component
public class BidMapper {
	public Bid toEntity(BidRequest req, Long auctionId) {
	
		Bid bid = new Bid();
		bid.setAuctionId(auctionId);
		bid.setBuyerId(req.getBuyerId());
		bid.setAmount(req.getAmount());
		bid.setBidTime(LocalDateTime.now());
		return bid;
	}

}
