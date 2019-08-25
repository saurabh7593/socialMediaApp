/**
 * 
 */
package com.cs.task.code.service;

import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.cs.task.code.repository.SocailMediaRepo;
import com.cs.task.code.request.CreatePostRequest;
import com.cs.task.code.response.BaseResponse;
import com.cs.task.code.response.FolloweeResponse;
import com.cs.task.code.response.NewsFeedResponse;

/**
 * @author Saurabh Gupta
 * test classes for social media service class
 *
 */

public class SocialMediaServiceTest  {
	private static final Logger logger=LoggerFactory.getLogger(SocialMediaServiceTest.class);
		
	@Autowired
	private BaseResponse response;
	
	@Autowired
	private SocailMediaRepo socailMediaRepoImpl;


	/* (non-Javadoc)
	 * @see com.cs.task.code.service.SocialMediaService#createPost(com.cs.task.code.request.CreatePostRequest)
	 */
	public ResponseEntity<BaseResponse> createPost(CreatePostRequest request) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cs.task.code.service.SocialMediaService#getNewsFeed(int)
	 */
	public ResponseEntity<NewsFeedResponse> getNewsFeed(String userId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cs.task.code.service.SocialMediaService#follow(int, int)
	 */
	public ResponseEntity<BaseResponse> follow(String followerId, String followeeId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cs.task.code.service.SocialMediaService#unFollow(int, int)
	 */
	public ResponseEntity<BaseResponse> unFollow(String followerId, String followeeId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cs.task.code.service.SocialMediaService#listOfFollowee(java.lang.String)
	 */
	public ResponseEntity<FolloweeResponse> listOfFollowee(String userId) {
		return null;
	}

}
