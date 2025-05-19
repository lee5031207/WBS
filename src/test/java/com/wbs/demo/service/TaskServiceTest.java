package com.wbs.demo.service;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaskServiceTest {

	@Test
	void testCalProgress() {
		LocalDate start = LocalDate.of(2025, 5, 19);
		LocalDate end = LocalDate.of(2025, 5, 23);
		System.out.println(TaskService.calProgress(start, end));
	}
}
