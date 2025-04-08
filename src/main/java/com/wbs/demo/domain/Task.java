package com.wbs.demo.domain;

import java.util.ArrayList;
import java.util.Date;
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
@Table(name = "task")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Task {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="task_id")
	private Long taskId;
	
	@Column(name="task_nm")
	private String taskNm;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Task parentTask;
	
	@OneToMany(mappedBy = "parentTask", fetch=FetchType.LAZY)
	private List<Task> childrenTasks = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_user_id")
	private ProjectUser charge;
	
	@Column(name="depth")
	private int depth;
	
	@Column(name="num")
	private int num;
	
	@Column(name="plan_start_dt")
	private Date planStartDt;
	
	@Column(name="plan_end_dt")
	private Date planEndDt;
	
	@Column(name="plan_progress")
	private int planProgress;

	@Column(name="real_start_dt")
	private Date realStartDt;
	
	@Column(name="real_end_dt")
	private Date realEndDt;
	
	@Column(name="real_progress")
	private int realProgress;
	
	@Column(name="weight")
	private int weight;
	
	@Column(name="remark")
	private String remark;
	

	
	
	
	
	
	
	
	
}
