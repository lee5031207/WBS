package com.wbs.demo.dto;

import java.time.LocalDate;
import java.util.List;

import com.wbs.demo.domain.Task;
import com.wbs.demo.dto.project.ProjectResponseDto;
import com.wbs.demo.dto.projectMember.ProjectMemberResponseDto;
import com.wbs.demo.dto.task.TaskResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
	private String type; // [CREATE, UPDATE, DELETE]
	private Long projectId;
	private String logonId;
	private TaskResponseDto task;
}
