package com.wbs.demo.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wbs.demo.domain.User;
import com.wbs.demo.dto.team.TeamResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto {
	
	private Long userId;
	private String loginId;
	private String userNm;
	private String email;
	private TeamResponseDto team;
	private String role;
}
