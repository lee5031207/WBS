package com.wbs.demo.dto.project;

import java.util.Date;

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
	
	@Column(name="start_dt")
	private Date startDt;
	
	@Column(name="end_dt")
	private Date endDt;
	
}
