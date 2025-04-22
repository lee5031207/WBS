package com.wbs.demo.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "project")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Project {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="project_id")
	private Long projectId;
	
	@Column(name="project_name")
	private String projectName;
	
	@Column(name="project_desc")
	private String projectDesc;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team team;
	
	@Column(name="start_dt")
	@Temporal(TemporalType.DATE)
	private LocalDate startDt;
	
	@Column(name="end_dt")
	@Temporal(TemporalType.DATE)
	private LocalDate endDt;
	
	@OneToMany(mappedBy = "project" , fetch = FetchType.LAZY)
	private List<ProjectMember> projectMembers = new ArrayList<>();
	
	@OneToMany(mappedBy = "project" , fetch = FetchType.LAZY)
	private List<Part> parts = new ArrayList<>();
	
	@CreationTimestamp
	@Column(name = "create_dt", updatable = false)
	private Date createDt;
	
	@Column(name="create_id")
	private String createId;
	
	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
	private List<Task> tasks = new ArrayList<>();
	
}
