package com.wbs.demo.dto.team;

import com.wbs.demo.domain.Team;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class TeamResponseDto {

	private Long teamId;
	private String teamCd;
	private String teamNm;
	
	public TeamResponseDto(Team team) {
		this.teamId = team.getTeamId();
		this.teamCd = team.getTeamCd();
		this.teamNm = team.getTeamNm();
	}
}
