package com.kh.upload.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.kh.upload.model.dto.BoardDTO;
import com.kh.upload.model.dto.PagingDTO;
import com.kh.upload.model.vo.Board;
import com.kh.upload.service.BoardService;

@Controller
public class BoardController {
	
	private String path = "\\\\192.168.0.35\\upload\\";
	
	@Autowired
	private BoardService service;

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	// 중복되는 메서드 분리!
	public String fileUpload(MultipartFile file) {
		// 중복 방지를 위한 UUID 적용
		UUID uuid = UUID.randomUUID();
		// System.out.println(uuid.toString());
		
		String fileName = uuid.toString() + "_" + file.getOriginalFilename();
		//System.out.println(fileName);
		File copyFile = new File(path + fileName);
		
		try {
			file.transferTo(copyFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	@PostMapping("/upload")
	public String upload(MultipartFile file) {
		System.out.println("파일 이름 : " + file.getOriginalFilename());
		System.out.println("파일 사이즈 : " + file.getSize());
		System.out.println("파일 파라미터명 : " + file.getName());
		
		String fileName = fileUpload(file);
		// http://localhost:8081/ + fileName <- url
		return "redirect:/";
	}
	
	// List<MultipartFile>
	@PostMapping("/multiUpload")
	public String multiUpload(List<MultipartFile> files) {
		for (MultipartFile file : files) {
			String fileName = fileUpload(file);
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/list")
	public String list(Model model, PagingDTO paging) {
		List<BoardDTO> list = service.selectBoard(paging);
		//System.out.println(list); // 리스트 받아오는지 확인
		//System.out.println(paging); // PagingDTO 값이 바뀌는지 확인
		//System.out.println("keyword : " + paging.getKeyword()); // 키워드 받아오는지 확인
		model.addAttribute("list", list);
		model.addAttribute("paging", new PagingDTO(paging.getPage(), service.page(paging.getKeyword())));
		
		return "list";
	}
	
	@PostMapping("/write")
	public String write(BoardDTO dto) {
		//System.out.println(dto.getTitle());
		//System.out.println(dto.getContent());
		//System.out.println(dto.getFile());
		
		// 이미지 업로드 추가
		// 추가한 파일 -> url로 변환!
		String fileName = fileUpload(dto.getFile());
		
		// board 테이블에 데이터 추가
		Board vo = new Board();
		vo.setTitle(dto.getTitle());
		vo.setContent(dto.getContent());
		vo.setUrl(fileName);
		service.insertBoard(vo);
		
		//System.out.println(vo);
		
		return "redirect:/view?no=" + vo.getNo();
	}
	
	// view?no=${item.no} -> view.jsp 데이터 보여주기
	@GetMapping("/view")
	public String view(Model model, int no) {
		//System.out.println(no);
		Board board = service.searchBoard(no);
		model.addAttribute("board", board);
		return "view";
	}
	
	@PostMapping("/update")
	public String update(BoardDTO dto) {
		/*
		// 기존 파라미터타입 (Board vo) 유지하면서 수정하기
		// 파일만 dto로 받아와서 vo.setUrl()로 새로운 url 넣어주기
		System.out.println(vo.getTitle());
		System.out.println(vo.getContent());
		System.out.println("기존 url : " + vo.getUrl());
		System.out.println(dto.getFile());
		String url = fileUpload(dto.getFile());
		System.out.println("url : " + url);
		vo.setUrl(url);
		System.out.println("수정 url : " + vo.getUrl());
		service.updateBoard(vo);
		*/
		
		// 새로운 파일로 수정 -> 기존 파일은 삭제하고 해당 파일을 업로드 하고 DB URL을 수정
		System.out.println(dto.getFile().isEmpty());
		if (!dto.getFile().isEmpty()) {
			// 1. 파일이 비어있지 않다면 기존 파일 삭제
			File file = new File(path + dto.getUrl());
			file.delete();
			
			// 2. 해당 파일 업로드 -> 새로운 파일의 url의 파일명
			String url = fileUpload(dto.getFile());
			dto.setUrl(url);
		}
		
		System.out.println(dto.getUrl());
		// 3. 해당 no에 따른 데이터들 수정
		service.updateBoard(dto);
		
		return "redirect:/view?no=" + dto.getNo();
	}
	
	@GetMapping("/delete")
	public String delete(int no) {
		// 이미지가 있는 경우 삭제
		// 기존 url 컬럼에 값이 필요하지 않을까?
		// -> no로 하나 정보 가지고 오는 기능 만들어놓지 않았나요?
		Board board = service.searchBoard(no);
		System.out.println(board.getUrl());
		
		File file = new File(path + board.getUrl());
		file.delete();
		
		service.deleteBoard(no);
		return "redirect:/list";
	}
	
}
