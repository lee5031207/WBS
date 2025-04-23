package com.wbs.demo.dto.team;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class TeamCreateDto {
	
	@NotBlank
	private String teamCd;
	
	@NotBlank
	private String teamNm;
	
}
