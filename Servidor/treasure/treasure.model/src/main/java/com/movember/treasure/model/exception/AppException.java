package com.movember.treasure.model.exception;


/**
 * The Class AppException.
 */
public class AppException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new app exception.
	 */
	public AppException() {
		super();
	}

	/**
	 * Instantiates a new app exception.
	 * 
	 * @param message
	 *            the message
	 */
	public AppException(String message) {
		super(message);
	}
}