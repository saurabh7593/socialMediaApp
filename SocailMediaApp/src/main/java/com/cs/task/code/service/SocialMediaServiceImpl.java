/**
 * 
 */
package com.cs.task.code.service;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.cs.task.code.exception.UserDetailsNotFoundException;
import com.cs.task.code.repository.SocialMediaRepo;
import com.cs.task.code.request.CreatePostRequest;
import com.cs.task.code.response.BaseResponse;
import com.cs.task.code.response.NewsFeedResponse;

/**
 * @author Saurabh Gupta
 *
 */

@Component
public class SocialMediaServiceImpl implements SocialMediaService {
	private static final Logger logger=LoggerFactory.getLogger(SocialMediaServiceImpl.class);
			
	@Autowired
	private SocialMediaRepo socialMediaRepoImpl;
	
	private BaseResponse response=new BaseResponse();
	
	Instant currTimeStamp;

	/* (non-Javadoc)
	 * @see com.cs.task.code.service.SocialMediaService#createPost(com.cs.task.code.request.CreatePostRequest)
	 */
	@Override
	public ResponseEntity<BaseResponse> createPost(CreatePostRequest request) {
		logger.info("Inside create post method with request {}",request.toString());		
		try {
			currTimeStamp=Instant.now();
			socialMediaRepoImpl.createPost(request.getUserId())
			.put(String.valueOf(currTimeStamp.toEpochMilli())
					.concat(request.getPostId()),request.getContent());
			setSuccessResponse(response, "Post created succesfully");
			} 
		catch (UserDetailsNotFoundException e) {
			setFailureResponse(response, e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			setFailureResponse(response, "Something went wrong");
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
		

	/* (non-Javadoc)
	 * @see com.cs.task.code.service.SocialMediaService#getNewsFeed(int)
	 */
	@Override
	public ResponseEntity<BaseResponse> getNewsFeed(String userId) {
		logger.info("inside getNewsFeed method with userId {}",userId);			
			NewsFeedResponse newsFeedResponse= new NewsFeedResponse();
			try {	 	
				 	newsFeedResponse.setUserData(socialMediaRepoImpl.getPostDetailsOfUser(userId).entrySet()
			                .stream()
			                .limit(20)
			                .sorted(Map.Entry.comparingByKey())
			                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new)));
					setSuccessResponse(newsFeedResponse, "Top 20 Post retrieved successfully");

			} catch (UserDetailsNotFoundException e) {
				logger.info(e.getMessage());
				setFailureResponse(newsFeedResponse, e.getMessage());
			} catch (Exception e) {
				logger.error(e.getMessage());
				setFailureResponse(newsFeedResponse, "Something went wrong");
			}
			
			return  ResponseEntity.status(HttpStatus.OK).body(newsFeedResponse);
	}
	

	/* (non-Javadoc)
	 * @see com.cs.task.code.service.SocialMediaService#follow(int, int)
	 */
	@Override
	public ResponseEntity<BaseResponse> follow(String followerId, String followeeId) {
		logger.info("Inside the follow method with follower id {} followeeId {}" ,followerId,followeeId);
			try {
				if (checkFollowCriteria(followerId, followeeId) && !socialMediaRepoImpl.getFolloweeDetailsOfUser(followerId).contains(followeeId)) {
					socialMediaRepoImpl.getFolloweeDetailsOfUser(followerId).add(followeeId);
					setSuccessResponse(response, "successfully followed the user");
				} else {
					setFailureResponse(response, "You already follow this user");
				}
			}
			catch (UserDetailsNotFoundException e) {
	            logger.info("user not found  {}" , e.getMessage());
	            setFailureResponse(response, e.getMessage());
			} catch (Exception e) {
	            logger.error("Something went wrong {}",e.getMessage());
	            setFailureResponse(response,"Something went wrong");
			}
			return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/* (non-Javadoc)
	 * @see com.cs.task.code.service.SocialMediaService#unFollow(int, int)
	 */
	@Override
	public ResponseEntity<BaseResponse> unFollow(String followerId, String followeeId){
		logger.info("Inside the Unfollow method with follower id {} to follow {}", followerId,followeeId);
            													            
            try {
            	if (checkFollowCriteria(followerId, followeeId) &&  socialMediaRepoImpl.getFolloweeDetailsOfUser(followerId).contains(followeeId)) {				
                    socialMediaRepoImpl.getFolloweeDetailsOfUser(followerId).remove(followeeId);
    				setSuccessResponse(response, "you uunfollowed the user");
            	} else {
            		setFailureResponse(response, "you do not follow this user");
            	}
    			
    		}catch (UserDetailsNotFoundException e) {
    			setFailureResponse(response, e.getMessage());
    		} catch(Exception e) {
    			logger.error("Something went wrong with exceptin meessage{}" ,e.getMessage());
    			e.printStackTrace();
    			setFailureResponse(response, "Someting went wrong");
    		}
    		return ResponseEntity.status(HttpStatus.OK).body(response);								
	}

    public void setSuccessResponse(BaseResponse response,String message) {
        response.setResponseStatus(HttpStatus.OK);
        response.setResponseMessage(message);
    }

    public void setFailureResponse(BaseResponse response,String message) {
        response.setResponseStatus(HttpStatus.EXPECTATION_FAILED);
        response.setResponseMessage(message);
    }
    
    public boolean checkFollowCriteria (String followerId , String followeeId) throws UserDetailsNotFoundException {
    	if(!followerId.equals(followeeId) && socialMediaRepoImpl.findUserdetails(followeeId)!=null 
    			&&socialMediaRepoImpl.findUserdetails(followerId)!=null) {
    		return true ;
    	} else if (followerId.equals(followeeId)){
    		throw new UserDetailsNotFoundException("You can not follow/unfollow yourself");
    	}
    	return false;
    }
}
