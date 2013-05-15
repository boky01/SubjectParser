package com.boky.SubjectParser.services.services.error;

public class WebServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2170537142188012974L;

	public WebServiceException() {
		super();
	}

	public WebServiceException(String message) {
		super(message);
	}

	public WebServiceException(Throwable cause) {
		super(cause);
	}

	public WebServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public WebServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
