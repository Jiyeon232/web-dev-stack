package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.BookDAO;
import dao.MemberDAO;
import dao.RentDAO;
import vo.Book;
import vo.Member;
import vo.Rent;

class RentDAOTest {

	private RentDAO dao;
	private MemberDAO member;
	private BookDAO book;

	@BeforeEach
	void setUp() {
		dao = RentDAO.getInstance();
		member = MemberDAO.getInstance();
		book = BookDAO.getInstance();
	}

	@AfterEach
	void setDown() throws SQLException {
		// 테스트 후에 입력한 정보 지우기
		dao.getConnect().prepareStatement("DELETE FROM rent").executeUpdate();
		member.getConnect().prepareStatement("DELETE FROM member").executeUpdate();
		book.getConnect().prepareStatement("DELETE FROM book").executeUpdate();
	}
	
	Member addMember(String id) throws SQLException {
		Member m = new Member(id, "테스트01", "pass01", 1);
		member.register(m);		
		return member.login(id, "pass01");
	}
	
	Book addBook() throws SQLException {
		book.registerBook("오늘의 낙원에서 만나자", "하태완", 15);
		ArrayList<Book> list = book.printBookAll();
		return list.get(0);
	}
	
	@Test
	void testRent() throws SQLException {
		Member member = addMember("test01");
		Book book = addBook();
		
		dao.rentBook(member.getId(), book.getBookNo());
	}
	
	@Test
	void testPrint() throws SQLException {
		Member member = addMember("test02");
		Book book = addBook();
		dao.rentBook(member.getId(), book.getBookNo());
		
		ArrayList<Rent> list = dao.printRentBook(member.getId());
		assertEquals(list.size(), 1);
	}
	
	@Test
	void testDelete() throws SQLException {
		Member member = addMember("test03");
		Book book = addBook();
		dao.rentBook(member.getId(), book.getBookNo());
		
		ArrayList<Rent> list = dao.printRentBook(member.getId());
		
		dao.deleteRent(list.get(0).getRentNo());
	}
	
}
