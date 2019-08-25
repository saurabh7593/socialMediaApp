/**
 * 
 */
package com.cs.task.code.exception;

/**
 * @author ethan
 *
 */
public class UserNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public UserNotFoundException(String message) {
		super(message);
	}

}
