package com.wbs.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wbs.demo.domain.User;
import com.wbs.demo.dto.user.UserCreateReqDto;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findById(Long id);
	
	Optional<User> findByloginId(String loginId);
	
	@Query("SELECT u FROM User u WHERE u.userNm LIKE %:userNm%")
	List<User> searchUser(@Param("userNm") String userNm);
	
}
