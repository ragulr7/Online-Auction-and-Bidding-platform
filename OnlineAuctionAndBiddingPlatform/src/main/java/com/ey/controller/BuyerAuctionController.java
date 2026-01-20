package com.ey.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.response.AuctionResponse;
import com.ey.service.AuctionService;

@RestController
@RequestMapping("/auctions/buyer")
public class BuyerAuctionController {
	@Autowired
    private  AuctionService auctionService;



    @GetMapping("/active")
    public ResponseEntity<List<AuctionResponse>> getActiveAuctionsForBuyer() {
        return ResponseEntity.ok(
                auctionService.getActiveAuctionsForBuyer()
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<AuctionResponse>> getAllAuctionsForBuyer() {
        return ResponseEntity.ok(
                auctionService.getAllAuctionsForBuyer()
        );
    }
}

