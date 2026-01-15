package com.ey.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.AuctionRequest;
import com.ey.dto.response.AuctionResponse;
import com.ey.service.AuctionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/auctions")
public class AuctionController {
	private final AuctionService auctionService;

	public AuctionController(AuctionService auctionService) {
		super();
		this.auctionService = auctionService;
	}
	
	@PostMapping
	public ResponseEntity<AuctionResponse> createAuction(@Valid @RequestBody
			AuctionRequest req){
		return new ResponseEntity<> (auctionService.createAuction(req),HttpStatus.CREATED);
	}
	@GetMapping("/{auctionId}")
	public ResponseEntity<AuctionResponse> getAuctionById(
	        @PathVariable Long auctionId) {
	    return ResponseEntity.ok(auctionService.getAuctionById(auctionId)
	    );
	}
	@GetMapping
	public ResponseEntity<List<AuctionResponse>> getAllAuctions() {
	    return ResponseEntity.ok( auctionService.getAllAuctions()
	    );
	}


}
