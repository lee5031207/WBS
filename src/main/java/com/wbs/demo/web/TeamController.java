package com.wbs.demo.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wbs.demo.dto.team.TeamCreateDto;
import com.wbs.demo.dto.team.TeamResponseDto;
import com.wbs.demo.dto.team.TeamUpdateDto;
import com.wbs.demo.dto.user.UserCreateReqDto;
import com.wbs.demo.dto.user.UserResponseDto;
import com.wbs.demo.service.TeamService;
import com.wbs.demo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teams")
@Tag(name = "Team", description = "Team API")
public class TeamController {

	private final TeamService teamSvc;
	
	@GetMapping(value = "/{id}")
	@Operation(summary = "팀(부서) 조회", description = "팀(부서) 조회 API")
	public ResponseEntity<TeamResponseDto> getTeamById(@PathVariable("id") Long id){
		TeamResponseDto team = teamSvc.findById(id);
		return ResponseEntity.ok(team);
	}
	
	@GetMapping
	@Operation(summary = "팀(부서) 목록 조회", description = "팀(부서) 목록 조회 API")
	public ResponseEntity<List<TeamResponseDto>> getTeamList(){
		List<TeamResponseDto> trds = teamSvc.getTeam();
		return ResponseEntity.ok(trds);
	}
	
	@PostMapping
	@Operation(summary = "팀(부서) 생성", description = "팀(부서) 생성 API")
	public ResponseEntity<TeamResponseDto> createTeam(@RequestBody TeamCreateDto request) {
		TeamResponseDto savedTeam = teamSvc.createTeam(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedTeam);
	}
	
	@PatchMapping
	@Operation(summary = "팀(부서) 수정", description = "팀(부서) 수정 API")
	public ResponseEntity<TeamResponseDto> updateTeam(@RequestBody TeamUpdateDto request){
		TeamResponseDto updatedTeam = teamSvc.updateTeam(request);
		return ResponseEntity.ok(updatedTeam);
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "팀(부서) 삭제", description = "팀(부서) 삭제 API")
	public ResponseEntity<?> deleteTeam(@PathVariable("id") Long id){
		teamSvc.deleteTeam(id);
		return ResponseEntity.ok("팀(부서) 삭제 완료");
	}
}