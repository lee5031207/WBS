package com.wbs.demo.web;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wbs.demo.dto.login.JwtToken;
import com.wbs.demo.dto.login.LoginRequestDto;
import com.wbs.demo.service.LoginService;
import com.wbs.demo.service.UserService;

import ch.qos.logback.classic.Logger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "login API")
public class LoginController {

	private final LoginService loginService;
	
	@PostMapping("/login")
	@Operation(summary = "로그인", description = "로그인 API")
	public JwtToken login(@RequestBody LoginRequestDto loginDto , HttpServletResponse response) {
		
		String loginId = loginDto.getLoginId();
		String pwd = loginDto.getPwd();
		JwtToken jwtToken = loginService.login(loginId, pwd);
		
		Cookie cookie = new Cookie("refreshToken", jwtToken.getRefreshToken());
		cookie.setHttpOnly(true);
		cookie.setSecure(false);
		cookie.setPath("/");
		cookie.setMaxAge(7*24*60*60); //7일
		response.addCookie(cookie);
		jwtToken.setRefreshToken("");
		
		return jwtToken;
	}
	
	
	@PostMapping("/refresh")
	@Operation(summary = "Token 재발급", description = "Token 재발급 API")
	public JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response) {
		
		String refreshToken = null;
		JwtToken jwtToken = null;
		
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			for (Cookie cookie : cookies) {
	            if ("refreshToken".equals(cookie.getName())) {
	                refreshToken = cookie.getValue();
	                break;
	            }
	        }
		}

		jwtToken = loginService.refresh(refreshToken);
		Cookie cookie = new Cookie("refreshToken", jwtToken.getRefreshToken());
		cookie.setHttpOnly(true);
		cookie.setSecure(false);
		cookie.setPath("/");
		cookie.setMaxAge(7*24*60*60); //7일
		response.addCookie(cookie);
		jwtToken.setRefreshToken("");
		
		return jwtToken;
	}
	
	
}
