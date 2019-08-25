package com.cs.task.code.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
import com.cs.task.code.service.SocialMediaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Saurabh Gupta
 * implements the social media controller Tests
 *
 */
@WebMvcTest(value=SocialMediaControllerImpl.class)
@RunWith(SpringRunner.class)
public class SocialMediaControllerTest  {
		
	@MockBean
	SocialMediaService socialMediaServiceImpl;

	
	@Autowired
	private MockMvc mockMvc;
	
	CreatePostRequest request;
	
	BaseResponse response;
	
	NewsFeedResponse newsFeedResponse;
	
	FolloweeResponse followeeResponse;
	
	String requestJson;
	
	private static final Logger logger=LoggerFactory.getLogger(SocialMediaControllerTest.class);
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);

		request=new CreatePostRequest();
		request.setUserId("sd212");
		request.setPostId("ps123");
		request.setContent("Hi Tis is conent");
		requestJson=this.mapToJson(request);
		
		response =new BaseResponse();
		response.setResponseStatus(HttpStatus.OK);
		
		newsFeedResponse=new NewsFeedResponse();
		newsFeedResponse.setResponseStatus(HttpStatus.OK);
		
		followeeResponse= new FolloweeResponse();
		response.setResponseStatus(HttpStatus.OK);
	}
	
	
	  @Test
	  public void createPostTest() throws Exception{
			when(socialMediaServiceImpl.createPost(request)).thenReturn(ResponseEntity.status(HttpStatus.OK).body(response));
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders
					.post("/socialMedia/create/createPost")
					.accept(MediaType.APPLICATION_JSON_VALUE).content(requestJson)
					.contentType(MediaType.APPLICATION_JSON_VALUE);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			assertEquals(HttpStatus.OK.value(), response.getStatus());
	  }

	@Test
	public void getNewsFeedTest() throws Exception {
		when(socialMediaServiceImpl.getNewsFeed(Mockito.anyString())).thenReturn(ResponseEntity.status(HttpStatus.OK).body(newsFeedResponse));
		String URI="/socialMedia/get/getNewsFeed/saur";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(URI)
				.contentType(MediaType.APPLICATION_JSON_VALUE);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void followTest() throws Exception {
		when(socialMediaServiceImpl.follow(Mockito.anyString(),Mockito.anyString())).thenReturn(ResponseEntity.status(HttpStatus.OK).body(response));
		String URI="/socialMedia/follow?followerId=saur102&followeeId=cs101";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(URI)
				.contentType(MediaType.APPLICATION_JSON_VALUE);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}
	
	@Test
	public void unFollowTest() throws Exception {
		when(socialMediaServiceImpl.unFollow(Mockito.anyString(),Mockito.anyString())).thenReturn(ResponseEntity.status(HttpStatus.OK).body(response));
		String URI="/socialMedia/unFollow?followerId=saur102&followeeId=cs101";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(URI)
				.contentType(MediaType.APPLICATION_JSON_VALUE);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}
	
	@Test
	public void listOfFolloweeTest() throws Exception {
		when(socialMediaServiceImpl.listOfFollowee(Mockito.anyString())).thenReturn(ResponseEntity.status(HttpStatus.OK).body(followeeResponse));
		String URI="/socialMedia/listOfFollowee/userId";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(URI)
				.contentType(MediaType.APPLICATION_JSON_VALUE);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}

	
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
