package com.swiggy.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(SwiggyException.class)
	public ResponseEntity<ErrorDetails> swiggyExceptionHandler(SwiggyException sw, WebRequest we) {
		return new ResponseEntity<ErrorDetails>(
				new ErrorDetails(sw.getMessage(), LocalDateTime.now(), we.getDescription(false)),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetails> noHandlerFoundException(
			org.springframework.web.servlet.NoHandlerFoundException sw, WebRequest we) {
		return new ResponseEntity<ErrorDetails>(
				new ErrorDetails(sw.getMessage(), LocalDateTime.now(), we.getDescription(false)),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException sw,
			WebRequest we) {
		return new ResponseEntity<ErrorDetails>(
				new ErrorDetails(sw.getMessage(), LocalDateTime.now(), we.getDescription(false)),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> globalExceptionHandler(Exception sw, WebRequest we) {
		return new ResponseEntity<ErrorDetails>(
				new ErrorDetails(sw.getMessage(), LocalDateTime.now(), we.getDescription(false)),
				HttpStatus.BAD_REQUEST);
	}
}
