package com.wbs.demo.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wbs.demo.dto.team.TeamCreateDto;
import com.wbs.demo.dto.team.TeamResponseDto;
import com.wbs.demo.dto.user.UserCreateReqDto;
import com.wbs.demo.dto.user.UserResponseDto;
import com.wbs.demo.service.TeamService;
import com.wbs.demo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Controller
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
	
	@PostMapping
	@Operation(summary = "팀(부서) 생성", description = "팀(부서) 생성 API")
	public ResponseEntity<TeamResponseDto> createUser(@RequestBody TeamCreateDto request) {
		TeamResponseDto savedTeam = teamSvc.createTeam(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedTeam);
	}
	
}
