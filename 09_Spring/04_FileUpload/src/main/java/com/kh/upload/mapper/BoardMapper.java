package com.kh.upload.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.upload.model.vo.Board;

@Mapper
public interface BoardMapper {

	void insertBoard(Board vo);
	List<Board> selectBoard();
	Board searchBoard(int no); // 리스트 하나 -> Board 만 있어도 OK
	void updateBoard(Board vo);
	void deleteBoard(int no);
	
}
