package com.wbs.demo.dto.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder 
@Getter @Setter
public class JwtToken {
	private String grantType;
	private String accessToken;
	private String refreshToken;
}
