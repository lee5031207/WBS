package com.wbs.demo.dto.project;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ProjectUpdateDto {

	@NotBlank
	private Long ProjectId;
	
	private String projectName;
	private String projectDesc;
	private Long teamId;
	private LocalDate startDt;
	private LocalDate endDt;
}
