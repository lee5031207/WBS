package com.wbs.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wbs.demo.domain.Project;
import com.wbs.demo.domain.Task;
import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
	Optional<Task> findById(Long id);
	List<Task> findByProject(Project project);
	
	@Query("SELECT t FROM Task t LEFT JOIN FETCH t.parentTask WHERE t.project.id = :projectId ORDER BY t.depth, t.num")
	List<Task> findAllByProjectId(@Param("projectId") Long projectId);

}
