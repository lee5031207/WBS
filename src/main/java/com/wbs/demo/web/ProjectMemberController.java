package com.wbs.demo.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wbs.demo.dto.projectMember.ProjectMemberCreateDto;
import com.wbs.demo.dto.projectMember.ProjectMemberResponseDto;
import com.wbs.demo.dto.projectMember.ProjectMemberUpdateDto;
import com.wbs.demo.service.ProjectMemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects/{projectId}/members")
@Tag(name = "projectMember", description = "projectMember API")
public class ProjectMemberController {
	
	private final ProjectMemberService prjMemSvc;
	
	@GetMapping(value= "/{id}")
	@Operation(summary = "프로젝트 멤버 조회", description = "프로젝트 멤버 조회 API")
	public ResponseEntity<ProjectMemberResponseDto> getPrjMemberById(
			@PathVariable("projectId") Long projectId,
			@PathVariable("id") Long id){
		ProjectMemberResponseDto prjMem = prjMemSvc.findById(id);
		
		if(!prjMem.getProject().getProjectId().equals(projectId)) {
			throw new IllegalArgumentException("이 사용자는 해당 프로젝트에 속하지 않습니다.");
		}
		
		return ResponseEntity.ok(prjMem);
	}

	@GetMapping()
	@Operation(summary = "프로젝트 멤버 목록 조회", description = "프로젝트 멤버 목록 조회 API")
	public ResponseEntity<List<ProjectMemberResponseDto>> getPrjMemberList(
			@PathVariable("projectId") Long projectId){
		List<ProjectMemberResponseDto> prjMems = prjMemSvc.getPrjMember(projectId);
		return ResponseEntity.ok(prjMems);
	}
	
	@PostMapping()
	@Operation(summary = "프로젝트 멤버 생성", description = "프로젝트 멤버 생성 API")
	public ResponseEntity<ProjectMemberResponseDto> createPrjMember(
			ProjectMemberCreateDto request,
			@PathVariable("projectId") Long projectId,
			@AuthenticationPrincipal User user){
		ProjectMemberResponseDto savedPrjMem = prjMemSvc.createPrjMember(request, projectId);
		return ResponseEntity.ok(savedPrjMem);
	}
	
	@PatchMapping(value= "/{id}")
	@Operation(summary = "프로젝트 멤버 수정", description = "프로젝트 멤버 수정 API")
	public ResponseEntity<ProjectMemberResponseDto> updatePrjMember(
			@PathVariable("projectId") Long projectId,
			ProjectMemberUpdateDto request){
		
		ProjectMemberResponseDto prjMem = prjMemSvc.findById(request.getPrjMemId());
		if(!prjMem.getProject().getProjectId().equals(projectId)) {
			throw new IllegalArgumentException("이 사용자는 해당 프로젝트에 속하지 않습니다.");
		}
		
		ProjectMemberResponseDto updatedPrjMem = prjMemSvc.updatePrjMember(request);
		return ResponseEntity.ok(updatedPrjMem);
	}
	
	@DeleteMapping()
	@Operation(summary = "프로젝트 멤버 삭제", description = "프로젝트 멤버 삭제 API")
	public ResponseEntity<?> deletePrjMember(@PathVariable("id") Long id){
		prjMemSvc.deletePrjMem(id);
		return ResponseEntity.ok("프로젝트 멤버 삭제 완료");
	}
	 
	 
	
}
