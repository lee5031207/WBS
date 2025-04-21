package com.wbs.demo.dto.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
	private LocalDate startDt;
	private LocalDate endDt;
	private List<ProjectUserResponseDto> projectUsers;
	private List<PartResponseDto> parts;
	private Date createDt;
	private String createId;
	private List<TaskResponseDto> tasks;
	
	public static ProjectResponseDto fromSimple(Project project) {
		return ProjectResponseDto
				.builder()
				.projectId(project.getProjectId())
				.projectName(project.getProjectName())
				.projectDesc(project.getProjectDesc())
				.startDt(project.getStartDt())
				.endDt(project.getEndDt())
				.createDt(project.getCreateDt())
				.createId(project.getCreateId())
				.build();	
	}
	
	public static ProjectResponseDto fromDetail(Project project) {
		
		List<ProjectUserResponseDto> projectUsers = project.getProjectUsers().stream()
				.map(ProjectUserResponseDto::fromSimple)
				.collect(Collectors.toList());
		
		List<PartResponseDto> parts = project.getParts().stream()
				.map(PartResponseDto::fromSimple)
				.collect(Collectors.toList());
		
		List<TaskResponseDto> tasks = project.getTasks().stream()
				.map(TaskResponseDto::fromSimple)
				.collect(Collectors.toList());
		
		return ProjectResponseDto
				.builder()
				.projectId(project.getProjectId())
				.projectName(project.getProjectName())
				.projectDesc(project.getProjectDesc())
				.startDt(project.getStartDt())
				.endDt(project.getEndDt())
				.createDt(project.getCreateDt())
				.createId(project.getCreateId())
				//Detail 추가사항
				.team(TeamResponseDto.fromSimple(project.getTeam()))
				.projectUsers(projectUsers)
				.parts(parts)
				.tasks(tasks)
				.build();	
	}
	
	
	
}
