package com.cs.task.code.beans;

import java.util.List;
import java.util.Map;

/**
 * @author Saurabh Gupta
 *
 */
public class SocialUser {
	
	/**
	 * @param userId
	 * @param followList
	 * @param posts
	 */
	public SocialUser(String userId, List<String> followList, Map<String, String> posts) {
		super();
		this.userId = userId;
		this.followList = followList;
		this.posts = posts;
	}

	/**
	 * User Id 
	 * Also serves as follower Id
	 */
	private String userId;
	
	/**
	 * User Name 
	 */
	private String username;
	
	/**
	 * Follow List
	 * It includes all the user followed by this user Id
	 */
	private List<String> followList;
	
	/**
	 * List of posts by this user
	 */
	private Map<String,String> posts;
	

	/**
	 * @return the posts
	 */
	public Map<String,String> getPosts() {
		return posts;
	}

	/**
	 * @param posts the posts to set
	 */
	public void setPosts(Map<String,String> posts) {
		this.posts = posts;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getFollowList() {
		return followList;
	}

	public void setFollowList(List<String> followList) {
		this.followList = followList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((followList == null) ? 0 : followList.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SocialUser other = (SocialUser) obj;
		if (followList == null) {
			if (other.followList != null)
				return false;
		} else if (!followList.equals(other.followList))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	
}
