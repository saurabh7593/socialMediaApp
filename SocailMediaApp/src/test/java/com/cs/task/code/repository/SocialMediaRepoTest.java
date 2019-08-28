/**
 * 
 */
package com.cs.task.code.repository;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cs.task.code.beans.SocialUser;
import com.cs.task.code.exception.UserDetailsNotFoundException;


/**
 * @author Saurabh Gupta
 *Test class for social media repository
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SocialMediaRepoTest  {
	
	@Mock
	private SocialMediaRepoImpl socialMediaRepoImpl;
	
	
	Map<String,SocialUser> userDetailsMap;
	
	List <String> followeeList;
	
	Map <String,String> postsMap;
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		userDetailsMap = new HashMap<>();
		//SocialUser user = new SocialUser(Mockito.anyString(),Mockito.anyList(), Mockito.anyMap());
		postsMap =new LinkedHashMap<>();
		postsMap.put("ps123", "Some content");
		followeeList =new ArrayList<>();
		followeeList.add("cs101");
		SocialUser user = new SocialUser("sa123",followeeList, postsMap);
		userDetailsMap.put("sa123", user);
		socialMediaRepoImpl.setuserDetailsMap(userDetailsMap);

	}
	
	@Test
	public void findUserDetailsTest() throws UserDetailsNotFoundException  {		
		when(socialMediaRepoImpl.findUserdetails("sa123")).thenReturn(userDetailsMap);
		assertTrue(userDetailsMap.get("sa123").getFollowList().contains("cs101"));
	}
	
	@Test
	public void getPostDetailsOfUSerTest() throws UserDetailsNotFoundException {		
		when(socialMediaRepoImpl.getPostDetailsOfUser("sa123")).thenReturn(postsMap);
		assertTrue(userDetailsMap.get("sa123").getPosts().get("ps123").contains("Some content"));
	}
	
	@Test
	public void getFolloweeDetailsOfUserTest() throws UserDetailsNotFoundException {		
		when(socialMediaRepoImpl.getFolloweeDetailsOfUser("sa123")).thenReturn(followeeList);
		assertTrue(userDetailsMap.get("sa123").getFollowList().contains("cs101"));
	}
	
}
