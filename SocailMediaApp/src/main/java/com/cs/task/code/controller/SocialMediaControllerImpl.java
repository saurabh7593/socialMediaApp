package com.cs.task.code.controller;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cs.task.code.exception.UserDetailsNotFoundException;
import com.cs.task.code.request.CreatePostRequest;
import com.cs.task.code.response.BaseResponse;
import com.cs.task.code.response.FolloweeResponse;
import com.cs.task.code.response.NewsFeedResponse;
import com.cs.task.code.service.SocialMediaService;


/**
 * @author Saurabh Gupta
 * implements the social media controller
 *
 */
@RestController
public class SocialMediaControllerImpl implements SocialMediaController {
	
	private static final Logger logger=LoggerFactory.getLogger(SocialMediaControllerImpl.class);
	
	@Autowired
	SocialMediaService socialMediaServiceImpl;
	
	private BaseResponse response=new BaseResponse();

	@Override
	@RequestMapping(value="/create/createPost",method= {RequestMethod.POST},consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponse> createPost(@RequestBody(required=true) @Valid CreatePostRequest request) {
		logger.info("Inside createPost Method");
		return socialMediaServiceImpl.createPost(request);
	}

	@Override
	@RequestMapping(value="/get/getNewsFeed/{userId}",method= {RequestMethod.GET},produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponse> getNewsFeed(@PathVariable(name="userId", required=true) @NotNull String userId) {
		logger.info("inside getNewsFeed method with userId {}",userId);		
		return  socialMediaServiceImpl.getNewsFeed(userId);
	}

	@Override
	@RequestMapping(value="/follow",method= {RequestMethod.GET},produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponse> follow(@RequestParam (name="followerId",required=true) String followerId,@RequestParam (name="followeeId",required=true) String followeeId) {
		logger.info("Inside follow Method");
		return socialMediaServiceImpl.follow(followerId, followeeId);
	}
	
	@Override
	@RequestMapping(value="/unFollow",method= {RequestMethod.GET},produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponse> unFollow(@RequestParam (name="followerId",required=true) String followerId,@RequestParam (name="followeeId",required=true) String followeeId) {
		logger.info("Inside Unfollow Method");		
		return socialMediaServiceImpl.unFollow(followerId, followeeId);
	}
}
