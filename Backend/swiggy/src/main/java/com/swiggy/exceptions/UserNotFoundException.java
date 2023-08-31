package com.swiggy.exceptions;

public class UserNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4366378453772555030L;

	public UserNotFoundException(String str) {
		super(str);
	}

	public UserNotFoundException() {
		super();
	}
}
