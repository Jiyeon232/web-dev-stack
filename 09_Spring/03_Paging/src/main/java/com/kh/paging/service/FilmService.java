package com.kh.paging.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.paging.mapper.FilmMapper;
import com.kh.paging.model.vo.Film;

@Service
public class FilmService implements FilmMapper {
	
	@Autowired
	private FilmMapper mapper;

	@Override
	public List<Film> showFilm() {
		return mapper.showFilm();
	}

}
