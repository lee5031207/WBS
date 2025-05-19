package com.wbs.demo.dto.projectMember;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wbs.demo.domain.ProjectMember;
import com.wbs.demo.domain.ProjectRole;
import com.wbs.demo.dto.part.PartResponseDto;
import com.wbs.demo.dto.project.ProjectResponseDto;
import com.wbs.demo.dto.task.TaskResponseDto;
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
public class ProjectMemberResponseDto {

	private Long prjMemId;
	private UserResponseDto user;
	private ProjectResponseDto project;
	private ProjectRole projectRole;
	private PartResponseDto part;
	private String partNm;
	private List<TaskResponseDto> tasks;
	
	public static ProjectMemberResponseDto fromSimple(ProjectMember projectMember) {
		return ProjectMemberResponseDto
				.builder()
				.prjMemId(projectMember.getPrjMemId())
				.user(UserResponseDto.fromSimple(projectMember.getUser()))
				.projectRole(projectMember.getProjectRole())
				.partNm(
						projectMember.getPart() != null
							? projectMember.getPart().getPartNm()
							: null
						)
				.build();
	}
	
	public static ProjectMemberResponseDto fromDetail(ProjectMember projectMember) {
		
		List<TaskResponseDto> tasks = projectMember.getTasks().stream()
				.map(TaskResponseDto::fromSimple)
				.collect(Collectors.toList());
		
		return ProjectMemberResponseDto
				.builder()
				.prjMemId(projectMember.getPrjMemId())
				.projectRole(projectMember.getProjectRole())
				.user(UserResponseDto.fromSimple(projectMember.getUser()))
				.project(ProjectResponseDto.fromSimple(projectMember.getProject()))
				.part(
						projectMember.getPart() != null
							? PartResponseDto.fromSimple(projectMember.getPart())
							: null
						)
				.tasks(tasks)
				.build();
	}
}
