package com.wbs.demo.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wbs.demo.auth.JwtTokenProvider;
import com.wbs.demo.dto.login.JwtToken;
import com.wbs.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final JwtTokenProvider jwtTokenProvider;
	
	private final CustomUserDetailsService userDetailSvc;
	
	@Transactional
	public JwtToken login(String loginId, String pwd) {
		
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(loginId, pwd);
		
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		
		JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);
		
		return jwtToken;
	}
	
	@Transactional
	public JwtToken refresh(String refreshToken) {
		
		if (refreshToken == null || !jwtTokenProvider.validateToken(refreshToken)) {
	        throw new BadCredentialsException("Refresh Token is missing or invalid");
	    }
		
		String userId = jwtTokenProvider.parseClaims(refreshToken).getSubject();
		UserDetails userDetails = userDetailSvc.loadUserByUsername(userId);
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,"", userDetails.getAuthorities());
		JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);
		
		return jwtToken;
	}

}