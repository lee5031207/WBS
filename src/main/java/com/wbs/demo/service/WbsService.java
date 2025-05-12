package com.wbs.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hibernate.type.descriptor.java.LocalDateJavaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wbs.demo.domain.Project;
import com.wbs.demo.dto.dateInfo.DateDto;
import com.wbs.demo.dto.dateInfo.DateInfoDto;
import com.wbs.demo.dto.dateInfo.WeekDto;
import com.wbs.demo.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WbsService {

	private final ProjectRepository projectRepo;
	
	@Transactional(readOnly = true)
	public DateInfoDto getDateInfo(Long projectId) {
		
		DateInfoDto dateInfo = new DateInfoDto();
		
		Project project = projectRepo.findById(projectId)
				.orElseThrow(()-> new IllegalArgumentException("프로젝트 조회 결과가 없습니다."));
		
		try {
			
			LocalDate startDt = project.getStartDt();
			LocalDate endDt = project.getEndDt();
			
			List<WeekDto> weeks = new ArrayList<WeekDto>();
			List<DateDto> dates = new ArrayList<DateDto>();
			List<DateDto> tempDates = new ArrayList<DateDto>();
			int weekIdx = 1;
			for(LocalDate d = startDt; !d.isAfter(endDt); d = d.plusDays(1)) {
				dates.add(DateDto.fromDate(d, weekIdx));
				tempDates.add(DateDto.fromDate(d, weekIdx));
				if(d.getDayOfWeek().getValue() == 6 || d.isEqual(endDt)) {
					//week정리
					weeks.add(WeekDto.builder()
							//.label(tempDates.get(0).getDate().substring(0,7)+"("+weekIdx+"주)")
							.label(weekIdx+"주")
							.weekIdx(weekIdx)
							.startDate(tempDates.get(0).getDate())
							.endDate(tempDates.get(tempDates.size()-1).getDate())
							.dateCnt(tempDates.size())
							.build());
					tempDates.clear();
					weekIdx++;
				}
			}
			
			dateInfo = DateInfoDto.builder()
					.dates(dates)
					.weeks(weeks)
					.build();
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return dateInfo;
	}
}
