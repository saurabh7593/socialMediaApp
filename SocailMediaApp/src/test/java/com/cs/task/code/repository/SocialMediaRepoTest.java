/**
 * 
 */
package com.cs.task.code.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import com.cs.task.code.beans.SocialUser;
import com.cs.task.code.exception.UserNotFoundException;
import com.cs.task.code.request.CreatePostRequest;

import ch.qos.logback.core.db.dialect.HSQLDBDialect;

/**
 * @author Saurabh Gupta
 *Test class for social media repository
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SocialMediaRepoTest  {
	
	@InjectMocks
	private SocialMediaRepoImpl socialMediaRepoImpl;
	
	@Mock
	Map<String,SocialUser> postMap;
	
	
	
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}
		
	@Test(expected=UserNotFoundException.class)
	public void findUserDetailsExceptionTest() throws UserNotFoundException {
		socialMediaRepoImpl.findUserdetails(Mockito.anyString());
	}
}
