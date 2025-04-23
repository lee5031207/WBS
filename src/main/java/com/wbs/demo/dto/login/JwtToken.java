package com.wbs.demo.dto.login;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder 
//@Data
@Getter @Setter
public class JwtToken {
	private String grantType;
	private String accessToken;
	private String refreshToken;
}
