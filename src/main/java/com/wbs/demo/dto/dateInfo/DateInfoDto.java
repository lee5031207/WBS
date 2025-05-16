package com.wbs.demo.dto.dateInfo;

import java.time.LocalDate;
import java.util.List;

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
public class DateInfoDto {
	
	private List<WeekDto> weeks;
	private List<DateDto> dates;
	private LocalDate startDt;
	private LocalDate endDt;
	private int totalDays;
	private int totalWeeks;
	
	
}
