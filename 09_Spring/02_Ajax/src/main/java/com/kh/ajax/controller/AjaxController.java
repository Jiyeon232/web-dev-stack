package com.kh.ajax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.ajax.model.vo.Member;
import com.kh.ajax.service.MemberService;

@Controller
public class AjaxController {
	
	private int count = 0;
	
	@Autowired
	private MemberService service;

	@ResponseBody
	@GetMapping("/count")
	public int count() {
		System.out.println("ajax 요청!");
		return ++count;
	}
	
	@ResponseBody
	@GetMapping("/encoding")
	public String encoding(String nickname) {
		System.out.println(nickname);
		return nickname;
	}
	
	@ResponseBody
	@PostMapping("/check")
	public boolean check(String id) {
		//System.out.println(id); // 아이디 값 받아오는지 확인
		//Member member = service.idCheck(id);
		//System.out.println(member); // 중복되는 아이디에 정보가 있는지 확인
		//if (member != null) return true; // 중복되는 정보가 있으면 true, 없으면 false
		
		// boolean 값으로 받아오기 -> 중복되는 정보가 있으면 true, 없으면 false
		System.out.println(service.idBoolCheck(id));
		return service.idBoolCheck(id);
	}
	
	@ResponseBody
	@PostMapping("/signup")
	public void signup(Member vo) {
		System.out.println("id :" + vo.getId());
		System.out.println("pwd : " + vo.getPwd());
	}
	
}
