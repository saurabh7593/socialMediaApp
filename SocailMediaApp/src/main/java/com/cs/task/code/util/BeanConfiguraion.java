/**
 * 
 */
package com.cs.task.code.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cs.task.code.beans.SocialUser;
import com.cs.task.code.response.BaseResponse;

/**
 * @author Saurabh Gupta
 *
 */

@Configuration
public class BeanConfiguraion {
	
	@Bean
	Map<String,SocialUser> postMap(){
		return new LinkedHashMap<>();
	}
	
	@Bean
	BaseResponse createPostResponse() {
		return new BaseResponse();
	}

}
