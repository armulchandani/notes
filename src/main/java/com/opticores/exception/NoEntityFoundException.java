package com.opticores.exception;

public class NoEntityFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1367726030433891141L;
	
	public NoEntityFoundException(){
		super();
	}
	
	public NoEntityFoundException(String message){
		super(message);
	}

}
