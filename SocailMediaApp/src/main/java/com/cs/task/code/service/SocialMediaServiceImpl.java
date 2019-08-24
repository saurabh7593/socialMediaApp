/**
 * 
 */
package com.cs.task.code.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.cs.task.code.beans.SocialUser;
import com.cs.task.code.request.CreatePostRequest;
import com.cs.task.code.response.BaseResponse;
import com.cs.task.code.response.FolloweeResponse;
import com.cs.task.code.response.NewsFeedResponse;

/**
 * @author Saurabh Gupta
 *
 */

@Component
public class SocialMediaServiceImpl implements SocialMediaService {
	
	@Autowired
	private Map<String,SocialUser> postMap;
	
	@Autowired
	private BaseResponse response;


	/* (non-Javadoc)
	 * @see com.cs.task.code.service.SocialMediaService#createPost(com.cs.task.code.request.CreatePostRequest)
	 */
	@Override
	public ResponseEntity<BaseResponse> createPost(CreatePostRequest request) {
		if(postMap.get(request.getUserId()) != null) {
			postMap.get(request.getUserId()).getPosts().put(request.getPostId(),request.getContent());
		} else {
			SocialUser user=new SocialUser();
				user.setUserId(request.getUserId());
				Map<String,String>post=new LinkedHashMap<>();
				post.put(request.getPostId(), request.getContent());
				user.setPosts(post);
				postMap.put(request.getUserId(),user);
					
		}
		response.setResponseStatus(HttpStatus.OK);
		response.setResponseMessage("Post Created Successfully");
		
										
		return ResponseEntity.status(HttpStatus.OK).body(response);	}

	/* (non-Javadoc)
	 * @see com.cs.task.code.service.SocialMediaService#getNewsFeed(int)
	 */
	@Override
	public ResponseEntity<NewsFeedResponse> getNewsFeed(String userId) {
		NewsFeedResponse newsFeedResponse= new NewsFeedResponse();
		if(postMap.get(userId)!=null) {
			newsFeedResponse.setResponseStatus(HttpStatus.OK);
			newsFeedResponse.setResponseMessage("Post Retrieved Successfully");
			if(postMap.get(userId).getPosts().size()<=20) {
				newsFeedResponse.setUserData(postMap.get(userId).getPosts());
			} else {
				List<Map.Entry<String, String>> postList = new ArrayList<>(postMap.get(userId).getPosts().entrySet());
				postMap.get(userId).getPosts().clear();
				for (Map.Entry<String, String> entry : postList.subList(0,20)) {
				    postMap.get(userId).getPosts().put(entry.getKey(), entry.getValue());
				}
				newsFeedResponse.setResponseStatus(HttpStatus.OK);
				newsFeedResponse.setResponseMessage("Top 20 Post Retrieved Successfully");
				newsFeedResponse.setUserData(postMap.get(userId).getPosts());				
			}
			return  ResponseEntity.status(HttpStatus.OK).body(newsFeedResponse);
		} else {
			newsFeedResponse.setResponseStatus(HttpStatus.EXPECTATION_FAILED);
			newsFeedResponse.setResponseMessage("User Not Found");
			newsFeedResponse.setUserData(null);
			return  ResponseEntity.status(HttpStatus.OK).body(newsFeedResponse);
		}
	}

	/* (non-Javadoc)
	 * @see com.cs.task.code.service.SocialMediaService#follow(int, int)
	 */
	@Override
	public ResponseEntity<BaseResponse> follow(String followerId, String followeeId) {
		if(followerId==followeeId) {
			response.setResponseStatus(HttpStatus.EXPECTATION_FAILED);
			response.setResponseMessage("You can not follow yourself");
		}else if(postMap.get(followerId) != null && postMap.get(followeeId) != null && postMap.get(followerId).getFollowList()!=null){
			if( postMap.get(followerId).getFollowList().contains(followeeId)) {
				response.setResponseStatus(HttpStatus.EXPECTATION_FAILED);
				response.setResponseMessage("You already follows the user"+followeeId);
			} else if (postMap.get(followerId) != null && !postMap.get(followerId).getFollowList().contains(followeeId)) {
				postMap.get(followerId).getFollowList().add(followeeId);
				response.setResponseStatus(HttpStatus.ACCEPTED);
				response.setResponseMessage("Successfully Followed the user "+followeeId);
			}
			
		}else if(postMap.get(followerId) != null && postMap.get(followeeId) != null && postMap.get(followerId).getFollowList()==null){
			List<String> followList=new ArrayList<>();
			followList.add(followeeId);
			postMap.get(followerId).setFollowList(followList);
			response.setResponseStatus(HttpStatus.ACCEPTED);
			response.setResponseMessage("Successfully Followed the user "+followeeId);			
		}else if(postMap.get(followeeId) == null ) {
			response.setResponseStatus(HttpStatus.EXPECTATION_FAILED);
			response.setResponseMessage("Followee "+followeeId+" not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/* (non-Javadoc)
	 * @see com.cs.task.code.service.SocialMediaService#unFollow(int, int)
	 */
	@Override
	public ResponseEntity<BaseResponse> unFollow(String followerId, String followeeId) {
		if(followerId==followeeId) {
			response.setResponseStatus(HttpStatus.EXPECTATION_FAILED);
			response.setResponseMessage("You can not unfollow yourself");
		}else if(postMap.get(followerId) != null &&postMap.get(followeeId) != null && postMap.get(followerId).getFollowList()!=null) {
			if(postMap.get(followerId).getFollowList().contains(followeeId)) {
				response.setResponseStatus(HttpStatus.ACCEPTED);
				response.setResponseMessage("You are unfollowing the user"+followeeId);
			} else if(!postMap.get(followerId).getFollowList().contains(followeeId)) {
				response.setResponseStatus(HttpStatus.EXPECTATION_FAILED);
				response.setResponseMessage("You do not follow the user"+followeeId);
			}		
		} else if (postMap.get(followerId) != null && postMap.get(followerId).getFollowList()==null) {
			response.setResponseStatus(HttpStatus.EXPECTATION_FAILED);
			response.setResponseMessage("You do not follow the user"+followeeId);
		} if(postMap.get(followeeId) == null ) {
			response.setResponseStatus(HttpStatus.EXPECTATION_FAILED);
			response.setResponseMessage("Followee "+followeeId+" not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/* (non-Javadoc)
	 * @see com.cs.task.code.service.SocialMediaService#listOfFollowee(java.lang.String)
	 */
	@Override
	public ResponseEntity<FolloweeResponse> listOfFollowee(String userId) {
		FolloweeResponse response = new FolloweeResponse();
		if(postMap.get(userId)!=null) {
			response.setResponseStatus(HttpStatus.OK);
			response.setResponseMessage("retrieval of followee successfull");
			response.setFolloweeData(postMap.get(userId).getFollowList());						
			}
		return ResponseEntity.status(HttpStatus.OK).body(response);		
	}

}
