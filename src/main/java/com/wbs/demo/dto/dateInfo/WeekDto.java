package com.wbs.demo.dto.dateInfo;

import com.fasterxml.jackson.annotation.JsonInclude;

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
public class WeekDto {

	private String label;
	private int weekIdx;
	private String startDate;
	private String endDate;
	private int dateCnt;
	
}
