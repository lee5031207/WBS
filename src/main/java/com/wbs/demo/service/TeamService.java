package com.wbs.demo.service;

import org.springframework.stereotype.Service;

import com.wbs.demo.domain.Team;
import com.wbs.demo.dto.team.TeamCreateDto;
import com.wbs.demo.dto.team.TeamResponseDto;
import com.wbs.demo.repository.TeamRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamService {

	private final TeamRepository teamRepo;
	
	@Transactional
	public TeamResponseDto findById(Long id) {
		Team team = teamRepo.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("팀(부서) 조회 결과가 없습니다."));
		return new TeamResponseDto(team);
	}
	
	@Transactional 
	public TeamResponseDto createTeam(TeamCreateDto request) {
		Team team = new Team();
		team.setTeamCd(request.getTeamCd());
		team.setTeamNm(request.getTeamNm());
		Team savedTeam = teamRepo.save(team);
		return new TeamResponseDto(savedTeam);
	}
}
