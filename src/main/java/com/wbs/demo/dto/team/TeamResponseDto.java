package com.wbs.demo.dto.team;

import java.util.ArrayList;
import java.util.List;

import com.wbs.demo.domain.Project;
import com.wbs.demo.domain.Team;
import com.wbs.demo.domain.User;
import com.wbs.demo.dto.user.UserResponseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class TeamResponseDto {

	private Long teamId;
	private String teamCd;
	private String teamNm;
	private List<UserResponseDto> teamMember = new ArrayList<>();
	
	public TeamResponseDto(Team team) {
		this.teamId = team.getTeamId();
		this.teamCd = team.getTeamCd();
		this.teamNm = team.getTeamNm();
	}
}
