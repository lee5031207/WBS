package com.wbs.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wbs.demo.domain.Project;
import com.wbs.demo.domain.Team;
import com.wbs.demo.domain.User;
import com.wbs.demo.dto.project.ProjectResponseDto;
import com.wbs.demo.dto.project.ProjectUpdateDto;
import com.wbs.demo.dto.projectUser.ProjectUserResponseDto;
import com.wbs.demo.dto.project.ProjectCreateDto;
import com.wbs.demo.repository.ProjectRepository;
import com.wbs.demo.repository.TeamRepository;
import com.wbs.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {
	
	private final ProjectRepository projectRepo;
	
	private final TeamRepository teamRepo;
	
	private final UserRepository userRepo;
	
	@Transactional(readOnly = true)
	public ProjectResponseDto findById(Long id) {
		Project project = projectRepo.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("프로젝트 조회 결과가 없습니다."));
		return ProjectResponseDto.fromDetail(project);		
	}
	
	@Transactional(readOnly = true)
	public List<ProjectResponseDto> getProject(){
		List<Project> projects = projectRepo.findAll();
		List<ProjectResponseDto> prds = new ArrayList<>();
		for(Project project : projects) {
			ProjectResponseDto prd = ProjectResponseDto.fromSimple(project);
			prds.add(prd);
		}
		return prds;
	}
	
	@Transactional
	public ProjectResponseDto createProject(ProjectCreateDto request, String createId) {
		Project project = new Project();
		project.setProjectName(request.getProjectName());
		project.setProjectDesc(request.getProjectDesc());
		project.setStartDt(request.getStartDt());
		project.setEndDt(request.getEndDt());
		project.setCreateId(createId);
		
		User user = userRepo.findByloginId(createId)
				.orElseThrow(()-> new IllegalArgumentException("사용자 조회 결과가 없습니다."));
		
		Team team = user.getTeam();
		project.setTeam(team);
		
		Project savedProject = projectRepo.save(project);
		return ProjectResponseDto.fromSimple(savedProject);
	}
	
	@Transactional
	public ProjectResponseDto updateProject(ProjectUpdateDto request) {
		Project project = projectRepo.findById(request.getProjectId())
				.orElseThrow(()-> new IllegalArgumentException("프로젝트 조회 결과가 없습니다."));
		
		if(request.getProjectName() != null) {
			project.setProjectName(request.getProjectName());
		}
		
		if(request.getProjectDesc() != null) {
			project.setProjectDesc(request.getProjectDesc());
		}
		
		if(request.getTeamId() != null) {
			Team team = teamRepo.findById(request.getTeamId())
					.orElseThrow(()-> new IllegalArgumentException("팀(부서) 조회 결과가 없습니다."));
			
			project.setTeam(team);
		}
		
		if(request.getStartDt() != null) {
			project.setStartDt(request.getStartDt());
		}
		
		if(request.getEndDt() != null) {
			project.setEndDt(request.getEndDt());
		}
		
		Project updatedProject = projectRepo.save(project);
		
		return ProjectResponseDto.fromDetail(updatedProject);
	}
	
	@Transactional
	public void deleteProject(Long id) {
		projectRepo.deleteById(id);
	}
	
	
}
