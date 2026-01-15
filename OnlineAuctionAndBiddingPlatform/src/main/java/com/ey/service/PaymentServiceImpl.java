package com.ey.service;

import org.springframework.stereotype.Service;

import com.ey.dto.request.PaymentRequest;
import com.ey.dto.response.PaymentResponse;
import com.ey.entity.Auction;
import com.ey.entity.Payment;
import com.ey.mapper.PaymentMapper;
import com.ey.repository.AuctionRepository;
import com.ey.repository.PaymentRepository;

import jakarta.validation.Valid;

@Service
public class PaymentServiceImpl implements PaymentService {
	private final AuctionRepository auctionRepository;
	private final PaymentRepository paymentRepository;
	private final PaymentMapper paymentMapper;
	public PaymentServiceImpl(AuctionRepository auctionRepository, PaymentRepository paymentRepository,
			PaymentMapper paymentMapper) {
		super();
		this.auctionRepository = auctionRepository;
		this.paymentRepository = paymentRepository;
		this.paymentMapper = paymentMapper;
	}
	@Override
	public PaymentResponse makePayment(Long auctionId, @Valid PaymentRequest req) {
		Auction auction = auctionRepository.findById(auctionId)
				.orElseThrow(() -> new RuntimeException("Auction not found"));
		if(auction.getWinnerId() != null && !auction.getWinnerId().equals(req.getBuyerId())) {
			throw new RuntimeException("Only winner can make payment");
		}
		
		if(paymentRepository.findById(auctionId).isPresent()) {
			throw new RuntimeException("Payment already completed for this auction");
		}
		Payment payment = paymentMapper.toEntity(req, auctionId);
		Payment savedPayment = paymentRepository.save(payment);
		return paymentMapper.toResponse(savedPayment);
	}
	@Override
	public PaymentResponse getPaymentById(Long paymentId) {
		 Payment payment = paymentRepository.findById(paymentId)
		            .orElseThrow(() -> new RuntimeException("Payment not found"));
		    return paymentMapper.toResponse(payment);
	}
	

}
