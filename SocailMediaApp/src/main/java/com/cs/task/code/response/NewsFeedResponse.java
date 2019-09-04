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
	 * Indicates the number of post by a user
	 */
	
	private int numbrOfPosts;
	

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

	public int getNumbrOfPosts() {
		return numbrOfPosts;
	}

	public void setNumbrOfPosts(int numbrOfPosts) {
		this.numbrOfPosts = numbrOfPosts;
	}
}
