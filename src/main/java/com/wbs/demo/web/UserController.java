package com.wbs.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	
	@GetMapping(value = "/users")
	public String list() {
		
		return null;
	}
	
}
