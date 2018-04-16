package com.nic.deputation.customexception;

public class DefaultException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -112901231498785014L;
	private String message;

	public DefaultException(String message, String name) {
		this.message = message;
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;
}
