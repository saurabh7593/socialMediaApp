/**
 * 
 */
package com.cs.task.code.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.cs.task.code.beans.SocialUser;
import com.cs.task.code.exception.UserDetailsNotFoundException;
import com.cs.task.code.repository.SocialMediaRepoImpl;
import com.cs.task.code.request.CreatePostRequest;
import com.cs.task.code.response.BaseResponse;
import com.cs.task.code.response.NewsFeedResponse;

/**
 * @author Saurabh Gupta
 * test classes for social media service class
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SocialMediaServiceTest  {
	
	@Autowired
	private SocialMediaServiceImpl socialMediaServiceImpl;
	
	
	@Autowired
	private SocialMediaRepoImpl socialMediaRepoImpl;
	
	CreatePostRequest request;
	
	Map<String,SocialUser> userDetailsMap=new LinkedHashMap<>();
	
	Map <String,String> postsMap=new LinkedHashMap<>();
	
	BaseResponse response= new BaseResponse();
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);	
		
		postsMap =new HashMap<>();
		postsMap.put(String.valueOf(Instant.now().toEpochMilli()).concat("ps123"), "Some content");
		List <String> followeeList =new ArrayList<>();
		followeeList.add("cs101");
		
		userDetailsMap.put("cs101", new SocialUser("cs101", followeeList, postsMap));
		userDetailsMap.put("cs102", new SocialUser("cs102", followeeList, postsMap));
		
		socialMediaRepoImpl.setuserDetailsMap(userDetailsMap);
		
		response.setResponseStatus(HttpStatus.OK);
        response.setResponseMessage("Post Created succesfully.");
	}


	/* (non-Javadoc)
	 * @see com.cs.task.code.service.SocialMediaService#createPost(com.cs.task.code.request.CreatePostRequest)
	 */
	@Test
	public void createPostTest() throws UserDetailsNotFoundException {
		CreatePostRequest request1=new CreatePostRequest();
		request1.setUserId("cs101");
		request1.setPostId("req1");
		request1.setContent("Hi This is new post");
		socialMediaServiceImpl.createPost(request1);
		assertTrue(socialMediaRepoImpl.getPostDetailsOfUser("cs101").containsValue("Hi This is new post"));
	}
	
	/* (non-Javadoc)
	 * @see com.cs.task.code.service.SocialMediaService#createPost(com.cs.task.code.request.CreatePostRequest)
	 */
	@Test
	public void createPostExceptionTest() throws UserDetailsNotFoundException {
		CreatePostRequest request1=new CreatePostRequest();
		ResponseEntity<BaseResponse> response1;
		request1.setUserId("randompostId");
		request1.setPostId("randompost");
		request1.setContent("random content");
		response1=socialMediaServiceImpl.createPost(request1);
		assertEquals(response1.getBody().getResponseMessage(), "User details not found, Creating new user. Please try again");
	}
		
	@Test
	public void getNewsFeedTest() throws UserDetailsNotFoundException {
		assertTrue(socialMediaServiceImpl.getNewsFeed("cs101").getBody().getUserData().containsValue("Some content"));
	}
	
	@Test(expected=UserDetailsNotFoundException.class)
	public void createNewsFeedTestException() throws UserDetailsNotFoundException {
		socialMediaRepoImpl.getPostDetailsOfUser("PS1234");
	}
	
	@Test
	public void checkFollowersNEwsFeedTest() throws UserDetailsNotFoundException {
		assertTrue(socialMediaRepoImpl.getFolloweeDetailsOfUser("cs101").size()==1);
		socialMediaServiceImpl.follow("cs101", "cs102");
		assertTrue(socialMediaRepoImpl.getFolloweeDetailsOfUser("cs101").size()==2);	
	}
	
	@Test
	public void checkTop20UserFeedTest() throws UserDetailsNotFoundException {
		for (int i = 0; i < 50; i++) {
			CreatePostRequest req = new CreatePostRequest();
			req.setUserId("auto");
			req.setPostId("autoPost".concat(String.valueOf(i)));
			req.setContent("Auto content");
			socialMediaServiceImpl.createPost(req);			
		}
		socialMediaServiceImpl.follow("auto", "cs101");
		socialMediaServiceImpl.follow("auto", "cs102");

		assertTrue(socialMediaServiceImpl.getNewsFeed("auto").getBody().getNumbrOfPosts()==20); //indicated we are getting top 20 posts from user

		assertFalse(socialMediaServiceImpl.getNewsFeed("auto").getBody().getUserData().containsValue("Some content"));

		
	}
 
	@Test(expected=UserDetailsNotFoundException.class)
    public void checkFollowCriteria () throws UserDetailsNotFoundException {
    	socialMediaServiceImpl.checkFollowCriteria("cs101", "cs101");
    }
	
	@Test
	public void followTest () throws UserDetailsNotFoundException {
		socialMediaServiceImpl.follow("cs101", "cs102");
		assertTrue(socialMediaRepoImpl.getFolloweeDetailsOfUser("cs101").contains("cs102"));
	}
	
	@Test
	public void unFollowTest () throws UserDetailsNotFoundException {
		socialMediaServiceImpl.follow("cs101", "cs102");
		assertTrue(socialMediaRepoImpl.getFolloweeDetailsOfUser("cs101").contains("cs102"));
		socialMediaServiceImpl.unFollow("cs101", "cs102");
		assertFalse(socialMediaRepoImpl.getFolloweeDetailsOfUser("cs101").contains("cs102"));		
	}
}
