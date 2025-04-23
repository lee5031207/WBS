package com.wbs.demo.dto.project;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ProjectCreateDto {

	@NotBlank
	private String projectName;
	
	@NotBlank
	private String projectDesc;
	
	@NotBlank
	private LocalDate startDt;
	
	@NotBlank
	private LocalDate endDt;
	
}
