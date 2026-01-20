package com.ey.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
 
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex){
		Map<String,List<String>>body=new HashMap<>();
		List<String> errors=ex.getBindingResult()
							.getFieldErrors()
							.stream()
							.map(DefaultMessageSourceResolvable::getDefaultMessage)
							.collect(Collectors.toList());
		body.put("errors",errors);
		return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<Object> handleEmailNotFoundException(EmailNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(AuctionNotFoundException.class)
	public ResponseEntity<Object> handleAuctionNotFoundException(AuctionNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(WrongPasswordException.class)
	public ResponseEntity<Object> handleWrongPasswordException(WrongPasswordException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ProductNotApprovedException.class)
	public ResponseEntity<Object> handleProductNotApprovedException(ProductNotApprovedException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NoBidException.class)
	public ResponseEntity<Object> handleNoBidException(NoBidException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ExpectedPriceException.class)
	public ResponseEntity<Object> handleExpectedPriceException(ExpectedPriceException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(PaymentException.class)
	public ResponseEntity<Object> handlePaymentException(PaymentException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> hanldeException(Exception ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
}