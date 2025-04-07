package com.wbs.demo.domain;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	private Project project;
	
	private Task parentTask;
	
	private ProjectUser charge;
	
	private int num;
	
	private Date planStartDt;
	
	private Date planEndDt;
	
	private int planProgress;

	private Date realStartDt;
	
	private Date realEndDt;
	
	private int realProgress;
	
	private int weight;
	
	private String remark;

	
	
	
	
	
	
	
	
}
