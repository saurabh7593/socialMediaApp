package com.cs.task.code.response;

import org.springframework.http.HttpStatus;

/**
 * @author Saurabh Gupta
 *
 */
public class BaseResponse {
	
	/**
	 * represents the response
	 */
	private HttpStatus responseStatus;
	
	/**
	 *returns the response message 
	 */
	private String responseMessage;

	/**
	 * @return the responseStatus
	 */
	public HttpStatus getResponseStatus() {
		return responseStatus;
	}

	/**
	 * @param responseStatus the responseStatus to set
	 */
	public void setResponseStatus(HttpStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	/**
	 * @return the responseMessage
	 */
	public String getResponseMessage() {
		return responseMessage;
	}

	/**
	 * @param responseMessage the responseMessage to set
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
}
