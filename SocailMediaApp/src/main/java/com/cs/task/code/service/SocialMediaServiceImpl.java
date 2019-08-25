/**
 * 
 */
package com.cs.task.code.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.cs.task.code.exception.UserNotFoundException;
import com.cs.task.code.repository.SocailMediaRepo;
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
	private static final Logger logger=LoggerFactory.getLogger(SocialMediaServiceImpl.class);
		
	@Autowired
	private BaseResponse response;
	
	@Autowired
	private SocailMediaRepo socailMediaRepoImpl;


	/* (non-Javadoc)
	 * @see com.cs.task.code.service.SocialMediaService#createPost(com.cs.task.code.request.CreatePostRequest)
	 */
	@Override
	public ResponseEntity<BaseResponse> createPost(CreatePostRequest request) {
		logger.info("Inside create post method with request" +request.toString());
		try {
			socailMediaRepoImpl.findUserdetails(request.getUserId()).get(request.getUserId()).getPosts().put(request.getPostId(),request.getContent());
			response.setResponseStatus(HttpStatus.OK);
			response.setResponseMessage("Post Created Successfully");
		} catch (UserNotFoundException e) {
			response.setResponseStatus(HttpStatus.OK);
			response.setResponseMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			response.setResponseStatus(HttpStatus.OK);
			response.setResponseMessage("Something went wrong"+e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(response);	}

	/* (non-Javadoc)
	 * @see com.cs.task.code.service.SocialMediaService#getNewsFeed(int)
	 */
	@Override
	public ResponseEntity<NewsFeedResponse> getNewsFeed(String userId) {
		logger.info("inside getNewsFeed method with userId"+userId);
		NewsFeedResponse newsFeedResponse= new NewsFeedResponse();
		try {
			newsFeedResponse.setUserData(socailMediaRepoImpl.findUserdetails(userId).get(userId).getPosts());
			newsFeedResponse.setResponseStatus(HttpStatus.OK);
			if(socailMediaRepoImpl.findUserdetails(userId).get(userId).getPosts().size()<=20) {			
				newsFeedResponse.setResponseMessage("Post Retrieved Successfully");
			} else {
				List<Map.Entry<String, String>> postList = new ArrayList<>(socailMediaRepoImpl.findUserdetails(userId).get(userId).getPosts().entrySet());
				socailMediaRepoImpl.findUserdetails(userId).get(userId).getPosts().clear();
				for (Map.Entry<String, String> entry : postList.subList(0,20)) {
					socailMediaRepoImpl.findUserdetails(userId).get(userId).getPosts().put(entry.getKey(), entry.getValue());
					newsFeedResponse.setUserData(socailMediaRepoImpl.findUserdetails(userId).get(userId).getPosts());				
				}
			}
			logger.info("get news feed request completed successfully");
		} catch (UserNotFoundException e) {
			logger.info(e.getMessage());
			newsFeedResponse.setResponseStatus(HttpStatus.EXPECTATION_FAILED);
			newsFeedResponse.setResponseMessage(e.getMessage());
			newsFeedResponse.setUserData(null);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			newsFeedResponse.setResponseStatus(HttpStatus.EXPECTATION_FAILED);
			newsFeedResponse.setResponseMessage("Something went wrong"+e.getMessage());
			newsFeedResponse.setUserData(null);
		}
		
		return  ResponseEntity.status(HttpStatus.OK).body(newsFeedResponse);
	}

	/* (non-Javadoc)
	 * @see com.cs.task.code.service.SocialMediaService#follow(int, int)
	 */
	@Override
	public ResponseEntity<BaseResponse> follow(String followerId, String followeeId) {
		logger.info("Inside the follow method with follower id" +followerId+ " to follow"+followeeId);
		try {
			if(followerId==followeeId) {
				response.setResponseStatus(HttpStatus.EXPECTATION_FAILED);
				response.setResponseMessage("You can not follow yourself");
			}else if(socailMediaRepoImpl.findUserdetails(followerId).get(followerId) != null && socailMediaRepoImpl.findUserdetails(followeeId).get(followeeId) != null && socailMediaRepoImpl.findUserdetails(followerId).get(followerId).getFollowList()!=null){
				if( socailMediaRepoImpl.findUserdetails(followerId).get(followerId).getFollowList().contains(followeeId)) {
					response.setResponseStatus(HttpStatus.EXPECTATION_FAILED);
					response.setResponseMessage("You already follows the user"+followeeId);
				} else if (socailMediaRepoImpl.findUserdetails(followerId).get(followerId) != null && !socailMediaRepoImpl.findUserdetails(followerId).get(followerId).getFollowList().contains(followeeId)) {
					socailMediaRepoImpl.findUserdetails(followerId).get(followerId).getFollowList().add(followeeId);
					response.setResponseStatus(HttpStatus.ACCEPTED);
					response.setResponseMessage("Successfully Followed the user "+followeeId);
				}
				
			}else if(socailMediaRepoImpl.findUserdetails(followerId).get(followerId) != null && socailMediaRepoImpl.findUserdetails(followerId).get(followeeId) != null && socailMediaRepoImpl.findUserdetails(followerId).get(followerId).getFollowList()==null){
				List<String> followList=new ArrayList<>();
				followList.add(followeeId);
				socailMediaRepoImpl.findUserdetails(followerId).get(followerId).setFollowList(followList);
				response.setResponseStatus(HttpStatus.ACCEPTED);
				response.setResponseMessage("Successfully Followed the user "+followeeId);			
			}
			logger.info("follow request completed successfully");
		} catch (UserNotFoundException e) {
			response.setResponseStatus(HttpStatus.EXPECTATION_FAILED);
			response.setResponseMessage(e.getMessage());
		} catch(Exception e) {
			logger.error("Something went wrong" +e.getMessage());
			e.printStackTrace();
			response.setResponseStatus(HttpStatus.EXPECTATION_FAILED);
			response.setResponseMessage("Someting went wrong"+e.getMessage());
		}
		
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/* (non-Javadoc)
	 * @see com.cs.task.code.service.SocialMediaService#unFollow(int, int)
	 */
	@Override
	public ResponseEntity<BaseResponse> unFollow(String followerId, String followeeId) {
		logger.info("Inside the Unfollow method with follower id" +followerId+ " to follow"+followeeId);
		try {
			if(followerId==followeeId) {
				response.setResponseStatus(HttpStatus.EXPECTATION_FAILED);
				response.setResponseMessage("You can not unfollow yourself");
			}else if(socailMediaRepoImpl.findUserdetails(followerId).get(followerId) != null &&socailMediaRepoImpl.findUserdetails(followeeId).get(followeeId) != null && socailMediaRepoImpl.findUserdetails(followeeId).get(followerId).getFollowList()!=null) {
				if(socailMediaRepoImpl.findUserdetails(followeeId).get(followerId).getFollowList().contains(followeeId)) {
					response.setResponseStatus(HttpStatus.ACCEPTED);
					response.setResponseMessage("You are unfollowing the user"+followeeId);
				} else if(!socailMediaRepoImpl.findUserdetails(followeeId).get(followerId).getFollowList().contains(followeeId)) {
					response.setResponseStatus(HttpStatus.EXPECTATION_FAILED);
					response.setResponseMessage("You do not follow the user"+followeeId);
				}		
			} else if (socailMediaRepoImpl.findUserdetails(followeeId).get(followerId) != null && socailMediaRepoImpl.findUserdetails(followeeId).get(followerId).getFollowList()==null) {
				response.setResponseStatus(HttpStatus.EXPECTATION_FAILED);
				response.setResponseMessage("You do not follow the user"+followeeId);
			}
			logger.info("Unfollow request completed successfully");

		} catch (UserNotFoundException e) {
			logger.info("user not found" +e.getMessage());
			response.setResponseStatus(HttpStatus.EXPECTATION_FAILED);
			response.setResponseMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("Something went wrong" +e.getMessage());
			e.printStackTrace();
			response.setResponseStatus(HttpStatus.EXPECTATION_FAILED);
			response.setResponseMessage("Someting went wrong"+e.getMessage());		
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/* (non-Javadoc)
	 * @see com.cs.task.code.service.SocialMediaService#listOfFollowee(java.lang.String)
	 */
	@Override
	public ResponseEntity<FolloweeResponse> listOfFollowee(String userId) {
		FolloweeResponse response = new FolloweeResponse();
		try {
			if(socailMediaRepoImpl.findUserdetails(userId).get(userId)!=null) {
				response.setResponseStatus(HttpStatus.OK);
				response.setResponseMessage("retrieval of followee successfull");
				response.setFolloweeData(socailMediaRepoImpl.findUserdetails(userId).get(userId).getFollowList());						
				}
		} catch (UserNotFoundException e) {
			logger.info("user not found" +e.getMessage());
			response.setResponseStatus(HttpStatus.EXPECTATION_FAILED);
			response.setResponseMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("Something went wrong" +e.getMessage());
			e.printStackTrace();
			response.setResponseStatus(HttpStatus.EXPECTATION_FAILED);
			response.setResponseMessage("Someting went wrong"+e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).body(response);		
	}

}
