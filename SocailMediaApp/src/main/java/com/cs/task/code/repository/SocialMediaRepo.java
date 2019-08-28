/**
 * 
 */
package com.cs.task.code.repository;

import java.util.List;
import java.util.Map;

import com.cs.task.code.beans.SocialUser;
import com.cs.task.code.exception.UserDetailsNotFoundException;

/**
 * @author Saurabh Gupta
 *
 */
public interface SocialMediaRepo {
	
	Map<String, SocialUser> createUser(String userId);
	
	Map<String,SocialUser> findUserdetails(String userId) throws UserDetailsNotFoundException;
	
	Map<String, String> createPost(String userId) throws UserDetailsNotFoundException;

	Map<String, String> getPostDetailsOfUser(String userId) throws UserDetailsNotFoundException;

	List<String> getFolloweeDetailsOfUser(String userId) throws UserDetailsNotFoundException;

}
