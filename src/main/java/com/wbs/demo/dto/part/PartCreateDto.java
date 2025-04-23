package com.wbs.demo.dto.part;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class PartCreateDto {

	@NotBlank
	private String partNm;
	
	@NotBlank
	private String partDesc;
}
