package com.wbs.demo.dto.task;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
public class TaskResponseDto {

	private Long taskId;
	private String taskNm;
	
	private ProjectResponseDto project;
	
	private TaskResponseDto parentTask;
	
	private List<TaskResponseDto> childTasks;
	
	private ProjectMemberResponseDto charge;
	
	private int depth;
	private int num;
	private LocalDate planStartDt;
	private LocalDate planEndDt;
	private int planProgress;
	private LocalDate realStartDt;
	private LocalDate realEndDt;
	private int realProgress;
	private int weight;
	private String remark;
	
	public static TaskResponseDto fromSimple(Task task) {
		return TaskResponseDto
				.builder()
				.taskId(task.getTaskId())
				.taskNm(task.getTaskNm())
				.depth(task.getDepth())
				.num(task.getNum())
				.planStartDt(task.getPlanStartDt())
				.planEndDt(task.getPlanEndDt())
				.planProgress(task.getPlanProgress())
				.realStartDt(task.getRealStartDt())
				.realEndDt(task.getRealEndDt())
				.realProgress(task.getRealProgress())
				.weight(task.getWeight())
				.remark(task.getRemark())
				.build();
	}
	
	public static TaskResponseDto fromDetail(Task task) {
		
		List<TaskResponseDto> childTasks = task.getChildrenTasks().stream()
				.map(TaskResponseDto::fromSimple)
				.collect(Collectors.toList());
		
		return TaskResponseDto
				.builder()
				.taskId(task.getTaskId())
				.taskNm(task.getTaskNm())
				.depth(task.getDepth())
				.num(task.getNum())
				.planStartDt(task.getPlanStartDt())
				.planEndDt(task.getPlanEndDt())
				.planProgress(task.getPlanProgress())
				.realStartDt(task.getRealStartDt())
				.realEndDt(task.getRealEndDt())
				.realProgress(task.getRealProgress())
				.weight(task.getWeight())
				.remark(task.getRemark())
				.project(ProjectResponseDto.fromSimple(task.getProject()))
				.parentTask(TaskResponseDto.fromSimple(task.getParentTask()))
				.childTasks(childTasks)
				.charge(ProjectMemberResponseDto.fromSimple(task.getCharge()))
				.build();
	}
	
}
