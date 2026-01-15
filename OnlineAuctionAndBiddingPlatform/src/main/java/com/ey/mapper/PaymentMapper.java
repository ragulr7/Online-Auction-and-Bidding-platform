package com.ey.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.ey.dto.request.PaymentRequest;
import com.ey.dto.response.PaymentResponse;
import com.ey.entity.Payment;
import com.ey.enums.PaymentStatus;

@Component
public class PaymentMapper {
	public Payment toEntity(PaymentRequest req , Long auctionId) {
		Payment payment = new Payment();
		payment.setAuctionId(auctionId);
		payment.setBuyerId(req.getBuyerId());
		payment.setAmount(req.getAmount());
		payment.setMethod(req.getMethod());
		payment.setStatus(PaymentStatus.COMPLETED);
		payment.setPaymentTime(LocalDateTime.now());
		return payment;
	}
	public PaymentResponse toResponse(Payment payment) {
		PaymentResponse res = new PaymentResponse();
		res.setPaymentId(payment.getPaymentId());
		res.setAuctionId(payment.getAuctionId());
		res.setAmount(payment.getAmount());
		res.setStatus(payment.getStatus());
		return res;
	}

}
