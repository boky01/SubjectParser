package com.boky.SubjectParser.services.services.error;

public class EditServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2170537142188012974L;

	public EditServiceException() {
		super();
	}

	public EditServiceException(String message) {
		super(message);
	}

	public EditServiceException(Throwable cause) {
		super(cause);
	}

	public EditServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public EditServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
