package com.opticores.model;

/**
 * A model class responsible for capturing error messages and showing to the
 * client in an appropriate format
 * 
 * 
 * @author anubhav
 *
 */
public class ErrorMessage {

	// Application specific error code, should not be confused with the container specific which is a default handler if not handled at application level
	private String errorCode;
	
	// Application specific error message
	private String message;
	
	// A link for the client to visit to know more about an error which occurred
	private String documentLink;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDocumentLink() {
		return documentLink;
	}

	public void setDocumentLink(String documentLink) {
		this.documentLink = documentLink;
	}

}
