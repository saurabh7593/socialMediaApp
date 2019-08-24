package com.cs.task.code.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	
	@Autowired
	SocialMediaService socialMediaServiceImpl;

	@Override
	@RequestMapping(value="/create/createPost",method= {RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponse> createPost(@RequestBody(required=true) @Valid CreatePostRequest request) {
		return socialMediaServiceImpl.createPost(request);
	}

	@Override
	@RequestMapping(value="/get/getNewsFeed/{userId}",method= {RequestMethod.GET},produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NewsFeedResponse> getNewsFeed(@PathVariable(name="userId", required=true) @NotNull String userId) {
		return socialMediaServiceImpl.getNewsFeed(userId);
	}

	@Override
	@RequestMapping(value="/follow",method= {RequestMethod.GET},produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponse> follow(@RequestParam (name="followerId",required=true) String followerId,@RequestParam (name="followeeId",required=true) String followeeId) {
		return socialMediaServiceImpl.follow(followerId, followeeId);
	}
	
	@Override
	@RequestMapping(value="/unFollow",method= {RequestMethod.GET},produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponse> unFollow(@RequestParam (name="followerId",required=true) String followerId,@RequestParam (name="followeeId",required=true) String followeeId) {
		return socialMediaServiceImpl.unFollow(followerId, followeeId);
	}
	
	@RequestMapping(value="/hello",method= {RequestMethod.GET})
	public String helloWorld(){
		return "Hi Saurabh";
	}

	/* (non-Javadoc)
	 * @see com.cs.task.code.controller.SocailMediaController#listOfFollowers(java.lang.String)
	 */
	@Override
	@RequestMapping(value="/listOfFollowee/{userId}",method= {RequestMethod.GET})
	public ResponseEntity<FolloweeResponse> listOfFollowers(@PathVariable(name="userId",required=true)String userId) {
		return socialMediaServiceImpl.listOfFollowee(userId);
	}

}
