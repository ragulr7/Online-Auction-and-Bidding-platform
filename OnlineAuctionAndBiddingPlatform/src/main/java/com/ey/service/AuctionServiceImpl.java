package com.ey.service;

import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dto.request.AuctionRequest;
import com.ey.dto.request.AuctionRescheduleRequest;
import com.ey.dto.response.AuctionResponse;
import com.ey.entity.Auction;
import com.ey.entity.Bid;
import com.ey.entity.Product;
import com.ey.enums.ApprovalStatus;
import com.ey.enums.AuctionStatus;
import com.ey.exception.AuctionNotFoundException;
import com.ey.exception.ExpectedPriceException;
import com.ey.exception.NoBidException;
import com.ey.exception.ProductNotApprovedException;
import com.ey.exception.ProductNotFoundException;
import com.ey.mapper.AuctionMapper;
import com.ey.repository.AuctionRepository;
import com.ey.repository.BidRepository;
import com.ey.repository.ProductRepository;

import jakarta.validation.Valid;
@Service
public class AuctionServiceImpl implements AuctionService {
	@Autowired
	private  AuctionRepository auctionRepository;
	@Autowired
	private  ProductRepository productRepository;
	@Autowired
	private  AuctionMapper auctionMapper;
	@Autowired
	private  BidRepository bidRepository;
	Logger log = LoggerFactory.getLogger(UserService.class);

	@Override
	public AuctionResponse createAuction(@Valid AuctionRequest req) {
		Product product = productRepository.findById(req.getProductId())
		        .orElseThrow(() -> {
		        log.error("Product not found");	
		        return new ProductNotFoundException("Product not found");});

		if (product.getApprovalStatus() != ApprovalStatus.APPROVED) {
			log.error("Product is not approved for auction");
		    throw new ProductNotApprovedException("Product is not approved for auction");
		}

		Auction auction = auctionMapper.toEntity(req);
		Auction savedAuction = auctionRepository.save(auction);
		return auctionMapper.toResponse(savedAuction);
	}
	@Override
	public AuctionResponse getAuctionById(Long auctionId) {

	    Auction auction = auctionRepository.findById(auctionId)
	            .orElseThrow(() -> {
	            log.error("Auction not found");
	            return new AuctionNotFoundException("Auction not found");});
	    return auctionMapper.toResponse(auction);
	}
	@Override
	public List<AuctionResponse> getAllAuctions() {
		return auctionRepository.findAll()
	            .stream()
	            .map(auctionMapper::toResponse)
	            .toList();
	}
	@Override
	public AuctionResponse closeAuction(Long auctionId) {
		Auction auction = auctionRepository.findById(auctionId)
				.orElseThrow(() -> {
				log.error("Auction not found");	
				return new AuctionNotFoundException("Auction not found");});
		Product product = productRepository.findById(auction.getProductId())
				.orElseThrow(() -> {
				log.error("Product not found");
				return new ProductNotFoundException("Product not found");});
		List<Bid> bids = bidRepository.findByAuctionId(auctionId);
		if(bids.isEmpty()) {
			auction.setStatus(AuctionStatus.FAILED);
			auctionRepository.save(auction);
			log.error("No Bids are placed");
			throw new NoBidException("No bids are placed");
		}
		Bid highestBid = bids.stream()
				.max(Comparator.comparing(Bid::getAmount)).get();
		if(highestBid.getAmount() <product.getExpectedPrice()) {
			auction.setStatus(AuctionStatus.FAILED);
			auctionRepository.save(auction);
			log.error("Expected Price not met");
			throw new ExpectedPriceException("Expected Price not met!");
		}
		auction.setWinnerId(highestBid.getBuyerId());
		auction.setHighestBid(highestBid.getAmount());
		auction.setStatus(AuctionStatus.COMPLETED);
		auctionRepository.save(auction);
		
		return auctionMapper.toResponse(auction);
	}
	@Override
	public List<Auction> getAllActiveAuctions() {
		return auctionRepository.findByStatus(AuctionStatus.SCHEDULED);
	}
	@Override
	public AuctionResponse rescheduleAuction(Long auctionId, AuctionRescheduleRequest req) {

	    Auction auction = auctionRepository.findById(auctionId)
	            .orElseThrow(() -> {
	            log.error("Auction not found");	
	            return new AuctionNotFoundException("Auction not found");});
	    if (auction.getStatus() != AuctionStatus.FAILED) {
	        throw new IllegalStateException(
	                "Only FAILED auctions can be rescheduled"
	        );
	    }
	    auction.setAuctionDate(req.getAuctionDate());
	    auction.setStartTime(req.getStartTime());
	    auction.setEndTime(req.getEndTime());
	    auction.setStatus(AuctionStatus.SCHEDULED); 
	    auction.setHighestBid(0.0);
	    auction.setWinnerId(null);

	    auctionRepository.save(auction);

	    return auctionMapper.toResponse(auction);
	}
	@Override
	public List<AuctionResponse> getActiveAuctionsForBuyer() {
	    return auctionRepository
	            .findByStatusIn(
	                    List.of(AuctionStatus.SCHEDULED)
	            )
	            .stream()
	            .map(auctionMapper::toResponse)
	            .toList();
	}

	@Override
	public List<AuctionResponse> getAllAuctionsForBuyer() {
	    return auctionRepository.findAll()
	            .stream()
	            .map(auctionMapper::toResponse)
	            .toList();
	}


	}



 

