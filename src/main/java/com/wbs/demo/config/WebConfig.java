package com.wbs.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Value("${wbs.front.url}")
	private String frontUrl;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
	    registry.addMapping("/**")
	            .allowedOrigins(frontUrl)
	            .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
	            .allowedHeaders("Authorization", "Content-Type", "X-Requested-With", "Accept")  // 허용할 헤더들
	            .allowCredentials(true);
	}
}
