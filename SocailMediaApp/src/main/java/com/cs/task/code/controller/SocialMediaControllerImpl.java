package com.cs.task.code.controller;

import java.awt.PageAttributes.MediaType;
import java.util.logging.Logger;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
public class SocialMediaControllerImpl implements SocailMediaController {
	
	private static final Logger logger=LoggerFactory.getLogger(SocialMediaControllerImpl.class);
	
	@Autowired
	SocialMediaService socialMediaServiceImpl;

	@Override
	@RequestMapping(value="/create/createPost",method= {RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponse> createPost(@RequestBody(required=true) @Valid CreatePostRequest request) {
		logger.info("Inside createPost Method");
		return socialMediaServiceImpl.createPost(request);
	}

	@Override
	@RequestMapping(value="/get/getNewsFeed/{userId}",method= {RequestMethod.GET},produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NewsFeedResponse> getNewsFeed(@PathVariable(name="userId", required=true) @NotNull String userId) {
		logger.info("Inside getNewsFeed Method");
		return socialMediaServiceImpl.getNewsFeed(userId);
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

	/* (non-Javadoc)
	 * @see com.cs.task.code.controller.SocailMediaController#listOfFollowers(java.lang.String)
	 */
	@Override
	@RequestMapping(value="/listOfFollowee/{userId}",method= {RequestMethod.GET})
	public ResponseEntity<FolloweeResponse> listOfFollowers(@PathVariable(name="userId",required=true)String userId) {
		logger.info("Inside listOfFollowers Method");
		return socialMediaServiceImpl.listOfFollowee(userId);
	}

}
