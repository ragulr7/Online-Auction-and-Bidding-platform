package com.ey.exception;

public class AuctionNotFoundException extends RuntimeException {
	public AuctionNotFoundException(String message) {
		super(message);
	}

}
