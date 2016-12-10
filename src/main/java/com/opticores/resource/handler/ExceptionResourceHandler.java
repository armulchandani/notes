package com.opticores.resource.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.opticores.exception.UserNotFoundException;
import com.opticores.model.ErrorMessage;

/**
 * A new global handler to handle all the exceptions thrown by other resource
 * handlers/controllers.
 * 
 * A handler/controller should not contain any exception handling method making
 * it follow SRP.
 * 
 * 
 * @author anubhav
 *
 */
@ControllerAdvice
public class ExceptionResourceHandler {

	private Logger LOGGER = LoggerFactory
			.getLogger(ExceptionResourceHandler.class);

	/**
	 * A handler to catch if a user is not found and an exception and send
	 * appropriate response to the caller/client
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleUserNotFound(
			UserNotFoundException ex) {

		final String METHOD_NAME = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		LOGGER.info("Entering method '{}' to handle error",METHOD_NAME);
		
		LOGGER.error("An error occured", ex);

		ErrorMessage errorMessage = new ErrorMessage();
		StringBuilder builder = new StringBuilder("User not found: ");
		errorMessage.setMessage(builder.toString());
		
		LOGGER.info("Exiting method '{}' , prepared response and sending it to the caller",METHOD_NAME);

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);

	}

	/**
	 * A handler to catch all exception and send appropriate response to the
	 * caller/client
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleAllException(Exception ex) {
		
		final String METHOD_NAME = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		LOGGER.info("Entering method '{}' to handle error",METHOD_NAME);
		
		LOGGER.error("An error occured", ex);

		ErrorMessage errorMessage = new ErrorMessage();
		StringBuilder builder = new StringBuilder(
				"Some occured while performing operation : ");
		builder.append(ex.getLocalizedMessage());
		errorMessage.setMessage(builder.toString());
		
		LOGGER.info("Exiting method '{}' , prepared response and sending it to the caller",METHOD_NAME);

		return new ResponseEntity<>(errorMessage,
				HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
