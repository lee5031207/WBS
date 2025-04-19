package com.wbs.demo.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wbs.demo.dto.project.ProjectResponseDto;
import com.wbs.demo.dto.project.ProjectUpdateDto;
import com.wbs.demo.dto.project.ProjectCreateDto;
import com.wbs.demo.dto.team.TeamCreateDto;
import com.wbs.demo.dto.team.TeamResponseDto;
import com.wbs.demo.service.ProjectService;
import com.wbs.demo.service.TeamService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
@Tag(name = "Project", description = "Project API")
public class ProjectController {
	
	private final ProjectService projectSvc;
	
	@GetMapping(value="/{id}")
	@Operation(summary = "프로젝트 조회", description = "프로젝트 조회 API")
	public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable("id") Long id){
		return null;
	}
	
	@GetMapping()
	@Operation(summary = "프로젝트 목록 조회", description = "프로젝트 목록 조회 API")
	public ResponseEntity<List<ProjectResponseDto>> getProjectList(){
		return null;
	}
	
	@PostMapping
	@Operation(summary = "프로젝트 생성", description = "프로젝트 생성 API")
	public ResponseEntity<ProjectResponseDto> createProject(@RequestBody ProjectCreateDto request,
			@AuthenticationPrincipal User user){
		ProjectResponseDto savedProject = projectSvc.createProject(request, user.getUsername());
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
	}
	
	@PatchMapping
	@Operation(summary = "프로젝트 수정", description = "프로젝트 수정 API")
	public ResponseEntity<ProjectResponseDto> updateProject(@RequestBody ProjectUpdateDto request){
		return null;
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "프로젝트 삭제", description = "프로젝트 삭제 API")
	public ResponseEntity<?> deleteProject(@PathVariable("id") Long id){
		return null;
	}
	

}
