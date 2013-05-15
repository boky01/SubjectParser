package com.boky.SubjectParser.services.services.error;

public class NoSuchSpecializationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3626793803364102412L;
	private String nonExistingSpecialization;

	public NoSuchSpecializationException(String message) {
		super("There is no specialization with name: " + message);
		nonExistingSpecialization = message;

	}

	public String getNonExistingSpecialization() {
		return nonExistingSpecialization;
	}

	public void setNonExistingSpecialization(String nonExistingSpecialization) {
		this.nonExistingSpecialization = nonExistingSpecialization;
	}
}
