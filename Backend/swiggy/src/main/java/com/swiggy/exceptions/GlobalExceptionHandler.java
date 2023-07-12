package com.swiggy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(SwiggyException.class)
	public ResponseEntity<String> swiggyExceptionHandler(SwiggyException sw) {
		return new ResponseEntity<>(sw.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
	public ResponseEntity<String> noHandlerFoundException(org.springframework.web.servlet.NoHandlerFoundException sw) {
		return new ResponseEntity<>(sw.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException sw) {
		return new ResponseEntity<>(sw.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> globalExceptionHandler(Exception sw) {
		return new ResponseEntity<>(sw.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
