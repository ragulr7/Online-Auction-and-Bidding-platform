package com.ey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.PaymentRequest;
import com.ey.dto.response.PaymentResponse;
import com.ey.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	@Autowired
	private  PaymentService paymentService;

	
	@PostMapping("/{auctionId}")
		public ResponseEntity<PaymentResponse> makePayment(@PathVariable Long auctionId,
				@Valid @RequestBody PaymentRequest req){
			return new ResponseEntity<>(paymentService.makePayment(auctionId , req),
					HttpStatus.CREATED);
	} 
	
	@GetMapping("/{paymentId}")
	public ResponseEntity<PaymentResponse> getPaymentById(
	        @PathVariable Long paymentId) {
	    return ResponseEntity.ok(paymentService.getPaymentById(paymentId));
	}
	@GetMapping("/auction/{auctionId}")
    public ResponseEntity<PaymentResponse> getPaymentByAuctionId(
            @PathVariable Long auctionId) {
        return ResponseEntity.ok(
                paymentService.getPaymentByAuctionId(auctionId)
        );
    }

	
}
