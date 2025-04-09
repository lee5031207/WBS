package com.wbs.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wbs.demo.domain.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{
	Optional<Team> findById(Long id);
}
