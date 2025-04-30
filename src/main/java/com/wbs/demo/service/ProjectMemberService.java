package com.wbs.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wbs.demo.domain.Part;
import com.wbs.demo.domain.Project;
import com.wbs.demo.domain.ProjectMember;
import com.wbs.demo.domain.ProjectRole;
import com.wbs.demo.domain.User;
import com.wbs.demo.dto.projectMember.ProjectMemberCreateDto;
import com.wbs.demo.dto.projectMember.ProjectMemberResponseDto;
import com.wbs.demo.dto.projectMember.ProjectMemberUpdateDto;
import com.wbs.demo.repository.PartRepository;
import com.wbs.demo.repository.ProjectMemberRepository;
import com.wbs.demo.repository.ProjectRepository;
import com.wbs.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectMemberService {

	private final ProjectMemberRepository prjMemRepo;
	
	private final ProjectRepository projectRepo;
	
	private final UserRepository userRepo;
	
	private final PartRepository partRepo;
	
	@Transactional(readOnly = true)
	public ProjectMemberResponseDto findById(Long id) {
		ProjectMember prjMem = prjMemRepo.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("프로젝트 멤버 조회 결과가 없습니다."));
		return ProjectMemberResponseDto.fromDetail(prjMem);
	}
	
	@Transactional(readOnly = true)
	public List<ProjectMemberResponseDto> getPrjMember(Long projectId) {
		
		List<ProjectMember> prjmems = prjMemRepo.findByProject(
				projectRepo.findById(projectId)
				.orElseThrow(()-> new IllegalArgumentException("프로젝트 조회 결과가 없습니다.")));
		
		List<ProjectMemberResponseDto> pmrds = new ArrayList<>();
		for(ProjectMember prjMem : prjmems) {
			ProjectMemberResponseDto pmrd = ProjectMemberResponseDto.fromSimple(prjMem);
			pmrds.add(pmrd);
		}
		
		return pmrds;
	}
	
	@Transactional
	public List<ProjectMemberResponseDto> createPrjMember(List<ProjectMemberCreateDto> request, Long projectId) {
		
		List<ProjectMemberResponseDto> savedMembers = new ArrayList<>();
		
		Project project = projectRepo.findById(projectId)
				.orElseThrow(()-> new IllegalArgumentException("프로젝트 조회 결과가 없습니다."));
		
		for(ProjectMemberCreateDto pmcd : request) {
			
			ProjectMember prjMem = new ProjectMember();
			
			prjMem.setProjectRole(ProjectRole.valueOf(pmcd.getProjectRole()));
			
			User user = userRepo.findById(pmcd.getUserId())
					.orElseThrow(()-> new IllegalArgumentException("사용자 조회 결과가 없습니다."));
			
			prjMem.setUser(user);
			
			if(pmcd.getPartId() != null) {
				Part part = partRepo.findById(pmcd.getPartId())
						.orElseThrow(()-> new IllegalArgumentException("작업 파트 조회 결과가 없습니다."));
				prjMem.setPart(part);
			}
			
			prjMem.setProject(project);
			
			ProjectMember savedPrjMem = prjMemRepo.save(prjMem);
			savedMembers.add(ProjectMemberResponseDto.fromSimple(savedPrjMem));
		}
		
		return savedMembers;
	}
	
	@Transactional
	public ProjectMemberResponseDto updatePrjMember(ProjectMemberUpdateDto request, Long id) {
		ProjectMember prjMem = prjMemRepo.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("프로젝트 멤버 조회 결과가 없습니다."));
		
		if(request.getProjectRole() != null) {
			prjMem.setProjectRole(ProjectRole.valueOf(request.getProjectRole()));
		}
		
		if(request.getPartId() != null) {
			Part part = partRepo.findById(request.getPartId())
			.orElseThrow(()-> new IllegalArgumentException("작업 파트 조회 결과가 없습니다."));
			
			prjMem.setPart(part);
		}
		
		ProjectMember updatedprjMem = prjMemRepo.save(prjMem);
		return ProjectMemberResponseDto.fromDetail(updatedprjMem);
	}
	
	@Transactional
	public void deletePrjMem(Long id) {
		prjMemRepo.deleteById(id);
	}
	
}
