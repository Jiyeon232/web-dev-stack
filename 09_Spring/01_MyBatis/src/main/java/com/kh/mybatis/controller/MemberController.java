package com.kh.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kh.mybatis.model.vo.Member;
import com.kh.mybatis.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;
	
	@GetMapping("/")
	public String index(Model model) {
		List<Member> list = service.allMember();
		model.addAttribute("list", list);
		return "index";
	}
	
	@GetMapping("/register")
	public String register() {
		return "/mypage/register";
	}
	
	@PostMapping("/register")
	public String register(Member vo) {
		service.register(vo);
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "/mypage/login";
	}
	
	@PostMapping("/login")
	public String login(Member vo, HttpServletRequest request) {
		Member member = service.login(vo);
		HttpSession session = request.getSession();
		session.setAttribute("member", member);
		return "redirect:/";
	}
	
	@PostMapping("/update")
	public String update(Member vo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member"); // 기존 정보
		
		// Member vo의 id 값은 null
		vo.setId(member.getId()); // 기존 id 값을 넣어주기
		//System.out.println(vo); // 수정된 값
		service.update(vo);
		session.setAttribute("member", vo);
		return "redirect:/";
	}
	
}
