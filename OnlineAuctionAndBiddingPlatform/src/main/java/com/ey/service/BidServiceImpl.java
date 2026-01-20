package com.ey.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dto.request.BidRequest;
import com.ey.dto.response.BidResponse;
import com.ey.entity.Auction;
import com.ey.entity.Bid;
import com.ey.entity.Product;
import com.ey.entity.User;
import com.ey.enums.ApprovalStatus;
import com.ey.enums.AuctionStatus;
import com.ey.enums.Role;
import com.ey.exception.AuctionNotFoundException;
import com.ey.exception.ProductNotFoundException;
import com.ey.exception.UserNotFoundException;
import com.ey.mapper.BidMapper;
import com.ey.repository.AuctionRepository;
import com.ey.repository.BidRepository;
import com.ey.repository.ProductRepository;
import com.ey.repository.UserRepository;


@Service
public class BidServiceImpl implements BidService {
	@Autowired
	private  AuctionRepository auctionRepository;
	@Autowired
	private  BidRepository bidRepository;
	@Autowired
	private  BidMapper bidMapper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	Logger log = LoggerFactory.getLogger(UserService.class);

	@Override
	public BidResponse placeBid(Long auctionId, BidRequest req) {
		Auction auction = auctionRepository.findById(auctionId)
				.orElseThrow(()-> {
				log.error("Auction not found");	
				return new AuctionNotFoundException("Auction not found");});
	Double currentHighest = auction.getHighestBid();
	

	Product product = productRepository.findById(auction.getProductId())
	        .orElseThrow(() -> {
	        log.error("Product not found");	
	        return new ProductNotFoundException("Product not found");});

	if (product.getApprovalStatus() != ApprovalStatus.APPROVED) {
		log.error("Bidding not allowed for unapproved product");
	    throw new RuntimeException("Bidding not allowed for unapproved product");
	}

	
	 User user = userRepository.findById(req.getBuyerId())
	            .orElseThrow(() ->{
	            log.error("User not found");
	            return new UserNotFoundException("User not found");});
	 
	
	  if (user.getRole() != Role.BUYER) {
		  log.error("Only BUYER is allowed to place bids");
	        throw new RuntimeException("Only BUYER is allowed to place bids");
	    }
	
	if(req.getAmount() <= currentHighest) {
		log.error("Bid amount must be greater than current highest bid");
		throw new RuntimeException(
				"Bid amount must be greater than current highest bid");
	}
	if (auction.getStatus() != AuctionStatus.SCHEDULED) {
		log.error("Bidding is closed for this auction");
	    throw new RuntimeException("Bidding is closed for this auction");
	}
	if (!"ACTIVE".equals(user.getStatus())) { 
		log.error("Please login to place bids");
	    throw new RuntimeException("Please login to place bids");
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
	@Override
	public Double getHighestBidForAuction(Long auctionId) {
	    List<Bid> bids = bidRepository.findByAuctionId(auctionId);
	    if (bids.isEmpty()) {
	        throw new RuntimeException("No bids found for this auction");
	    }
	    return bids.stream()
	            .map(Bid::getAmount)
	            .max(Double::compareTo)
	            .get();
	}



}
