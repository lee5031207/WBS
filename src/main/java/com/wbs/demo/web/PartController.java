package com.wbs.demo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wbs.demo.dto.part.PartCreateDto;
import com.wbs.demo.dto.part.PartResponseDto;
import com.wbs.demo.dto.part.PartUpdateDto;
import com.wbs.demo.service.PartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects/{projectId}/parts")
@Tag(name = "Part", description = "Part API")
public class PartController {

	private final PartService partSvc;
	
	@GetMapping(value= "/{id}")
	@Operation(summary = "프로젝트 파트 조회", description = "프로젝트 파트 조회 API")
	public ResponseEntity<PartResponseDto> getPartById(
			@PathVariable("projectId") Long projectId,
			@PathVariable("id") Long id){
		PartResponseDto part = partSvc.findById(id);
		
		if(!part.getProject().getProjectId().equals(projectId)) {
			throw new IllegalArgumentException("이 파트는 해당 프로젝트에 속하지 않습니다.");
		}
		
		return ResponseEntity.ok(part);
	}
	
	@GetMapping
	@Operation(summary = "프로젝트 파트 목록 조회", description = "프로젝트 파트 목록 조회 API")
	public ResponseEntity<List<PartResponseDto>> getPartList(
			@PathVariable("projectId") Long projectId){
		List<PartResponseDto> parts = partSvc.getPart(projectId);
		return ResponseEntity.ok(parts);
	}
	
	@PostMapping
	@Operation(summary = "프로젝트 파트 생성", description = "프로젝트 파트 생성 API")
	public ResponseEntity<PartResponseDto> createPart(
			@PathVariable("projectId") Long projectId,
			@RequestBody PartCreateDto request){
		PartResponseDto savedPpart = partSvc.createPart(request, projectId);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPpart);
	}
	
	@PatchMapping
	@Operation(summary = "프로젝트 파트 수정", description = "프로젝트 파트 수정 API")
	public ResponseEntity<PartResponseDto> updatePart(
			@PathVariable("projectId") Long projectId,
			@RequestBody PartUpdateDto request){
		
		PartResponseDto part = partSvc.findById(request.getPartId());
		if(!part.getProject().getProjectId().equals(projectId)) {
			throw new IllegalArgumentException("이 파트는 해당 프로젝트에 속하지 않습니다.");
		}
		
		PartResponseDto updatedPart = partSvc.updatePart(request, projectId);
		
		return ResponseEntity.ok(updatedPart);
	}
	
	@DeleteMapping
	@Operation(summary = "프로젝트 파트 삭제", description = "프로젝트 파트 삭제 API")
	public ResponseEntity<?> deletePart(@PathVariable("id") Long id){
		partSvc.deletePart(id);
		return ResponseEntity.ok("프로젝트 파트 삭제 완료");
	}
	
	
	
	
}
