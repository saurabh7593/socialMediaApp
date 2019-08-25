/**
 * 
 */
package com.cs.task.code.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.task.code.beans.SocialUser;
import com.cs.task.code.exception.UserNotFoundException;

/**
 * @author Saurabh Gupta
 *Test class for social media repository
 */
@Component
public class SocailMediaRepoTest  {

	/* (non-Javadoc)
	 * @see com.cs.task.code.repository.SocailMediaRepo#createUser(int)
	 * 
	 */
	
	@Autowired
	private Map<String,SocialUser> postMap;
	
	
	public Map<String, SocialUser> createUser(String userId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cs.task.code.repository.SocailMediaRepo#findUserdetails(int)
	 */
	public Map<String, SocialUser> findUserdetails(String userId) throws UserNotFoundException {
		return null;
	}

}
