package com.kh.upload.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.upload.vo.Board;

@Mapper
public interface BoardMapper {

	void insertBoard();
	List<Board> selectBoard();
	List<Board> searchBoard(Board vo);
	void updateBoard(Board vo);
	void deleteBoard(int no);
	
}
