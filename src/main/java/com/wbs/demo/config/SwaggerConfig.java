package com.wbs.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;

@SecurityScheme(
	    name = "bearerAuth",
	    type = SecuritySchemeType.HTTP,
	    scheme = "bearer",
	    bearerFormat = "JWT",
	    in = SecuritySchemeIn.HEADER
)
@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI().components(new Components())
				.info(apiInfo())
				.addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
	}
	
	private Info apiInfo() {
		return new Info()
                .title("WBS API Document") // API의 제목
                .description("WBS Api 명세서 입니다") // API에 대한 설명
                .version("1.0.0"); // API의 버전
	}
}
