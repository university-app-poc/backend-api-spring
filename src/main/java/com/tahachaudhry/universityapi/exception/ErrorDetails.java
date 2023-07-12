package com.tahachaudhry.universityapi.exception;

import java.util.Date;

public class ErrorDetails {
	private int errorCode;
	private String message;
	private String details;

	public ErrorDetails(int errorCode, String message, String details) {
		super();
		this.errorCode = errorCode;
		this.message = message;
		this.details = details;
	}

	public int getCode() {
		return errorCode;
	}
	
	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
}
