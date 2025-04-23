package com.wbs.demo.dto.part;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wbs.demo.domain.Part;
import com.wbs.demo.dto.project.ProjectResponseDto;
import com.wbs.demo.dto.projectMember.ProjectMemberResponseDto;
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
	private List<ProjectMemberResponseDto> projectMembers;
	
	public static PartResponseDto fromSimple(Part part) {
		return PartResponseDto
				.builder()
				.partId(part.getPartId())
				.partNm(part.getPartNm())
				.partDesc(part.getPartDesc())
				.build();
	}
	
	public static PartResponseDto fromDetail(Part part) {
		
		List<ProjectMemberResponseDto> projectMembers = part.getProjectMembers().stream()
				.map(ProjectMemberResponseDto::fromSimple)
				.collect(Collectors.toList());
		
		return PartResponseDto
				.builder()
				.partId(part.getPartId())
				.partNm(part.getPartNm())
				.partDesc(part.getPartDesc())
				.project(ProjectResponseDto.fromSimple(part.getProject()))
				.projectMembers(projectMembers)
				.build();
	}
}
