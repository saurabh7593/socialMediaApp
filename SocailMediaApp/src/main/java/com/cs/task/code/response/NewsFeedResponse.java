/**
 * 
 */
package com.cs.task.code.response;

import java.util.Map;

/**
 * @author Saurabh Gupta
 *
 */
public class NewsFeedResponse extends BaseResponse {
	
	/**
	 * returns the map with user id and User Data
	 */
	private Map<String,String> userData;
	

	/**
	 * @return the userData
	 */
	public Map<String, String> getUserData() {
		return userData;
	}

	/**
	 * @param map the userData to set
	 */
	public void setUserData(Map<String, String> map) {
		this.userData = map;
	}
}
