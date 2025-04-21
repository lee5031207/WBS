package com.wbs.demo.dto.team;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wbs.demo.domain.Project;
import com.wbs.demo.domain.Team;
import com.wbs.demo.domain.User;
import com.wbs.demo.dto.user.UserResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamResponseDto {
	private Long teamId;
	private String teamCd;
	private String teamNm;
	private List<UserResponseDto> teamMember;
	
	public static TeamResponseDto fromSimple(Team team) {
		
		return TeamResponseDto
				.builder()
				.teamId(team.getTeamId())
				.teamCd(team.getTeamCd())
				.teamNm(team.getTeamNm())
				.build();
	}
	
	public static TeamResponseDto fromDetail(Team team) {
		
		List<UserResponseDto> teamMembers = team.getTeamMember().stream()
				.map(UserResponseDto::fromSimple)
				.collect(Collectors.toList());
		
		return TeamResponseDto
				.builder()
				.teamId(team.getTeamId())
				.teamCd(team.getTeamCd())
				.teamNm(team.getTeamNm())
				//Detail 추가사항
				.teamMember(teamMembers)
				.build();
	}
}
