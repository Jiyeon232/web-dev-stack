package com.kh.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kh.mybatis.model.dto.SearchDTO;
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
		
		// 동적 쿼리를 사용하지 않았을 때 -> 조건문으로
		//if (vo.getName()==null) vo.setName(member.getName());
		//if (vo.getAge()==0) vo.setAge(member.getAge());
		
		service.update(vo);
		
		// 동적 쿼리를 사용했을 때 session의 값을 수정된 값으로 바꿔주려면
		Member result = service.login(vo);
		session.setAttribute("member", result);
		
		return "redirect:/";
	}
	
	@GetMapping("/delete")
	public String delete(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		service.delete(member.getId());
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/search")
	public String search(SearchDTO dto, Model model) {
		model.addAttribute("list", service.search(dto));
		return "index";
	}
	
}
