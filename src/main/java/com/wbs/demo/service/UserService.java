package com.wbs.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wbs.demo.domain.User;
import com.wbs.demo.dto.UserResponseDto;
import com.wbs.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepo;
	
	@Transactional(readOnly = true)
	public UserResponseDto findById(Long id) {
		User user = userRepo.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("조회 결과가 없습니다."));
		return new UserResponseDto(user);
	}

	
}
