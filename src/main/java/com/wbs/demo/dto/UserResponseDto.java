package com.wbs.demo.dto;

import com.wbs.demo.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserResponseDto {
	
	private Long userId;
	private String loginId;
	private String name;
	private String email;
	private String teamName;
	
	public UserResponseDto(User user) {
        this.userId = user.getUserId();
        this.loginId = user.getLoginId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.teamName = user.getTeam().getTeamNm();
    }
}
