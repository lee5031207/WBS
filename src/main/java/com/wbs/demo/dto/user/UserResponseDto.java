package com.wbs.demo.dto.user;

import com.wbs.demo.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserResponseDto {
	
	private Long userId;
	private String loginId;
	private String userNm;
	private String email;
	private String teamName;
	private String role;
	
	public UserResponseDto(User user) {
        this.userId = user.getUserId();
        this.loginId = user.getLoginId();
        this.userNm = user.getUserNm();
        this.email = user.getEmail();
        this.teamName = user.getTeam().getTeamNm();
        this.role = user.getRole();
    }
}
