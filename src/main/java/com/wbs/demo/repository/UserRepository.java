package com.wbs.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wbs.demo.domain.User;
import com.wbs.demo.dto.user.UserCreateReqDto;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findById(Long id);
	
	Optional<User> findByloginId(String loginId);
	
	
}
