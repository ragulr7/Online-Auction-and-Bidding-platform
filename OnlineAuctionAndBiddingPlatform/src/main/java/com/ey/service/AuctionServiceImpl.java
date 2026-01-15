package com.ey.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ey.dto.request.AuctionRequest;
import com.ey.dto.response.AuctionResponse;
import com.ey.entity.Auction;
import com.ey.mapper.AuctionMapper;
import com.ey.repository.AuctionRepository;

import jakarta.validation.Valid;
@Service
public class AuctionServiceImpl implements AuctionService {
	private final AuctionRepository auctionRepository;
	private final AuctionMapper auctionMapper;
	public AuctionServiceImpl(AuctionRepository auctionRepository, AuctionMapper auctionMapper) {
		super();
		this.auctionRepository = auctionRepository;
		this.auctionMapper = auctionMapper;
	}
	@Override
	public AuctionResponse createAuction(@Valid AuctionRequest req) {
		Auction auction = auctionMapper.toEntity(req);
		Auction savedAuction = auctionRepository.save(auction);
		return auctionMapper.toResponse(savedAuction);
	}
	@Override
	public AuctionResponse getAuctionById(Long auctionId) {

	    Auction auction = auctionRepository.findById(auctionId)
	            .orElseThrow(() -> new RuntimeException("Auction not found"));
	    return auctionMapper.toResponse(auction);
	}
	@Override
	public List<AuctionResponse> getAllAuctions() {
		return auctionRepository.findAll()
	            .stream()
	            .map(auctionMapper::toResponse)
	            .toList();
	}


 
}
