package com.wbs.demo.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wbs.demo.dto.dateInfo.DateInfoDto;
import com.wbs.demo.dto.task.WbsTaskResponseDto;
import com.wbs.demo.service.TaskService;
import com.wbs.demo.service.WbsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects/{projectId}/wbs")
@Tag(name = "WBS", description = "WBS API")
public class WbsController {
	
	private final WbsService wbsSvc;
	
	@GetMapping(value="/date-info")
	@Operation(summary = "WBS 날짜 정보 조회", description = "WBS 날짜 정보 조회 API")
	public ResponseEntity<DateInfoDto> getWbsDateInfo(
			@PathVariable("projectId") Long projectId){
		DateInfoDto dateInfo = wbsSvc.getDateInfo(projectId);
		return ResponseEntity.ok(dateInfo);
	}
	
	@GetMapping
	@Operation(summary = "WBS 전체 데이터 조회", description = "WBS 전체 데이터 조회 API")
	public ResponseEntity<List<WbsTaskResponseDto>> getWbsData(
			@PathVariable("projectId") Long projectId){
		List<WbsTaskResponseDto> wbsData = wbsSvc.getWbsData(projectId);
		return ResponseEntity.ok(wbsData);
	}
	
	@GetMapping(value="/members/{id}")
	@Operation(summary = "WBS 멤버 별 데이터 조회", description = "WBS 멤버 별 데이터 조회 API")
	public ResponseEntity<List<WbsTaskResponseDto>> getWbsMemberData(
			@PathVariable("projectId") Long projectId,
			@PathVariable("id") Long prjMemId){
		return null;
	}
	
	@GetMapping(value="/parts/{id}")
	@Operation(summary = "WBS 파트 별 데이터 조회", description = "WBS 파트 별 데이터 조회 API")
	public ResponseEntity<List<WbsTaskResponseDto>> getWbsPartData(
			@PathVariable("projectId") Long projectId,
			@PathVariable("id") Long partId){
		return null;
	}
	
}
