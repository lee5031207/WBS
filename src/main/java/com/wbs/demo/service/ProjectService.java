package com.wbs.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wbs.demo.domain.InitTask;
import com.wbs.demo.domain.Part;
import com.wbs.demo.domain.Project;
import com.wbs.demo.domain.ProjectMember;
import com.wbs.demo.domain.ProjectRole;
import com.wbs.demo.domain.Task;
import com.wbs.demo.domain.Team;
import com.wbs.demo.domain.User;
import com.wbs.demo.dto.project.ProjectResponseDto;
import com.wbs.demo.dto.project.ProjectUpdateDto;
import com.wbs.demo.dto.project.ProjectCreateDto;
import com.wbs.demo.repository.PartRepository;
import com.wbs.demo.repository.ProjectMemberRepository;
import com.wbs.demo.repository.ProjectRepository;
import com.wbs.demo.repository.TaskRepository;
import com.wbs.demo.repository.TeamRepository;
import com.wbs.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {
	
	private final ProjectRepository projectRepo;
	
	private final TeamRepository teamRepo;
	
	private final UserRepository userRepo;
	
	private final ProjectMemberRepository prjMemRepo;
	
	private final PartRepository partRepo;
	
	private final TaskRepository taskRepo;
	
	private final List<String> initTask = Arrays.stream(InitTask.values())
	        .map(InitTask::getDisplayName)
	        .collect(Collectors.toList());

	
	@Transactional(readOnly = true)
	public ProjectResponseDto findById(Long id) {
		Project project = projectRepo.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("프로젝트 조회 결과가 없습니다."));
		return ProjectResponseDto.fromDetail(project);		
	}
	
	@Transactional(readOnly = true)
	public List<ProjectResponseDto> getProject(String loginId, String role){
		
		List<Project> projects = new ArrayList<>();
		
		if(role.equals("ROLE_ADMIN")) {
			projects = projectRepo.findAll();
		}else if(role.equals("ROLE_USER")){
			//TO-DO 검증 해야함
			User user = userRepo.findByloginId(loginId)
					.orElseThrow(() -> new IllegalArgumentException("사용자 조회 결과가 없습니다."));
			List<ProjectMember> prjMems = prjMemRepo.findByUser(user);
			for(ProjectMember prjMem : prjMems) {
				Project project = prjMem.getProject();
				projects.add(project);
			}
		}
		
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
		
		Part part = new Part();
		part.setPartNm("공통");
		part.setPartDesc("공통 모듈 Part");
		part.setProject(project);
		partRepo.save(part);
		
		ProjectMember prjMem = new ProjectMember();
		prjMem.setProject(project);
		prjMem.setUser(user);
		prjMem.setProjectRole(ProjectRole.PM);
		prjMem.setPart(part);
		prjMemRepo.save(prjMem);
		
		//3. 최초 TASK 생성		
		for (String taskNm : initTask) {
			Task task = new Task();
			task.setCharge(prjMem);
			task.setDepth(0);
			task.setNum(1);
			task.setPlanStartDt(request.getStartDt());
			task.setPlanEndDt(request.getEndDt());
			task.setPlanProgress(100);
			task.setProject(project);
			task.setRemark("WBS 최초 작성");
			task.setTaskNm(taskNm);
			task.setWeight(1);
			taskRepo.save(task);
		}
		
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
