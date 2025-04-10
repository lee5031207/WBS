package com.wbs.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wbs.demo.domain.Team;
import com.wbs.demo.domain.User;
import com.wbs.demo.dto.team.TeamCreateDto;
import com.wbs.demo.dto.team.TeamResponseDto;
import com.wbs.demo.dto.team.TeamUpdateDto;
import com.wbs.demo.dto.user.UserResponseDto;
import com.wbs.demo.repository.TeamRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamService {

	private final TeamRepository teamRepo;
	
	@Transactional(readOnly = true)
	public TeamResponseDto findById(Long id) {
		Team team = teamRepo.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("팀(부서) 조회 결과가 없습니다."));
		
		List<UserResponseDto> urds = new ArrayList<>();
		for(User user : team.getTeamMember()) {
			UserResponseDto urd = new UserResponseDto();
			urd = new UserResponseDto(user);
			urds.add(urd);
		}
		
		TeamResponseDto trd = new TeamResponseDto(team);
		trd.setTeamMember(urds);
		
		return trd;
	}
	
	@Transactional(readOnly = true)
	public List<TeamResponseDto> getTeam() {
		List<Team> teams = teamRepo.findAll();
		List<TeamResponseDto> trds = new ArrayList<>();
		for (Team team : teams) {
			TeamResponseDto trd = new TeamResponseDto(team);
			trds.add(trd);
		}
		return trds;
	}
	
	@Transactional 
	public TeamResponseDto createTeam(TeamCreateDto request) {
		Team team = new Team();
		team.setTeamCd(request.getTeamCd());
		team.setTeamNm(request.getTeamNm());
		Team savedTeam = teamRepo.save(team);
		return new TeamResponseDto(savedTeam);
	}
	
	@Transactional
	public TeamResponseDto updateTeam(TeamUpdateDto request) {
		
		Team team = teamRepo.findById(request.getTeamId())
				.orElseThrow(()-> new IllegalArgumentException("팀(부서) 조회 결과가 없습니다."));

		if(request.getTeamCd() != null) {
			team.setTeamCd(request.getTeamCd());
		}
		if(request.getTeamNm() != null) {
			team.setTeamNm(request.getTeamNm());
		}
		
		Team updatedTeam = teamRepo.save(team);
		
		List<UserResponseDto> urds = new ArrayList<>();
		for(User user : updatedTeam.getTeamMember()) {
			UserResponseDto urd = new UserResponseDto();
			urd = new UserResponseDto(user);
			urds.add(urd);
		}
		
		TeamResponseDto trd = new TeamResponseDto(updatedTeam);
		trd.setTeamMember(urds);
		
		return trd;
	}
	
	@Transactional
	public void deleteTeam(Long id) {
		teamRepo.deleteById(id);
	}
	
}