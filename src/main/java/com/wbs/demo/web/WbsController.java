package com.wbs.demo.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wbs.demo.dto.dateInfo.DateInfoDto;
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
	
}
