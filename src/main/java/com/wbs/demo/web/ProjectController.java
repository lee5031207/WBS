package com.wbs.demo.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wbs.demo.dto.project.ProjectResponseDto;
import com.wbs.demo.dto.project.projectCreateDto;
import com.wbs.demo.dto.team.TeamCreateDto;
import com.wbs.demo.dto.team.TeamResponseDto;
import com.wbs.demo.service.TeamService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
@Tag(name = "Project", description = "Project API")
public class ProjectController {
	
	@PostMapping
	@Operation(summary = "프로젝트 생성", description = "프로젝트 생성 API")
	public ResponseEntity<ProjectResponseDto> createProject(@RequestBody projectCreateDto request){
		ProjectResponseDto savedProject = null; //TO-DO : 서비스 만들어야함
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
	}
	

}
