package com.wbs.demo.domain;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "project_member")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Where(clause = "delete_yn = 'N'") //Filter 권고임
public class ProjectMember {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="project_member_id")
	private Long prjMemId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;
	
	@Enumerated(EnumType.STRING)
    private ProjectRole projectRole;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "part_id")
	private Part part;
	
	@OneToMany(mappedBy = "charge", fetch=FetchType.LAZY)
	private List<Task> tasks = new ArrayList<>();
	
	@Column(name = "delete_yn", nullable = false)
	@ColumnDefault("'N'")
	private String deleteYn;
}
