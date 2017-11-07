package com.example.loyalty.exception;

import com.example.loyalty.api.classes.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<GeneralResponse> exceptionHandler(Exception ex) {
		GeneralResponse error = new GeneralResponse();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorDescription("The request could not be understood by the server due to malformed syntax.");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
