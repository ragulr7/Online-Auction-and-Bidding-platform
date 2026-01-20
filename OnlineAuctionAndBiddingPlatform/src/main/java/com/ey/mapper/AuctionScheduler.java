package com.ey.mapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ey.entity.Auction;
import com.ey.service.AuctionService;

@Component
public class AuctionScheduler {
	
	private final AuctionService auctionService;

	public AuctionScheduler(AuctionService auctionService) {
		super();
		this.auctionService = auctionService;
	}
	@Scheduled(fixedRate = 60000)
	public void autoCloseAuction() {
		List<Auction> auctions = auctionService.getAllActiveAuctions();
		LocalDate today = LocalDate.now();
		LocalTime now = LocalTime.now();
		
		for(Auction auction : auctions) {
			boolean isExpired=auction.getAuctionDate().isBefore(today) ||
					(auction.getAuctionDate().isEqual(today) 
							&& auction.getEndTime().isBefore(now));
			if(isExpired) {
				auctionService.closeAuction(auction.getAuctionId());
			}
		}
	}
	

}
