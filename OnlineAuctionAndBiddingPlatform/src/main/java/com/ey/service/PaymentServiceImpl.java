package com.ey.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dto.request.PaymentRequest;
import com.ey.dto.response.PaymentResponse;
import com.ey.entity.Auction;
import com.ey.entity.Payment;
import com.ey.enums.AuctionStatus;
import com.ey.exception.AuctionNotFoundException;
import com.ey.exception.PaymentException;
import com.ey.mapper.PaymentMapper;
import com.ey.repository.AuctionRepository;
import com.ey.repository.PaymentRepository;

import jakarta.validation.Valid;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private  AuctionRepository auctionRepository;
	@Autowired
	private  PaymentRepository paymentRepository;
	@Autowired
	private  PaymentMapper paymentMapper;
	Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Override
	public PaymentResponse makePayment(Long auctionId, @Valid PaymentRequest req) {
		Auction auction = auctionRepository.findById(auctionId)
				.orElseThrow(() -> {
				log.error("Auction not found");	
				return new AuctionNotFoundException("Auction not found");});
		if (auction.getStatus() != AuctionStatus.COMPLETED) {
			log.error("Auction is not completed yet");
		    throw new PaymentException("Auction is not completed yet");
		}

		if(auction.getWinnerId() != null && !auction.getWinnerId().equals(req.getBuyerId())) {
			log.error("Only winner can make payment");
			throw new PaymentException("Only winner can make payment");
		}
		if (!req.getAmount().equals(auction.getHighestBid())) {
			log.error("Payment amount must be equal to winning bid amount");
		    throw new PaymentException(
		        "Payment amount must be equal to winning bid amount: " 
		        + auction.getHighestBid()
		    );
		}

		
		if(paymentRepository.findById(auctionId).isPresent()) {
			throw new PaymentException("Payment already completed for this auction");
		}
		Payment payment = paymentMapper.toEntity(req, auctionId);
		Payment savedPayment = paymentRepository.save(payment);
		return paymentMapper.toResponse(savedPayment);
	}
	@Override
	public PaymentResponse getPaymentById(Long paymentId) {
		 Payment payment = paymentRepository.findById(paymentId)
		            .orElseThrow(() -> new PaymentException("Payment not found"));
		    return paymentMapper.toResponse(payment);
	}
	@Override
	public PaymentResponse getPaymentByAuctionId(Long auctionId) {

	    Payment payment = paymentRepository.findByAuctionId(auctionId)
	            .orElseThrow(() ->
	                    new PaymentException(
	                            "Payment not found for auction id: " + auctionId) );
	    return paymentMapper.toResponse(payment);
	}

	

}
