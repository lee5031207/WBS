package com.wbs.demo.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter @Setter 
@NoArgsConstructor @AllArgsConstructor
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="longin_id")
	private String loginId;
	
	@Column(name="pwd")
	private String pwd;
	
	@Column(name="user_nm")
	private String userNm;
	
	@Column(name="email")
	private String email;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team team;
	
	@OneToMany(mappedBy = "user" , fetch = FetchType.LAZY)
	private List<ProjectUser> projectUsers = new ArrayList<>();
	
}
