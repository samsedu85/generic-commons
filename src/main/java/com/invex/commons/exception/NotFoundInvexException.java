/**
 * 
 */
package com.invex.commons.exception;

/**
 * @author EduSam
 *
 */
public class NotFoundInvexException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8570722910042367767L;

	private final boolean success;

	private final String message;

	private final int errorCode;

	public NotFoundInvexException(String message, int errorCode) {
		super(message);
		this.success = Boolean.FALSE;
		this.message = message;
		this.errorCode = errorCode;
	}

	public boolean getSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public int getErrorCode() {
		return errorCode;
	}

}
