package com.ey.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ey.dto.request.BidRequest;
import com.ey.dto.response.BidResponse;
import com.ey.entity.Auction;
import com.ey.entity.Bid;
import com.ey.mapper.BidMapper;
import com.ey.repository.AuctionRepository;
import com.ey.repository.BidRepository;

@Service
public class BidServiceImpl implements BidService {
	
	private final AuctionRepository auctionRepository;
	private final BidRepository bidRepository;
	private final BidMapper bidMapper;
	public BidServiceImpl(AuctionRepository auctionRepository, BidRepository bidRepository, BidMapper bidMapper) {
		super();
		this.auctionRepository = auctionRepository;
		this.bidRepository = bidRepository;
		this.bidMapper = bidMapper;
	}
	@Override
	public BidResponse placeBid(Long auctionId, BidRequest req) {
		Auction auction = auctionRepository.findById(auctionId)
				.orElseThrow(()-> new RuntimeException("Auction not found"));
	Double currentHighest = auction.getHighestBid();
	
	if(req.getAmount() <= currentHighest) {
		throw new RuntimeException(
				"Bid amount must be greater than current highest bid");
	}
	Bid bid = bidMapper.toEntity(req,auctionId);
	Bid savedBid = bidRepository.save(bid);
	
	auction.setHighestBid(req.getAmount());
	auctionRepository.save(auction);
	
	BidResponse response = new BidResponse();
	response.setBidId(savedBid.getBidId());
	response.setCurrentHighestBid(auction.getHighestBid());
	return response;
	}
	
	@Override
	public List<Bid> getBidsByAuction(Long auctionId) {
	    return bidRepository.findByAuctionId(auctionId);
	}


}
