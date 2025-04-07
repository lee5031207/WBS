package com.wbs.demo.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "team")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Team {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="team_id")
	private Long teamId;
	
	@Column(name="team_cd")
	private String teamCd;
	
	@Column(name="team_nm")
	private String teamNm;
	
	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
	private List<User> teamMember = new ArrayList<>();
	
	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
	private List<Project> projectList = new ArrayList<>();
	
}
