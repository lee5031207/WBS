package com.wbs.demo.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wbs.demo.dto.task.TaskResponseDto;
import com.wbs.demo.service.ProjectService;
import com.wbs.demo.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
@Tag(name = "Task", description = "Task API")
public class TaskController {
	
	private TaskService taskSvc;
	
	@GetMapping()
	@Operation(summary = "작업 목록 조회", description = "작업 목록 조회 API")
	public ResponseEntity<List<TaskResponseDto>> getTaskList(){
		return null;
	}

}
