package com.wbs.demo.dto.project;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
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
	private Long teamId;
	
	@NotBlank
	private LocalDate startDt;
	
	@NotBlank
	private LocalDate endDt;
	
}
