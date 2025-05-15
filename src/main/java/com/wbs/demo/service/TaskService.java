package com.wbs.demo.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wbs.demo.domain.Project;
import com.wbs.demo.domain.ProjectMember;
import com.wbs.demo.domain.Task;
import com.wbs.demo.dto.task.TaskCreateDto;
import com.wbs.demo.dto.task.TaskResponseDto;
import com.wbs.demo.dto.task.TaskUpdateDto;
import com.wbs.demo.repository.ProjectMemberRepository;
import com.wbs.demo.repository.ProjectRepository;
import com.wbs.demo.repository.TaskRepository;
import com.wbs.demo.web.LoginController;
import com.wbs.demo.web.PartController;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final LoginController loginController;

    private final PartController partController;
	
	private final TaskRepository taskRepo;
	
	private final ProjectRepository projectRepo;
	
	private final ProjectMemberRepository projectMemRepo;

	@Transactional(readOnly = true)
	public TaskResponseDto findById(Long id) {
		Task task = taskRepo.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("작업 조회 결과가 없습니다."));
		return TaskResponseDto.fromDetail(task);
	}
	
	@Transactional(readOnly = true)
	public List<TaskResponseDto> getTask(Long projectId){
		
		List<Task> tasks = taskRepo.findByProject(
				projectRepo.findById(projectId)
				.orElseThrow(()-> new IllegalArgumentException("프로젝트 조회 결과가 없습니다.")));
		
		List<TaskResponseDto> trds = new ArrayList<>();
		for(Task task : tasks) {
			TaskResponseDto trd = TaskResponseDto.fromSimple(task);
			trds.add(trd);
		}
		return trds;
	}
	
	@Transactional
	public TaskResponseDto createTask(TaskCreateDto request, Long projectId) {
		Task task = new Task();
		task.setTaskNm(request.getTaskNm());
		
		if(request.getPlanStartDt().isAfter(request.getPlanEndDt())) {
			throw new IllegalArgumentException("계획 시작일이 종료일보다 늦을 수 없습니다.");
		}
		
		task.setPlanStartDt(request.getPlanStartDt());
		task.setPlanEndDt(request.getPlanEndDt());
		task.setPlanProgress(calProgress(task.getPlanStartDt(), task.getPlanEndDt()));
		
		task.setWeight(request.getWeight());
		task.setRemark(request.getRemark() != null ? request.getRemark() : "");
		
		Project project = projectRepo.findById(projectId)
				.orElseThrow(()-> new IllegalArgumentException("프로젝트 조회 결과가 없습니다."));
		task.setProject(project);
		
		if (request.getParentTaskId() != null) {	
			Task parentTask = taskRepo.findById(request.getParentTaskId())
					.orElseThrow(()-> new IllegalArgumentException("부모 작업 조회 결과가 없습니다."));
			task.setParentTask(parentTask);
			task.setDepth(parentTask.getDepth()+1);
			
			Integer maxNum = taskRepo.findMaxNumByParentId(task.getParentTask().getTaskId());
			int newNum = (maxNum != null ? maxNum : 1) + 1;
			task.setNum(newNum);
			
		}else {
			//최상위 TASK
			task.setDepth(0);
			task.setNum(1);
		}
		
		ProjectMember projectMember = projectMemRepo.findById(request.getChargeId())
				.orElseThrow(()-> new IllegalArgumentException("담당자 조회 결과가 없습니다."));
		task.setCharge(projectMember);
		
		Task savedTask = taskRepo.save(task);
		return TaskResponseDto.fromDetail(savedTask);
	}
	
	@Transactional
	public TaskResponseDto updateTask(TaskUpdateDto request) {
		Task task = taskRepo.findById(request.getTaskId())
				.orElseThrow(()-> new IllegalArgumentException("작업 조회 결과가 없습니다"));
		
		if(request.getTaskNm() != null) {
			task.setTaskNm(request.getTaskNm());
		}
		
		if(request.getParentTaskId() != null) {
			Task parentTask = taskRepo.findById(request.getParentTaskId())
					.orElseThrow(()-> new IllegalArgumentException("부모 작업 조회 결과가 없습니다."));
			task.setParentTask(parentTask);
			task.setDepth(parentTask.getDepth()+1);
		}
		
		if(request.getChargeId() != null) {
			ProjectMember projectMember = projectMemRepo.findById(request.getChargeId())
					.orElseThrow(()-> new IllegalArgumentException("담당자 조회 결과가 없습니다."));
			task.setCharge(projectMember);
		}
		
		if(request.getNum() != 0) {
			task.setNum(request.getNum());
		}
		
		if(request.getPlanStartDt() != null && request.getPlanEndDt() != null) {
			if(request.getPlanStartDt().isAfter(request.getPlanEndDt())) {
				throw new IllegalArgumentException("계획 시작일이 종료일보다 늦을 수 없습니다.");
			}
			task.setPlanStartDt(request.getPlanStartDt());
			task.setPlanEndDt(request.getPlanEndDt());
			task.setPlanProgress(calProgress(request.getPlanStartDt(), request.getPlanStartDt()));
		}else if(request.getPlanStartDt() != null || request.getPlanEndDt() != null){
			throw new IllegalArgumentException("계획 시작일과 종료일은 모두 입력하거나 모두 생략해야 합니다.");
		}
		
		
		if(request.getRealStartDt() != null) {
			task.setRealStartDt(request.getRealStartDt());
		}
		
		if(request.getRealEndDt() != null) {
			if(task.getRealStartDt() == null) {
				throw new IllegalArgumentException("실제 종료일 입력 시 실제 시작일 입력은 필수 입니다");
			}else {
				if(task.getRealStartDt().isAfter(request.getRealEndDt())) {
					throw new IllegalArgumentException("실제 시작일이 종료일보다 늦을 수 없습니다.");
				}
				task.setRealEndDt(request.getRealEndDt());
				task.setRealProgress(calProgress(request.getRealStartDt(), request.getRealEndDt()));
			}
		}
		
		if(request.getWeight() != 0) {
			task.setWeight(request.getWeight());
		}
		
		if(request.getRemark() != null) {
			task.setRemark(request.getRemark());
		}
		
		Task updatedTask = taskRepo.save(task);
		return TaskResponseDto.fromDetail(updatedTask);
	}
	
	@Transactional
	public void deleteTask(Long id) {
		//taskRepo.deleteById(id);
	}
	
	public List<TaskResponseDto> getChildTaskId(Long id){
		
		Task task = taskRepo.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("작업 조회 결과가 없습니다."));
		List<TaskResponseDto> list = new ArrayList<>();
		list.add(TaskResponseDto.fromSimple(task));
		
		if(task.getChildrenTasks().size()==0) {
			return list;
		}else {
			List<Task> childs = task.getChildrenTasks();
			for (Task child : childs) {
				 list.addAll(getChildTaskId(child.getTaskId())); 
			}
			return list;
		}
	}
	
	//진행도 계산
	public static int calProgress(LocalDate start, LocalDate end) {
		
		LocalDate today = LocalDate.now();
		
		if (today.isBefore(start)) return 0;
	    if (today.isAfter(end)) return 100;
	    
	    long totalDays = ChronoUnit.DAYS.between(start, end) + 1;
	    long passedDays = ChronoUnit.DAYS.between(start, today) + 1;
	    
	    return (int)((double) passedDays / totalDays * 100);
	}
	
}
