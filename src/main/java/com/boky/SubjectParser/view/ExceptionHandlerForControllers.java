package com.boky.SubjectParser.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.boky.SubjectParser.services.services.NoSuchSpecializationException;

@ControllerAdvice
public class ExceptionHandlerForControllers {
	final Logger LOGGER = LoggerFactory
			.getLogger(ExceptionHandlerForControllers.class);

	@ExceptionHandler(NullPointerException.class)
	public String nullPoninter(Exception exception) {
		LOGGER.error("Exception:", exception);
		return "error500";
	}

	@ExceptionHandler(NoSuchSpecializationException.class)
	public String noSuchSpecializationException(Exception exception) {
		LOGGER.error("Exception:", exception);
		return "error400";
	}

}
