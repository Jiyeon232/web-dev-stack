package com.kh.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.kh.security.model.vo.User;
import com.kh.security.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping("/register")
	public String register(User vo) {
		service.register(vo);
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String login(String id) {
		service.login(id);
		return "redirect:/";
	}
	
}
