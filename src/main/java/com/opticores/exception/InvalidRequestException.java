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
	
	/** Default constructor
	 * 
	 */
	public InvalidRequestException(){
		super();
	}
	
	
	/** Parameterized constructor
	 * 
	 * @param message
	 */
	public InvalidRequestException(String message){
		super(message);
	}

}
