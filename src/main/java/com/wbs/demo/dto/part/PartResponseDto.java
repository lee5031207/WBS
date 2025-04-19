package com.wbs.demo.dto.part;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wbs.demo.dto.project.ProjectResponseDto;
import com.wbs.demo.dto.projectUser.ProjectUserResponseDto;
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
public class PartResponseDto {
	private Long partId;
	private String partNm;
	private String partDesc;
	private ProjectResponseDto project;
	private List<ProjectUserResponseDto> projectUsers;
}
