package com.kh.upload.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.upload.model.dto.BoardDTO;
import com.kh.upload.model.dto.PagingDTO;
import com.kh.upload.model.vo.Board;

@Mapper
public interface BoardMapper {

	void insertBoard(Board vo);
	List<Board> selectBoard(PagingDTO paging);
	Board searchBoard(int no); // 리스트 하나 -> Board 만 있어도 OK
	void updateBoard(BoardDTO dto);
	void deleteBoard(int no);
	int page();
	
}
