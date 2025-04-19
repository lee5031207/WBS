package com.wbs.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wbs.demo.domain.Project;
import com.wbs.demo.dto.project.ProjectResponseDto;
import com.wbs.demo.dto.projectUser.ProjectUserResponseDto;
import com.wbs.demo.dto.project.ProjectCreateDto;
import com.wbs.demo.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {
	
	private final ProjectRepository projectRepo;
	
	@Transactional(readOnly = true)
	public ProjectResponseDto findById(Long id) {
		Project project = projectRepo.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("프로젝트 조회 결과가 없습니다."));
		
		List<ProjectUserResponseDto> projectUsers;
		
		
		
		
		
		return null;
		
	}
	
	
	@Transactional
	public ProjectResponseDto createProject(ProjectCreateDto request, String createId) {
		Project project = new Project();
		project.setProjectName(request.getProjectName());
		project.setProjectDesc(request.getProjectDesc());
		project.setStartDt(project.getStartDt());
		project.setEndDt(request.getEndDt());
		project.setCreateId(createId);
		Project savedProject = projectRepo.save(project);
		return ProjectResponseDto.fromSimple(savedProject);
	}
	
}
