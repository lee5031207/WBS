package com.wbs.demo.dto.team;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class TeamUpdateDto {
	
	@NotBlank
	private Long teamId;
	
	private String teamCd;
	private String teamNm;
}
