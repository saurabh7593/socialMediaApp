package com.cs.task.code.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cs.task.code.exception.UserDetailsNotFoundException;
import com.cs.task.code.request.CreatePostRequest;
import com.cs.task.code.response.BaseResponse;
import com.cs.task.code.response.NewsFeedResponse;

/**
 * @author Saurabh Gupta
 *
 */
public interface SocialMediaService {
	
	/**
	 * creates the post by taking below parameters
	 * @param userId
	 * @param postId
	 * @param content
	 * @return
	 * @throws UserDetailsNotFoundException 
	 */
	ResponseEntity<BaseResponse> createPost(CreatePostRequest request);
	
	/**
	 * returns the top 20 post created by particular userId
	 * @param userId
	 * @return
	 * @throws UserDetailsNotFoundException 
	 */
	ResponseEntity<NewsFeedResponse> getNewsFeed(String userId);
	
	/**
	 * returns success response if a user id foollows another successfully
	 * @param followerId
	 * @param followeeId
	 * @return
	 */
	ResponseEntity<BaseResponse> follow(String followerId,String followeeId);
	
	/**
	 ** returns success response if a user id follows another successfully
	 * @param followerId
	 * @param followeeId
	 * @return
	 * @throws UserDetailsNotFoundException 
	 */
	ResponseEntity<BaseResponse> unFollow(String followerId,String followeeId);

}
