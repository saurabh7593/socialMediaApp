/**
 * 
 */
package com.cs.task.code.service;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.cs.task.code.beans.SocialUser;
import com.cs.task.code.request.CreatePostRequest;
import com.cs.task.code.response.BaseResponse;

/**
 * @author Saurabh Gupta
 * test classes for social media service class
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SocialMediaServiceTest  {
	
	@Mock
	SocialMediaServiceImpl socialMediaServiceImpl;
	
	Map<String,SocialUser> postMap;
	
	CreatePostRequest request;
	
	ResponseEntity<BaseResponse> value;
	
	BaseResponse response;
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);	
		request=new CreatePostRequest();
		request.setUserId("sd212");
		request.setPostId("ps123");
		request.setContent("Hi Tis is conent");
		Map <String,String> postsMap =new HashMap<>();
		postsMap.put("ps123", "Some content");
		List <String> followeeList =new ArrayList<>();
		followeeList.add("cs101");
		
		postMap =new HashMap<>();
		postMap.put("cs", new SocialUser("cs", followeeList, postsMap));
		
		response =new BaseResponse();
		response.setResponseStatus(HttpStatus.OK);
		response.setResponseMessage("Post Created Successfully");
	}


	/* (non-Javadoc)
	 * @see com.cs.task.code.service.SocialMediaService#createPost(com.cs.task.code.request.CreatePostRequest)
	 */
	@Test
	public void createPostTest() {
		ResponseEntity<BaseResponse>resJson= ResponseEntity.status(HttpStatus.OK).body(response);
		when(socialMediaServiceImpl.createPost(request)).thenReturn(resJson);
		assertEquals(resJson.getStatusCode(), HttpStatus.OK);
	}
}
