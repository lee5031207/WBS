package com.wbs.demo.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wbs.demo.dto.task.TaskCreateDto;
import com.wbs.demo.dto.task.TaskResponseDto;
import com.wbs.demo.dto.task.TaskUpdateDto;
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
	
	private final TaskService taskSvc;
	
	
	@GetMapping(value= "/{id}")
	@Operation(summary = "작업 조회", description = "작업 조회 API")
	public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable("id") Long id){
		TaskResponseDto task = taskSvc.findById(id);
		return ResponseEntity.ok(task);
	}
	
	@GetMapping()
	@Operation(summary = "작업 목록 조회", description = "작업 목록 조회 API")
	public ResponseEntity<List<TaskResponseDto>> getTaskList(){
		List<TaskResponseDto> trds = taskSvc.getTask();
		return ResponseEntity.ok(trds);
	}
	
	@PostMapping
	@Operation(summary = "작업 생성", description = "작업 생성 API")
	public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskCreateDto request,
			@AuthenticationPrincipal User user){
		TaskResponseDto savedTask = taskSvc.createTask(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
	}
	
	@PatchMapping
	@Operation(summary = "작업 수정", description = "작업 수정 API")
	public ResponseEntity<TaskResponseDto> updateTask(@RequestBody TaskUpdateDto request,
			@AuthenticationPrincipal User user){
		TaskResponseDto updatedTask = taskSvc.updateTask(request);
		return ResponseEntity.ok(updatedTask);
	}
	
	@DeleteMapping
	@Operation(summary = "작업 삭제", description = "작업 삭제 API")
	public ResponseEntity<?> deleteTask(@PathVariable("id") Long id){
		taskSvc.deleteTask(id);
		return ResponseEntity.ok("작업 삭제 완료");
	}
}
