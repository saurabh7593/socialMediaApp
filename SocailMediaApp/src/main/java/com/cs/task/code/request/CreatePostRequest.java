package com.cs.task.code.request;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.lang.NonNull;

import com.cs.task.code.beans.SocialUser;

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

}
