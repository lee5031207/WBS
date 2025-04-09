package com.wbs.demo.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI().components(new Components())
				.info(apiInfo());
	}
	
	private Info apiInfo() {
		return new Info()
                .title("WBS API Spec") // API의 제목
                .description("WBS Api 명세서 입니다") // API에 대한 설명
                .version("1.0.0"); // API의 버전
	}
}
