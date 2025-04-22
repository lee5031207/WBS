package com.wbs.demo.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wbs.demo.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects/{projectId}/members")
@Tag(name = "projectMember", description = "projectMember API")
public class ProjectMemberController {

	/*
	 * @GetMapping(value= "/{id}")
	 * 
	 * @Operation(summary = "프로젝트 멤버 조회", description = "프로젝트 멤버 조회 API")
	 * 
	 * @GetMapping()
	 * 
	 * @Operation(summary = "프로젝트 멤버 목록 조회", description = "프로젝트 멤버 목록 조회 API")
	 * 
	 * @PostMapping()
	 * 
	 * @Operation(summary = "프로젝트 멤버 생성", description = "프로젝트 멤버 생성 API")
	 * 
	 * @PatchMapping()
	 * 
	 * @Operation(summary = "프로젝트 멤버 수정", description = "프로젝트 멤버 수정 API")
	 * 
	 * @DeleteMapping()
	 * 
	 * @Operation(summary = "프로젝트 멤버 삭제", description = "프로젝트 멤버 삭제 API")
	 */
	
}
