package com.wbs.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wbs.demo.auth.JwtAuthenticationFilter;
import com.wbs.demo.auth.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtTokenProvider jwtTokenProvider;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http
			.csrf(csrf -> csrf.disable())
			.sessionManagement(session ->
					session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests()
			.requestMatchers(
					"/api/login",
					"/v3/api-docs/**",            // Swagger 문서 JSON
				    "/swagger-ui/**",             // Swagger UI HTML, JS 등
				    "/swagger-ui.html",           
				    "/swagger-resources/**",
				    "/webjars/**",
				    "/apiSpec"
					).permitAll()
			//.requestMatchers("/api/users").hasRole("ROLE_USER")
			.anyRequest().authenticated()
			.and()
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}
