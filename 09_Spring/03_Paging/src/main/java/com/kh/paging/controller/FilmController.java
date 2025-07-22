package com.kh.paging.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.paging.model.dto.PagingDTO;
import com.kh.paging.model.vo.Film;
import com.kh.paging.service.FilmService;

@Controller
public class FilmController {
	
	@Autowired
	private FilmService service;
	
	// localhost:8080/list -> GET
	@GetMapping("/list")
	public String list(Model model, PagingDTO paging) {
		//System.out.println(paging); // PagingDTO 값이 바뀌는지 확인
		List<Film> list = service.showFilm(paging);
		//System.out.println(list); // 리스트 받아오는지 확인
		model.addAttribute("list", list);
		model.addAttribute("paging", new PagingDTO(paging.getPage(), service.total()));
		return "list";
	}
	
}
