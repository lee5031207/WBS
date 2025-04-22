package com.wbs.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wbs.demo.domain.ProjectUser;

@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser, Long>{
	Optional<ProjectUser> findById(Long id);
}
