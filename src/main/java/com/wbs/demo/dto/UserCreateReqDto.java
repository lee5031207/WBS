package com.wbs.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserCreateReqDto {

	@NotBlank
	private String loginId;
	
	@NotBlank
	private String pwd;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String email;
	
	@NotNull
	private Long teamId;
	
}
