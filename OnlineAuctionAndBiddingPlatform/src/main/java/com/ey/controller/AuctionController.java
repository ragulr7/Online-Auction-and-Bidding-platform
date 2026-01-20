package com.ey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.AuctionRequest;
import com.ey.dto.request.AuctionRescheduleRequest;
import com.ey.dto.response.AuctionResponse;
import com.ey.service.AuctionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/auctions")
public class AuctionController {
	@Autowired
    private AuctionService auctionService;


    @PostMapping
    public ResponseEntity<AuctionResponse> createAuction(
            @Valid @RequestBody AuctionRequest req) {
        return new ResponseEntity<>(
                auctionService.createAuction(req),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{auctionId}")
    public ResponseEntity<AuctionResponse> getAuctionById(
            @PathVariable Long auctionId) {
        return ResponseEntity.ok(
                auctionService.getAuctionById(auctionId)
        );
    }

    
    @GetMapping
    public ResponseEntity<List<AuctionResponse>> getAllAuctions() {
        return ResponseEntity.ok(
                auctionService.getAllAuctions()
        );
    }


    @PutMapping("/close/{auctionId}")
    public ResponseEntity<AuctionResponse> closeAuction(
            @PathVariable Long auctionId) {
        return ResponseEntity.ok(
                auctionService.closeAuction(auctionId)
        );
    }

    @PutMapping("/reschedule/{auctionId}")
    public ResponseEntity<AuctionResponse> rescheduleAuction(
            @PathVariable Long auctionId,
            @Valid @RequestBody AuctionRescheduleRequest req) {
        return ResponseEntity.ok(
                auctionService.rescheduleAuction(auctionId, req)
        );
    }
}
