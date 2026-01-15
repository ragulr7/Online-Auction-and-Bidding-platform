package com.ey.service;

import java.util.List;

import com.ey.dto.request.BidRequest;
import com.ey.dto.response.BidResponse;
import com.ey.entity.Bid;

public interface BidService {
	BidResponse placeBid(Long auctionId , BidRequest req);

	List<Bid> getBidsByAuction(Long auctionId);
}
