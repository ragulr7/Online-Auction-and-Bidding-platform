package com.ey.service;

import org.jspecify.annotations.Nullable;

import com.ey.dto.request.PaymentRequest;
import com.ey.dto.response.PaymentResponse;

import jakarta.validation.Valid;

public interface PaymentService {

	PaymentResponse makePayment(Long auctionId, @Valid PaymentRequest req);

	PaymentResponse getPaymentById(Long paymentId);

	PaymentResponse getPaymentByAuctionId(Long paymentId);

}
