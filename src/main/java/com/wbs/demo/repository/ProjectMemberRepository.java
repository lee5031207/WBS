package com.wbs.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wbs.demo.domain.Project;
import com.wbs.demo.domain.ProjectMember;
import com.wbs.demo.domain.User;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long>{
	Optional<ProjectMember> findById(Long id);
	List<ProjectMember> findByProject(Project project);
	List<ProjectMember> findByUser(User user);
}