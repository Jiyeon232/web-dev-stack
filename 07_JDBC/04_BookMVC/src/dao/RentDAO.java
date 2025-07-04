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
import vo.Rent;

public class RentDAO {
	
	private static RentDAO instance = new RentDAO();
	
	private RentDAO() {
		try {
			Class.forName(ServerInfo.DRIVER);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static RentDAO getInstance() {
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

	// 7. 책 대여
	public void rentBook(String id, int bookNo) throws SQLException {
		Connection connect = getConnect();
		
		String query = "INSERT INTO rent(id, book_no) VALUES(?, ?)";
		PreparedStatement ps = connect.prepareStatement(query);
		ps.setString(1, id);
		ps.setInt(2, bookNo);
		ps.executeUpdate();
		
		close(ps, connect);
	}
	
	// 8. 내가 대여한 책 조회
	public ArrayList<Rent> printRentBook(String id) throws SQLException{
		Connection connect = getConnect();
		
		String query = "SELECT * FROM rent JOIN book USING(book_no) WHERE id = ?";
		PreparedStatement ps = connect.prepareStatement(query);
		ps.setString(1, id);
		
		List<Rent> list = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Rent rent = new Rent();
			rent.setRentNo(rs.getInt("rent_no"));
			rent.setId(rs.getString("id"));
			rent.setBookNo(rs.getInt("book_no"));
			rent.setRentDate(rs.getDate("rent_date").toLocalDate());
			// rent 테이블과 book 테이블을 조인해서 Book 객체를 담을 수 있음
			rent.setBook(new Book(rs.getInt("book_No"), rs.getString("title"), rs.getString("author"), rs.getInt("access_age")));
			list.add(rent);
		}
		close(rs, ps, connect);
		return (ArrayList<Rent>) list;
	}

	// 내가 대여한 책 제목으로 rent_no값 조회
	public int selectRent(String title, String id) throws SQLException {
		Connection connect = getConnect();

		String query = "SELECT rent_no FROM rent JOIN book USING(book_no) WHERE title = ? AND id = ?";
		PreparedStatement ps = connect.prepareStatement(query);
		ps.setString(1, title);
		ps.setString(2, id);

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return rs.getInt("rent_no");
		}
		return -1;
	}

	// 9. 대여 취소
	public void deleteRent(int rentNo) throws SQLException {
		Connection connect = getConnect();

		String query = "DELETE FROM rent WHERE rent_no = ?";
		PreparedStatement ps = connect.prepareStatement(query);
		ps.setInt(1, rentNo);
		ps.executeUpdate();

		close(ps, connect);
	}
	
}
