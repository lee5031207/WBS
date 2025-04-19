package com.wbs.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wbs.demo.domain.Team;
import com.wbs.demo.domain.User;
import com.wbs.demo.dto.team.TeamResponseDto;
import com.wbs.demo.dto.user.UserCreateReqDto;
import com.wbs.demo.dto.user.UserResponseDto;
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
		
		return UserResponseDto
				.builder()
				.userId(user.getUserId())
				.loginId(user.getLoginId())
				.userNm(user.getUserNm())
				.email(user.getEmail())
				.team(TeamResponseDto
						.builder()
						.teamId(user.getTeam().getTeamId())
						.teamCd(user.getTeam().getTeamCd())
						.teamNm(user.getTeam().getTeamNm())
						.build()
				)
				.role(user.getRole())
				.build();
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
		
		User savedUser = userRepo.save(user);
		return UserResponseDto
				.builder()
				.userId(savedUser.getUserId())
				.loginId(savedUser.getLoginId())
				.userNm(savedUser.getUserNm())
				.email(savedUser.getEmail())
				.team(TeamResponseDto
						.builder()
						.teamId(savedUser.getTeam().getTeamId())
						.teamCd(savedUser.getTeam().getTeamCd())
						.teamNm(savedUser.getTeam().getTeamNm())
						.build()
				)
				.role(savedUser.getRole())
				.build();
	}

	
}
