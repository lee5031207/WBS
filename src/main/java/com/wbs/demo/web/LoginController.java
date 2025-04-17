package com.wbs.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wbs.demo.dto.login.JwtToken;
import com.wbs.demo.dto.login.LoginRequestDto;
import com.wbs.demo.service.LoginService;
import com.wbs.demo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
@Tag(name = "Auth", description = "login API")
public class LoginController {

	private final LoginService loginService;
	
	@PostMapping
	@Operation(summary = "로그인", description = "로그인 API")
	public JwtToken login(@RequestBody LoginRequestDto loginDto) {
		String loginId = loginDto.getLoginId();
		String pwd = loginDto.getPwd();
		JwtToken jwtToken = loginService.login(loginId, pwd);
		return jwtToken;
	}
}
