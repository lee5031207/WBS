package com.wbs.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wbs.demo.domain.Project;
import com.wbs.demo.dto.project.ProjectResponseDto;
import com.wbs.demo.dto.project.projectCreateDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {
	
	@Transactional
	public ProjectResponseDto createProject(projectCreateDto request) {
		
		Project project = new Project();
		project.setProjectName(request.getProjectName());
		project.setProjectDesc(request.getProjectDesc());
		project.setStartDt(project.getStartDt());
		project.setEndDt(request.getEndDt());
		
		project.setCreateId(null);
		
		return null;
	}

}
