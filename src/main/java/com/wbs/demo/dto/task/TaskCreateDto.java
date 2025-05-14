package com.wbs.demo.dto.task;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class TaskCreateDto {

	@NotBlank
	private String taskNm;
	
	private Long parentTaskId;
	
	@NotBlank
	private Long chargeId;
	
	@NotNull
	private LocalDate planStartDt;
	
	@NotNull
	private LocalDate planEndDt;
	
	private int weight;
	private String remark;
	
}
