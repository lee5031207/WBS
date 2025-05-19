package com.wbs.demo.service;

import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import com.wbs.demo.dto.Message;
import com.wbs.demo.dto.task.TaskResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PubMessageService {
	
	private final String destination = "/sub/projects/";
	//STOMP 용도
	private final SimpMessageSendingOperations template;
	
	public void sendMessage(String type, Long projectId, String logonId, TaskResponseDto task) {
		String prjDestination = destination+projectId;
		template.convertAndSend(prjDestination, Message.builder()
				.type(type)
				.projectId(projectId)
				.logonId(logonId)
				.task(task)
				.build());
	}
}
