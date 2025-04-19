package com.wbs.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wbs.demo.domain.Project;
import com.wbs.demo.domain.Team;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
	Optional<Project> findById(Long id);
}
