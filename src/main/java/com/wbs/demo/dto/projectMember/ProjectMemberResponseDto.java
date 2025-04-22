package com.wbs.demo.dto.projectMember;

import java.util.Date;
import java.util.List;

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

	private Long prjUserId;
	private UserResponseDto user;
	private ProjectResponseDto project;
	private ProjectRole projectRole;
	private PartResponseDto part;
	private List<TaskResponseDto> tasks;
	
	public static ProjectMemberResponseDto fromSimple(ProjectMember projectMember) {
		return ProjectMemberResponseDto
				.builder()
				.prjUserId(projectMember.getPrjMemId())
				.projectRole(projectMember.getProjectRole())
				.build();
	}
}
