package com.wbs.demo.auth;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.wbs.demo.dto.login.JwtToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;

@Component
public class JwtTokenProvider {

	@Value("${jwt.token.secretKey}")
	private String secretKey;
	
	@Value("${jwt.token.access.expiration}")
	private Long accessTokenExp;
	
	@Value("${jwt.token.refresh.expiration}")
	private Long refreshTokenExp;
	
    public JwtToken generateToken(Authentication authentication) {
    	
    	String authorities = authentication.getAuthorities().stream()
    			.map(GrantedAuthority::getAuthority)
    			.collect(Collectors.joining(","));
    	
    	long now = (new Date()).getTime();
    	String accessToken = Jwts.builder()
    			.setSubject(authentication.getName())
    			.claim("auth", authorities)
    			.setExpiration(new Date(now+accessTokenExp))
    			.signWith(SignatureAlgorithm.HS256, secretKey)
    			.compact();
    	
    	String refreshToken = Jwts.builder()
    			.setExpiration(new Date(now+refreshTokenExp))
    			.signWith(SignatureAlgorithm.HS256, secretKey)
    			.compact();
    	
    	return JwtToken.builder()
    			.grantType("Bearer")
    			.accessToken(accessToken)
    			.refreshToken(refreshToken)
    			.build();
    }
	
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
				.setSigningKey(secretKey)
				.build()
				.parseClaimsJws(token); //서명검증
			return true;
		}catch (SecurityException | MalformedJwtException e) {
			e.printStackTrace();
			// TODO: handle exception
		}catch (ExpiredJwtException e) {
			e.printStackTrace();
			// TODO: handle exception
		}catch (UnsupportedJwtException e) {
			e.printStackTrace();
			// TODO: handle exception
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return false;
	}
	
	//토큰 정보 확인하는 메서드
	public Authentication getAuthentication(String accessToken) {
		
		Claims claims = parseClaims(accessToken);
		
		if(claims.get("auth") == null) {
			throw new SecurityException("권한 정보가 없는 토큰 입니다.");
		}
		
		//권한 리스트 꺼내기
		List<GrantedAuthority> auths = new ArrayList<>();
		String[] roles = claims.get("auth").toString().split(",");
		for (String role : roles) {
			auths.add(new SimpleGrantedAuthority(role));
		}
		
		UserDetails principal = new User(claims.getSubject(), "", auths);
		return new UsernamePasswordAuthenticationToken(principal, "", auths);
	}
	
	//token정보 꺼내는 메서드
	public Claims parseClaims(String accessToken) {
		try {
			return Jwts.parserBuilder()
				.setSigningKey(secretKey)
				.build()
				.parseClaimsJws(accessToken)
				.getBody();
		}catch (ExpiredJwtException  e) {
			return e.getClaims();
		}
	}
	
	
}
