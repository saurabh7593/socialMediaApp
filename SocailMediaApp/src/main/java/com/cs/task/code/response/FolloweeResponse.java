/**
 * 
 */
package com.cs.task.code.response;

import java.util.List;

/**
 * @author Saurabh Gupta
 *
 */
public class FolloweeResponse extends BaseResponse {
	
	/**
	 * returns the map with user id and User Data
	 */
	private List<String> followeeData;

	/**
	 * @return the followeeData
	 */
	public List<String> getFolloweeData() {
		return followeeData;
	}

	/**
	 * @param followeeData the followeeData to set
	 */
	public void setFolloweeData(List<String> followeeData) {
		this.followeeData = followeeData;
	}
}
