package com.wbs.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wbs.demo.domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
	Optional<Task> findById(Long id);
}
