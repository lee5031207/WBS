package com.wbs.demo.dto.login;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtToken {
	private String grantType;
	private String accessToken;
	private String refreshToken;
}
