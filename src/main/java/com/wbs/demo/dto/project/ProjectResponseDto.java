package com.wbs.demo.dto.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wbs.demo.domain.Project;
import com.wbs.demo.domain.ProjectUser;
import com.wbs.demo.domain.Team;
import com.wbs.demo.dto.part.PartResponseDto;
import com.wbs.demo.dto.projectUser.ProjectUserResponseDto;
import com.wbs.demo.dto.task.TaskResponseDto;
import com.wbs.demo.dto.team.TeamResponseDto;
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
public class ProjectResponseDto {
	
	private Long projectId;
	private String projectName;
	private String projectDesc;
	private TeamResponseDto team;
	private Date startDt;
	private Date endDt;
	private List<ProjectUserResponseDto> projectUsers;
	private List<PartResponseDto> parts;
	private Date createDt;
	private String createId;
	private List<TaskResponseDto> tasks;
}
