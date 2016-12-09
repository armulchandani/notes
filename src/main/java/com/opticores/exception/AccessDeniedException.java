package com.opticores.exception;

/**
 * An exception to denote that user/client has been denied the requested
 * operation
 * 
 * 
 * @author anubhav
 *
 */
public class AccessDeniedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7631849068795022874L;

	public AccessDeniedException() {
		super("Access denied!!!");

	}

}
