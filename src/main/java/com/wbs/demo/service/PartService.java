package com.wbs.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wbs.demo.domain.Part;
import com.wbs.demo.domain.Project;
import com.wbs.demo.dto.part.PartCreateDto;
import com.wbs.demo.dto.part.PartResponseDto;
import com.wbs.demo.dto.part.PartUpdateDto;
import com.wbs.demo.repository.PartRepository;
import com.wbs.demo.repository.ProjectMemberRepository;
import com.wbs.demo.repository.ProjectRepository;
import com.wbs.demo.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PartService {

	private final PartRepository partRepo;
	
	private final ProjectRepository projectRepo;
	
	@Transactional(readOnly = true)
	public PartResponseDto findById(Long id) {
		Part part = partRepo.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("프로젝트 파트 조회 결과가 없습니다"));
		return PartResponseDto.fromDetail(part);
	}
	
	@Transactional(readOnly = true)
	public List<PartResponseDto> getPart(Long projectId){
		
		List<Part> parts = partRepo.findByProject(
					projectRepo.findById(projectId)
					.orElseThrow(()-> new IllegalArgumentException("프로젝트 조회 결과가 없습니다.")));
		
		List<PartResponseDto> prds = new ArrayList<>();
		
		for(Part part : parts) {
			PartResponseDto prd = PartResponseDto.fromSimple(part);
			prds.add(prd);
		}
		return prds;
	}
	
	
	@Transactional
	public PartResponseDto createPart(PartCreateDto request, Long projectId) {
		Part part = new Part();
		part.setPartNm(request.getPartNm());
		part.setPartDesc(request.getPartDesc());
		
		Project project = projectRepo.findById(projectId)
				.orElseThrow(()-> new IllegalArgumentException("프로젝트 조회 결과가 없습니다."));
		part.setProject(project);
		
		Part savedPart = partRepo.save(part);
		return PartResponseDto.fromDetail(savedPart);
	}
	
	@Transactional
	public PartResponseDto updatePart(PartUpdateDto request, Long projectId) {
		
		Part part = partRepo.findById(request.getPartId())
				.orElseThrow(()-> new IllegalArgumentException("프로젝트 파트 조회 결과가 없습니다"));
		
		if(request.getPartNm() != null) {
			part.setPartNm(request.getPartNm());
		}
		
		if(request.getPartDesc() != null) {
			part.setPartDesc(request.getPartDesc());
		}
		
		Part updatedPart = partRepo.save(part);
		return PartResponseDto.fromDetail(updatedPart);
	}
	
	@Transactional
	public void deletePart(Long id) {
		partRepo.deleteById(id);
	}
	
	
	
	
	
	
}
