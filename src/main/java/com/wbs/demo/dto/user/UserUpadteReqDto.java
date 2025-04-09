package com.wbs.demo.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserUpadteReqDto {
	private String pwd;
	private String userNm;
    private String email;
    private Long teamId;
}
