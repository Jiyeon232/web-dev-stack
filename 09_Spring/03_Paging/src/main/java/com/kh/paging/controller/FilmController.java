package com.kh.paging.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.paging.model.vo.Film;
import com.kh.paging.service.FilmService;

@Controller
public class FilmController {
	
	@Autowired
	private FilmService service;
	
	@GetMapping("/")
	public String showFilm() {
		return "/list";
	}
	
	@GetMapping("/list")
	public String showFilm(Model model) {
		List<Film> list = service.showFilm();
		model.addAttribute("list", list);
		return "list";
	}
	
}
