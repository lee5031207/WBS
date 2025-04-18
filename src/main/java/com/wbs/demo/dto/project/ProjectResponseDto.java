package com.wbs.demo.dto.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wbs.demo.domain.Project;
import com.wbs.demo.domain.ProjectUser;
import com.wbs.demo.domain.Team;
import com.wbs.demo.dto.team.TeamResponseDto;
import com.wbs.demo.dto.user.UserResponseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ProjectResponseDto {
	
	private Long projectId;
	private String projectName;
	private String projectDesc;
	private Long teamId;
	private String teamName;
	private Date startDt;
	private Date endDt;
	private Date createDt;
	
	//TO-DO ProjectUser, Part, Task responseDto 만들어야함
	
	public ProjectResponseDto(Project project) {
		this.projectId = project.getProjectId();
		this.projectName = project.getProjectName();
		this.projectDesc = project.getProjectDesc();
		this.teamId = project.getTeam().getTeamId();
		this.teamName = project.getTeam().getTeamNm();
		this.startDt = project.getStartDt();
		this.endDt = project.getEndDt();
		this.createDt = project.getCreateDt();
	}
}
