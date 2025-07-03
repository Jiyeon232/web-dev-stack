package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.ServerInfo;
import vo.Book;

public class BookDAO {
	
	private static BookDAO instance = new BookDAO();
	
	private BookDAO() {
		try {
			Class.forName(ServerInfo.DRIVER);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static BookDAO getInstance() {
		return instance;
	}
	
	public Connection getConnect() throws SQLException {
		return DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
	}
	
	public void close(PreparedStatement ps, Connection connect) throws SQLException {
		ps.close();
		connect.close();
	}

	public void close(ResultSet rs, PreparedStatement ps, Connection connect) throws SQLException {
		rs.close();
		close(ps, connect);
	}

	// 1. 전체 책 조회
	public ArrayList<Book> printBookAll() throws SQLException {
		Connection connect = getConnect();
		
		String query = "SELECT * FROM book";
		PreparedStatement ps = connect.prepareStatement(query);
		
		ResultSet rs = ps.executeQuery();
		List<Book> list = new ArrayList<>();
		while (rs.next()) {
			Book book = new Book(rs.getInt("book_no"), rs.getString("title"), rs.getString("author"), rs.getInt("access_age"));
			list.add(book);
		}
		close(rs, ps, connect);
		return (ArrayList<Book>) list;
	}
	
	// 동일한 책이 있는지 체크!
	public boolean checkBook(String title, String author, int accessAge) throws SQLException {
		Connection connect = getConnect();
		
		String query = "SELECT * FROM book WHERE title = ? AND author = ? AND access_age = ?";
		PreparedStatement ps = connect.prepareStatement(query);
		ps.setString(1, title);
		ps.setString(2, author);
		ps.setInt(3, accessAge);
		
		ResultSet rs = ps.executeQuery();
		// rs.next() - 존재하면 true, 존재하지 않으면 false
		return rs.next();
	}
	
	// 2. 책 등록
	public String registerBook(String title, String author, int accessAge) throws SQLException {
		Connection connect = getConnect();
		
		String query = "INSERT INTO book(title, author, access_age) VALUES(?, ?, ?)";
		PreparedStatement ps = connect.prepareStatement(query);
		ps.setString(1, title);
		ps.setString(2, author);
		ps.setInt(3, accessAge);
		
		ps.executeUpdate();
		
		close(ps, connect);
		
		return title + "이 등록 되었습니다.";
	}
	
	// 3. 책 삭제
	public void sellBook(int bookNo) {
		
	}
}
