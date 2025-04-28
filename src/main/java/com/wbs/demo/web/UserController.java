package com.wbs.demo.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wbs.demo.dto.user.UserCreateReqDto;
import com.wbs.demo.dto.user.UserResponseDto;
import com.wbs.demo.dto.user.UserSearchDto;
import com.wbs.demo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
//@SecurityRequirement(name = "bearerAuth")
@Tag(name = "User", description = "User API")
public class UserController {
	
	private final UserService userSvc;
	
	@GetMapping(value = "/{id}")
	@Operation(summary = "사용자 조회", description = "사용자 조회 API")
	public ResponseEntity<UserResponseDto> getUserById(@PathVariable("id") Long id) {
		UserResponseDto user = userSvc.findById(id);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping
	@Operation(summary = "사용자 생성", description = "사용자 생성 API")
	public ResponseEntity<UserResponseDto> createUser(@RequestBody UserCreateReqDto request) {
		UserResponseDto savedUser = userSvc.createUser(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}
	
	@GetMapping
	@Operation(summary = "사용자 검색", description = "사용자 검색 API")
	public ResponseEntity<List<UserResponseDto>> searchUser(@ModelAttribute UserSearchDto request){
		List<UserResponseDto> users = userSvc.searchUser(request);
		System.out.println("커밋 테스트");
		return ResponseEntity.ok(users);
	}
}

/*
 * 200 OK – 조회, 수정 성공
 * 
 * 201 Created – 생성 성공
 * 
 * 204 No Content – 삭제 성공
 * 
 * 400 Bad Request – 파라미터 오류
 * 
 * 401 Unauthorized – 로그인 안됨
 * 
 * 403 Forbidden – 권한 없음
 * 
 * 404 Not Found – 자원 없음
 * 
 * 409 Conflict – 중복 등
 * 
 * 500 Internal Server Error – 서버 터짐
 */
