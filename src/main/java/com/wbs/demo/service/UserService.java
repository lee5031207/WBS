package com.wbs.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wbs.demo.domain.Team;
import com.wbs.demo.domain.User;
import com.wbs.demo.dto.team.TeamResponseDto;
import com.wbs.demo.dto.user.UserCreateReqDto;
import com.wbs.demo.dto.user.UserResponseDto;
import com.wbs.demo.dto.user.UserSearchDto;
import com.wbs.demo.repository.TeamRepository;
import com.wbs.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepo;
	private final TeamRepository teamRepo;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional(readOnly = true)
	public UserResponseDto findById(Long id) {
		
		User user = userRepo.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("사용자 조회 결과가 없습니다."));
		
		return UserResponseDto.fromDetail(user);
	}
	
	@Transactional
	public UserResponseDto createUser(UserCreateReqDto request) {
		
		Team team = teamRepo.findById(request.getTeamId())
				.orElseThrow(()-> new IllegalArgumentException("팀(부서) 조회 결과가 없습니다."));
		
		User user = new User();
		user.setLoginId(request.getLoginId());
		user.setPwd(passwordEncoder.encode(request.getPwd()));
		user.setUserNm(request.getUserNm());
		user.setEmail(request.getEmail());
		user.setTeam(team);
		user.setDeleteYn("N");
		
		User savedUser = userRepo.save(user);
		return UserResponseDto.fromDetail(savedUser);
	}
	
	@Transactional(readOnly = true)
	public List<UserResponseDto> searchUser(UserSearchDto request){
		
		List<User> users = userRepo.searchUser(request.getUserNm());
		List<UserResponseDto> urds = new ArrayList<>();
		for(User user : users) {
			UserResponseDto urd = UserResponseDto.fromSimple(user);
			urds.add(urd);
		}
		
		return urds;
	}

	
}
