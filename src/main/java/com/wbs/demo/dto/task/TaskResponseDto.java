package com.wbs.demo.dto.task;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wbs.demo.dto.project.ProjectResponseDto;
import com.wbs.demo.dto.user.UserResponseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskResponseDto {

	private Long taskId;
	private String taksNm;
	
	private ProjectResponseDto project;
	
	private TaskResponseDto parentTask;
	
	private List<TaskResponseDto> childTasks;
	
	private UserResponseDto charge;
	
	private int depth;
	private int num;
	private Date planStartDt;
	private Date planEndDt;
	private int planProgress;
	private Date realStartDt;
	private Date realEndDt;
	private int realProgress;
	private int weight;
	private String remark;
	
}
