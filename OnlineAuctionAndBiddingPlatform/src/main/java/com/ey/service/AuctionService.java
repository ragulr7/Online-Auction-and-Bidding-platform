package com.ey.service;

import java.util.List;

import com.ey.dto.request.AuctionRequest;
import com.ey.dto.response.AuctionResponse;

import jakarta.validation.Valid;

public interface AuctionService {

	AuctionResponse createAuction(@Valid AuctionRequest req);

	AuctionResponse getAuctionById(Long auctionId);

	List<AuctionResponse> getAllAuctions();

}
