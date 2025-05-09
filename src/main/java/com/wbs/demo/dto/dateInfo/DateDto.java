package com.wbs.demo.dto.dateInfo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class DateDto {
	
	private String date;
	
	private int weekIdx;
	
	private int dayOfWeek;
	
	public static DateDto fromDate(LocalDate date, int weekIdx) {
		return DateDto.builder()
				.date(date.format(DateTimeFormatter.ISO_LOCAL_DATE))
				.weekIdx(weekIdx)
				.dayOfWeek(date.getDayOfWeek().getValue())
				.build();
	}

}
