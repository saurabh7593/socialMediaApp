package com.cs.task.code.controller;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cs.task.code.request.CreatePostRequest;
import com.cs.task.code.response.BaseResponse;
import com.cs.task.code.response.FolloweeResponse;
import com.cs.task.code.response.NewsFeedResponse;
import com.cs.task.code.util.AppConstants;

/**
 * Interface that defines all the end points interacting to Social media application  
 * @author Saurabh Gupta
 *
 */

@RequestMapping(value=AppConstants.Context_Path,produces=MediaType.APPLICATION_JSON_VALUE)
public interface SocailMediaController {
	
	/**
	 * creates the post by taking below parameters
	 * @param userId
	 * @param postId
	 * @param content
	 * @return
	 */
	ResponseEntity<BaseResponse> createPost(CreatePostRequest request);
	
	/**
	 * returns the top 20 post created by particular userId
	 * @param userId
	 * @return
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
	 */
	ResponseEntity<BaseResponse> unFollow(String followerId,String followeeId);
	
	/**
	 ** returns List of followee user follows
	 * @param followerId
	 * @param followeeId
	 * @return
	 */
	ResponseEntity<FolloweeResponse> listOfFollowers(String userId);


}
