package com.cart_management.cart_management.exception;

@SuppressWarnings("serial")
public class NoDataFoundException extends RuntimeException {
	public NoDataFoundException(String message) {
		super(message);
	}
}
