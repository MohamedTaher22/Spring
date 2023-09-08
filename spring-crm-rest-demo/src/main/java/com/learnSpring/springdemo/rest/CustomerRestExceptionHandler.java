package com.learnSpring.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {

	// Add an exception handler for CustomerNotFoundException
	@ExceptionHandler
	public ResponseEntity<CustomerErrorRresponse> handleException(CustomerNotFoundException exception){
		
		// create CustomerErrorResponse
		CustomerErrorRresponse error = new CustomerErrorRresponse(
									HttpStatus.NOT_FOUND.value(),
									exception.getMessage(),
									System.currentTimeMillis()
									);
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	// Add another exception handler ... to catch ant exception (catch all)
	@ExceptionHandler
	public ResponseEntity<CustomerErrorRresponse> handleException(Exception exception){
		
		// create CustomerErrorResponse
		CustomerErrorRresponse error = new CustomerErrorRresponse(
									HttpStatus.BAD_REQUEST.value(),
									exception.getMessage(),
									System.currentTimeMillis()
									);
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
