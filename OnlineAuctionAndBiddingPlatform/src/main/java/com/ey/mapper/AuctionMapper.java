package com.ey.mapper;

import org.springframework.stereotype.Component;

import com.ey.dto.request.AuctionRequest;
import com.ey.dto.response.AuctionResponse;
import com.ey.entity.Auction;
import com.ey.enums.AuctionStatus;

@Component 
public class AuctionMapper {

    public Auction toEntity(AuctionRequest req) {
        Auction auction = new Auction();
        auction.setProductId(req.getProductId());
        auction.setAuctionDate(req.getAuctionDate());
        auction.setStartTime(req.getStartTime());
        auction.setEndTime(req.getEndTime());
        auction.setStatus(AuctionStatus.SCHEDULED);
        auction.setHighestBid(0.0);

        return auction;
    }

    public AuctionResponse toResponse(Auction auction) {
        AuctionResponse res = new AuctionResponse();
        res.setAuctionId(auction.getAuctionId()); // now safe
        res.setStatus(auction.getStatus());
        return res;
    }
}
