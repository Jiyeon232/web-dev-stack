package com.kh.upload.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.upload.mapper.BoardMapper;
import com.kh.upload.model.vo.Board;

@Service
public class BoardService implements BoardMapper {
	
	@Autowired
	private BoardMapper mapper;

	@Override
	public void insertBoard(Board vo) {
		mapper.insertBoard(vo);
	}

	@Override
	public List<Board> selectBoard() {
		return mapper.selectBoard();
	}

	@Override
	public Board searchBoard(int no) {
		return mapper.searchBoard(no);
	}

	@Override
	public void updateBoard(Board vo) {
		mapper.updateBoard(vo);
	}

	@Override
	public void deleteBoard(int no) {
		mapper.deleteBoard(no);
	}
	
}
