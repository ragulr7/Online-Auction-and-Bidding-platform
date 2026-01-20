package com.ey.exception;

public class ProductNotApprovedException extends RuntimeException {
	public ProductNotApprovedException(String message) {
		super(message);
	}

}
