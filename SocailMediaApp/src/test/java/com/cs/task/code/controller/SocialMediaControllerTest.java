package com.cs.task.code.controller;





/**
 * @author Saurabh Gupta
 * implements the social media controller Tests
 *
 */
//@SpringBootTest
//@RunWith(SpringRunner.class)
public class SocialMediaControllerTest  {
	
	private static final Logger logger=LoggerFactory.getLogger(SocialMediaControllerTest.class);
	
	  //@Test
	  public void contextLoads() {
		//  assertTrue(true);
	  }

	
//	//@Mock
//	SocialMediaService socialMediaServiceImpl;
//
//	@Override
//	@RequestMapping(value="/create/createPost",method= {RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<BaseResponse> createPost(@RequestBody(required=true) @Valid CreatePostRequest request) {
//		logger.info("Inside createPost Method");
//		return socialMediaServiceImpl.createPost(request);
//	}
//
//	@Override
//	@RequestMapping(value="/get/getNewsFeed/{userId}",method= {RequestMethod.GET},produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<NewsFeedResponse> getNewsFeed(@PathVariable(name="userId", required=true) @NotNull String userId) {
//		logger.info("Inside getNewsFeed Method");
//		return socialMediaServiceImpl.getNewsFeed(userId);
//	}
//
//	@Override
//	@RequestMapping(value="/follow",method= {RequestMethod.GET},produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<BaseResponse> follow(@RequestParam (name="followerId",required=true) String followerId,@RequestParam (name="followeeId",required=true) String followeeId) {
//		logger.info("Inside follow Method");
//		return socialMediaServiceImpl.follow(followerId, followeeId);
//	}
//	
//	@Override
//	@RequestMapping(value="/unFollow",method= {RequestMethod.GET},produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<BaseResponse> unFollow(@RequestParam (name="followerId",required=true) String followerId,@RequestParam (name="followeeId",required=true) String followeeId) {
//		logger.info("Inside Unfollow Method");
//		return socialMediaServiceImpl.unFollow(followerId, followeeId);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.cs.task.code.controller.SocailMediaController#listOfFollowers(java.lang.String)
//	 */
//	@Override
//	@RequestMapping(value="/listOfFollowee/{userId}",method= {RequestMethod.GET})
//	public ResponseEntity<FolloweeResponse> listOfFollowers(@PathVariable(name="userId",required=true)String userId) {
//		logger.info("Inside listOfFollowers Method");
//		return socialMediaServiceImpl.listOfFollowee(userId);
//}
}
