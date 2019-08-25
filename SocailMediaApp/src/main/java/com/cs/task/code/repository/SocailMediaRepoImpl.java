/**
 * 
 */
package com.cs.task.code.repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.task.code.beans.SocialUser;
import com.cs.task.code.exception.UserNotFoundException;

/**
 * @author Saurabh Gupta
 *
 */
@Component
public class SocailMediaRepoImpl implements SocailMediaRepo {

	/* (non-Javadoc)
	 * @see com.cs.task.code.repository.SocailMediaRepo#createUser(int)
	 * 
	 */
	
	@Autowired
	private Map<String,SocialUser> postMap;
	
	
	@Override
	public Map<String, SocialUser> createUser(String userId) {
		Map<String,String>post=new LinkedHashMap<>();
		List<String> followList=new ArrayList<>();
		SocialUser user=new SocialUser(userId,followList,post);		
		postMap.put(userId,user);
		return postMap;
	}

	/* (non-Javadoc)
	 * @see com.cs.task.code.repository.SocailMediaRepo#findUserdetails(int)
	 */
	@Override
	public Map<String, SocialUser> findUserdetails(String userId) throws UserNotFoundException {
		if(userId!=null && postMap.get(userId)!=null) {
			return postMap;
		} else if (userId!=null && postMap.get(userId)==null) {
			createUser(userId);
			throw new UserNotFoundException("User details not found, Creating new user. Please try again");
		}
		return postMap;
	}

}
