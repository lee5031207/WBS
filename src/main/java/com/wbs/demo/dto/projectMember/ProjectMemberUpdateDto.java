package com.wbs.demo.dto.projectMember;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ProjectMemberUpdateDto {
	
	private String projectRole;
	private Long partId;
	
}
