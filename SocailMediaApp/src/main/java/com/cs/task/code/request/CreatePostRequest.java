package com.cs.task.code.request;

import javax.validation.constraints.NotNull;

/**
 * @author Saurabh Gupta
 *
 */
public class CreatePostRequest {
	
	/**
	 * user Id
	 */
	@NotNull
	private String userId;
	
	/**
	 *post Id
	 */
	@NotNull
	private String postId;
	
	/**
	 * Content of the Post
	 */
	@NotNull
	private String content;
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CreatePostRequest [userId=" + userId + ", postId=" + postId + ", content=" + content + "]";
	}

}
