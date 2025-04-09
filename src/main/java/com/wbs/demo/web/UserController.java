package com.wbs.demo.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wbs.demo.dto.UserResponseDto;
import com.wbs.demo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "User", description = "User API")
public class UserController {
	
	private final UserService userSvc;
	
	@GetMapping(value = "/{id}")
	@Operation(summary = "사용자 조회", description = "사용자 조회 API")
	public ResponseEntity<UserResponseDto> getUserById(@PathVariable("id") Long id) {
		UserResponseDto user = userSvc.findById(id);
		return ResponseEntity.ok(user);
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
