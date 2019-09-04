package com.cs.task.code.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cs.task.code.request.CreatePostRequest;
import com.cs.task.code.response.BaseResponse;
import com.cs.task.code.response.FolloweeResponse;
import com.cs.task.code.response.NewsFeedResponse;
import com.cs.task.code.service.SocialMediaServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Saurabh Gupta
 * implements the social media controller Tests
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SocialMediaControllerTest  {
	
	
		
	@InjectMocks
	SocialMediaControllerImpl socialMediaControllerImpl;
	
	@Mock
	SocialMediaServiceImpl socialMediaServiceImpl;
	
	CreatePostRequest request;
	
	BaseResponse response;
	
	NewsFeedResponse newsFeedResponse;
	
	FolloweeResponse followeeResponse;
	
	
	ResponseEntity<BaseResponse> baseResp;
	
	ResponseEntity<NewsFeedResponse> newsFeedResp;
		
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);

		request=new CreatePostRequest();
		request.setUserId("sd212");
		request.setPostId("ps123");
		request.setContent("Hi Tis is conent");
		
	}
	
	
	  @Test
	  public void createPostTest() throws Exception{
			when(socialMediaControllerImpl.createPost(request)).thenReturn(baseResp);
			assertEquals(socialMediaServiceImpl.createPost(request), baseResp);
	  }

	@Test
	public void getNewsFeedTest() throws Exception {
		when(socialMediaControllerImpl.getNewsFeed("randomId")).thenReturn(newsFeedResp);
		assertEquals(socialMediaServiceImpl.getNewsFeed("randomId"), newsFeedResp);
		
	}
	
	@Test
	public void followTest() throws Exception {
		when(socialMediaControllerImpl.unFollow("auto1", "auto2")).thenReturn(baseResp);
		assertEquals(socialMediaServiceImpl.unFollow("auto1", "auto2"), baseResp);		
	}
	
	@Test
	public void unFollowTest() throws Exception {
		when(socialMediaControllerImpl.follow("auto1", "auto2")).thenReturn(baseResp);
		assertEquals(socialMediaServiceImpl.follow("auto1", "auto2"), baseResp);		
	}

}
