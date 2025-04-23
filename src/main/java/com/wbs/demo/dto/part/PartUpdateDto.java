package com.wbs.demo.dto.part;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class PartUpdateDto {

	@NotNull
	private Long partId;
	
	private String partNm;
	
	private String partDesc;
}
