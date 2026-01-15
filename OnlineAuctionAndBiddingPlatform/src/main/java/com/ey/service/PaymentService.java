package com.ey.service;

import com.ey.dto.request.PaymentRequest;
import com.ey.dto.response.PaymentResponse;

import jakarta.validation.Valid;

public interface PaymentService {

	PaymentResponse makePayment(Long auctionId, @Valid PaymentRequest req);

	PaymentResponse getPaymentById(Long paymentId);

}
