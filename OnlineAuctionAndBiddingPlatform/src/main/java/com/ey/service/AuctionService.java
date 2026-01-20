package com.ey.service;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.ey.dto.request.AuctionRequest;
import com.ey.dto.request.AuctionRescheduleRequest;
import com.ey.dto.response.AuctionResponse;
import com.ey.entity.Auction;

import jakarta.validation.Valid;

public interface AuctionService {

	AuctionResponse createAuction(@Valid AuctionRequest req);

	AuctionResponse getAuctionById(Long auctionId);

	List<AuctionResponse> getAllAuctions();
	AuctionResponse closeAuction(Long auctionId);
	List<Auction> getAllActiveAuctions();
	AuctionResponse rescheduleAuction(Long auctionId, AuctionRescheduleRequest req);
	List<AuctionResponse> getActiveAuctionsForBuyer();
	List<AuctionResponse> getAllAuctionsForBuyer();




}
