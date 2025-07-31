package com.kh.upload.service;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.upload.mapper.BoardMapper;
import com.kh.upload.model.dto.BoardDTO;
import com.kh.upload.model.dto.PagingDTO;
import com.kh.upload.model.vo.Board;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper mapper;
	
	public void insertBoard(Board vo) {
		mapper.insertBoard(vo);
	}

	public List<BoardDTO> selectBoard(PagingDTO paging) {
		
		paging.setOffset(paging.getLimit() * (paging.getPage() - 1));
		List<Board> list = mapper.selectBoard(paging);
		
		List<BoardDTO> dtoList = new ArrayList<BoardDTO>();
		for (Board b : list) {
			BoardDTO dto = new BoardDTO();
			dto.setNo(b.getNo());
			dto.setTitle(b.getTitle());
			Date formatDate = Date.from(b.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant());
			dto.setFormatDate(formatDate);
			dtoList.add(dto);
		}
		return dtoList;
	}

	public int page(String keyword) {
		return mapper.page(keyword);
	}
	
	public Board searchBoard(int no) {
		return mapper.searchBoard(no);
	}
	
	public void updateBoard(BoardDTO dto) {
		mapper.updateBoard(dto);
	}
	
	public void deleteBoard(int no) {
		mapper.deleteBoard(no);
	}
	
}
