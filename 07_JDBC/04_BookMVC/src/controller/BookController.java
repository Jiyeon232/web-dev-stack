package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.BookDAO;
import vo.Book;

public class BookController {
	
	private BookDAO dao = BookDAO.getInstance();

	// 1. 전체 책 조회
	public ArrayList<Book> printBookAll() {
		try {
			return dao.printBookAll();
		} catch (SQLException e) {
			return null;
		}
	}
	
	// 2. 책 등록
	public String registerBook(String title, String author, int accessAge) {
		try {
			// 기존 제목, 저자, 제한 나이까지 동일한 책이 있으면 안되게!
			boolean check = dao.checkBook(title, author, accessAge);
			if (check) {
				return "이미 등록된 책입니다.";
			}
			
			return dao.registerBook(title, author, accessAge);
			
		} catch (SQLException e) {
			return "등록 실패!";
		}
	}
	
	// 3. 책 삭제
	public boolean sellBook(int bookNo) {
		// 빌려있는 책은 삭제 못하게!
		return false;
	}
}
