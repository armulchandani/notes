package com.opticores.exception;

/** An exception to denote that a request is invalid
 * 
 * 
 * @author anubhav
 *
 */
public class InvalidRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1766791558978783221L;
	
	
	public InvalidRequestException(){
		super("Either the resource is invalid or missing or request is invalid");
	}

}
