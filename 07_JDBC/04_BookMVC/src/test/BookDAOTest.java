package test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.BookDAO;
import vo.Book;

class BookDAOTest {

	private BookDAO dao;
	
	@BeforeEach
	void setUp() {
		dao = BookDAO.getInstance();
	}

	@AfterEach
	void setDown() throws SQLException {
		// 테스트 후에 입력한 정보 지우기
		dao.getConnect().prepareStatement("DELETE FROM book").executeUpdate();
	}
	
	@Test
	void testRegisterPrint() throws SQLException {
		dao.registerBook("오늘의 낙원에서 만나자", "하태완", 15);
		
		ArrayList<Book> list = dao.printBookAll();
		assertEquals(list.get(0).getAuthor(), "하태완");
	}
	
	@Test
	void sellBook() throws SQLException {
		dao.registerBook("오늘의 낙원에서 만나자", "하태완", 15);
		
		ArrayList<Book> list = dao.printBookAll();
		dao.sellBook(list.get(0).getBookNo());
		
		list = dao.printBookAll(); // 삭제하고 다시 한번 호출했을 때
		
		assertEquals(list.size(), 0); // list 크기가 0인지
		assertTrue(list.isEmpty()); // list가 비어있는지 - 둘 중 하나로 체크!
	}
	
}
