/**
 * 
 */
package com.cs.task.code.repository;

import java.util.Map;

import com.cs.task.code.beans.SocialUser;
import com.cs.task.code.exception.UserNotFoundException;

/**
 * @author Saurabh Gupta
 *
 */
public interface SocialMediaRepo {
	
	Map<String, SocialUser> createUser(String userId);
	
	Map<String,SocialUser> findUserdetails(String userId) throws UserNotFoundException;

}
