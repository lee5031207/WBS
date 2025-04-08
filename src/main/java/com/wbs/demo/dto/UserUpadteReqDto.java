package com.wbs.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserUpadteReqDto {
	private String pwd;
	private String name;
    private String email;
    private Long teamId;
}
