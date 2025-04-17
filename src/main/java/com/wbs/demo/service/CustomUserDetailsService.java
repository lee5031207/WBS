package com.wbs.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wbs.demo.domain.User;
import com.wbs.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		return userRepository.findByloginId(loginId)
			.map(this::createUserDetails)
			.orElseThrow(()-> new UsernameNotFoundException("해당 ID가 없습니다."));
	}
	
    private UserDetails createUserDetails(User user) {
    	return User.builder()
    		.loginId(user.getLoginId())
    		.pwd(user.getPwd())
    		.role(user.getRole())
    		.build();
    }
}
