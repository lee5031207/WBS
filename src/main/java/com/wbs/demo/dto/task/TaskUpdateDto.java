package com.wbs.demo.dto.task;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class TaskUpdateDto {

	@NotNull
	private Long taskId;
	
	private String taskNm;
	private Long parentTaskId;
	private Long chargeId; //? 좀더 생각해봐야함
	
	private int num;
	private LocalDate planStartDt;
	private LocalDate planEndDt;
	
	private LocalDate realStartDt;
	private LocalDate realEndDt;
	
	private int weight;
	private String remark;
}
