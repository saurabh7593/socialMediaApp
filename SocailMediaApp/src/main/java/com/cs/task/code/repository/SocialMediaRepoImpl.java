/**
 * 
 */
package com.cs.task.code.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cs.task.code.beans.SocialUser;
import com.cs.task.code.exception.UserDetailsNotFoundException;

/**
 * @author Saurabh Gupta
 *
 */
@Component
public class SocialMediaRepoImpl implements SocialMediaRepo {

	/* (non-Javadoc)
	 * @see com.cs.task.code.repository.socialMediaRepo#createUser(int)
	 * 
	 */
	
	
	private Map<String,SocialUser> userDetailsMap =new LinkedHashMap<>();
	
	private Map<String,String> newsFeedMap =new LinkedHashMap<String, String>() ;
		
	
	@Override
	public Map<String, SocialUser> createUser(String userId) {
		Map<String,String>post=new LinkedHashMap<>();
		List<String> followList=new ArrayList<>();
		SocialUser user=new SocialUser(userId,followList,post);		
		userDetailsMap.put(userId,user);
		return userDetailsMap;
	}

	/* (non-Javadoc)
	 * @see com.cs.task.code.repository.socialMediaRepo#findUserdetails(int)
	 */
	@Override
	public Map<String, SocialUser> findUserdetails(String userId) throws UserDetailsNotFoundException {
		if(userId!=null && userDetailsMap.get(userId)!=null) {
			return userDetailsMap;
		} else if (userId!=null && userDetailsMap.get(userId)==null) {
			createUser(userId);
			throw new UserDetailsNotFoundException("User details not found, Creating new user. Please try again");
		}
		return userDetailsMap;
	}
	
    @Override
    public Map<String, String> getPostDetailsOfUser(final String userId) throws UserDetailsNotFoundException {
    	Map<String,String> tmpMap= new LinkedHashMap<>(findUserdetails(userId).get(userId).getPosts());
        try {
        	for (String follower :getFolloweeDetailsOfUser(userId)) {
        		tmpMap.putAll(findUserdetails(follower).get(follower).getPosts());
        	}
            return tmpMap;
        } catch (NullPointerException e) {
            throw new UserDetailsNotFoundException("List of Posts not Found for this USer");
        }
    }

    @Override
    public List<String> getFolloweeDetailsOfUser(final String userId) throws UserDetailsNotFoundException {
        try {
            return findUserdetails(userId).get(userId).getFollowList();
        } catch (NullPointerException e) {
            throw new UserDetailsNotFoundException("List of Followers not Found");
        }
    }

	@Override
	public Map<String, String> createPost(String userId) throws UserDetailsNotFoundException {
		newsFeedMap=findUserdetails(userId).get(userId).getPosts();
		return newsFeedMap;
	}
	
	public Map<String, SocialUser> getuserDetailsMap() {
		return userDetailsMap;
	}

	public void setuserDetailsMap(Map<String, SocialUser> userDetailsMap) {
		this.userDetailsMap = userDetailsMap;
	}
}
