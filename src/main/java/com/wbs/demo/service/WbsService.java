package com.wbs.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
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
			int weekIdx = 1;
			for(LocalDate d = startDt; !d.isAfter(endDt); d = d.plusDays(1)) {
				dates.add(DateDto.fromDate(d, weekIdx));
				if(d.getDayOfWeek().getValue() == 6) {
					weekIdx++;
					//week정리
				}
			}
			
			dateInfo = DateInfoDto.builder()
					.dates(dates)
					.build();
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return dateInfo;
	}
}
