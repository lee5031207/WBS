package com.wbs.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wbs.demo.domain.Part;
import com.wbs.demo.domain.Project;

public interface PartRepository extends JpaRepository<Part, Long>{
	public Optional<Part> findById(Long partId);
	public List<Part> findByProject(Project project);
}
