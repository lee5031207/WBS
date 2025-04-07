package com.wbs.demo.domain;

import java.util.ArrayList;
import java.util.List;

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
@Table(name = "part")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Part {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="part_id")
	private Long partId;
	
	@Column(name="part_nm")
	private String partNm;
	
	@Column(name="part_desc")
	private String partDesc;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;
	
	@OneToMany(mappedBy = "part" , fetch = FetchType.LAZY)
	private List<ProjectUser> projectUsers = new ArrayList<>();
	
	
	
}
