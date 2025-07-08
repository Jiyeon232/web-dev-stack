package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.ServerInfo;
import vo.Member;

public class MemberDAO {

	public MemberDAO() {
		
		try {
			// 1. 드라이버 로딩
			Class.forName(ServerInfo.DRIVER);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection connect() throws SQLException {
		// 2. 디비 연결
		return DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
	}
	
	// 회원가입
	public void register(Member member) throws SQLException {
		Connection connect = connect();
		
		String query = "INSERT INTO member VALUES(?, ?, ?, ?)";
		PreparedStatement ps = connect.prepareStatement(query);
		ps.setString(1, member.getId());
		ps.setString(2, member.getName());
		ps.setString(3, member.getPwd());
		ps.setInt(4, member.getAge());
		
		ps.executeUpdate();
	}
	
	// 로그인
	public void login(String id, String pwd) throws SQLException {
		Connection connect = connect();
		
		String query = "SELECT * FROM member WHERE id = ? AND pwd = ?";
		PreparedStatement ps = connect.prepareStatement(query);
		ps.setString(1, id);
		ps.setString(2, pwd);
		
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			if (rs.getString("id").equals(id) && rs.getString("pwd").equals(pwd)) {
				// 로그인 성공
			}
		}
	}
	
	// 회원 검색
	public void search() {
		
	}
	
	// 전체 회원 보기
	public void allMember() {
		
	}
	
	// 로그아웃
	public void logout() {
		
	}
	
}
