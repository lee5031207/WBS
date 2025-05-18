package com.wbs.demo.web;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wbs.demo.dto.Message;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WebSocketController {

	private final SimpMessageSendingOperations template;
	
	@MessageMapping("/message")
	public void receiveMessage(Message message){
		String detination = "/sub/projects/" + message.getProjectId();
		template.convertAndSend(detination, message.getMessage());
	}
}
