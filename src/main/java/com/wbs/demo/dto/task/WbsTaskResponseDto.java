package com.wbs.demo.dto.task;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wbs.demo.domain.Task;
import com.wbs.demo.dto.project.ProjectResponseDto;
import com.wbs.demo.dto.projectMember.ProjectMemberResponseDto;

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
public class WbsTaskResponseDto {
	
	private Long taskId;
	private int depth;
	private String taskNm;
	private String charge;
	private String partNm;
	private LocalDate planStartDt;
	private LocalDate planEndDt;
	private int planProgress;
	private LocalDate realStartDt;
	private LocalDate realEndDt;
	private int realProgress;
	private int weight;
	
	private List<WbsTaskResponseDto> __children;
	
	public static WbsTaskResponseDto from(Task task) {
		return WbsTaskResponseDto.builder()
				.taskId(task.getTaskId())
				.depth(task.getDepth())
				.taskNm(task.getTaskNm())
				.charge(task.getCharge().getUser().getUserNm())
				.partNm(task.getCharge().getPart().getPartNm())
				.planStartDt(task.getPlanStartDt())
				.planEndDt(task.getPlanEndDt())
				.planProgress(task.getPlanProgress())
				.realStartDt(task.getRealStartDt())
				.realEndDt(task.getRealEndDt())
				.realProgress(task.getRealProgress())
				.weight(task.getWeight())
				.build();
	}
	
}
