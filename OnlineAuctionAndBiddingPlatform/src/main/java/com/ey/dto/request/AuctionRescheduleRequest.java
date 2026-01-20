package com.ey.dto.request;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public class AuctionRescheduleRequest {

    @NotNull
    @FutureOrPresent
    private LocalDate auctionDate;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

	public LocalDate getAuctionDate() {
		return auctionDate;
	}

	public void setAuctionDate(LocalDate auctionDate) {
		this.auctionDate = auctionDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

    
}

