package com.ey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.BidRequest;
import com.ey.dto.response.BidResponse;
import com.ey.entity.Bid;
import com.ey.service.BidService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auctions")
public class BidController {
	@Autowired
	private BidService bidService;
	
    @PostMapping("/{auctionId}/bids")
	public ResponseEntity<BidResponse> placeBid(
			@PathVariable Long auctionId,@Valid @RequestBody BidRequest req){
		return new ResponseEntity<>(bidService.placeBid(auctionId , req),
				HttpStatus.CREATED);
	}
    @GetMapping("/{auctionId}/bids")
    public ResponseEntity<List<Bid>> getBidsByAuction(
            @PathVariable Long auctionId) {
        return ResponseEntity.ok(bidService.getBidsByAuction(auctionId));
    }
    @GetMapping("/{auctionId}/highest-bid")
    public ResponseEntity<Double> getHighestBid(
            @PathVariable Long auctionId) {

        return ResponseEntity.ok(
                bidService.getHighestBidForAuction(auctionId)
        );
    }

	

}
