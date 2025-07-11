package com.kh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kh.vo.Member;

import config.ServerInfo;

@Repository
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
	public Member login(String id, String pwd) throws SQLException {
		Connection connect = connect();
		
		String query = "SELECT * FROM member WHERE id = ? AND pwd = ?";
		PreparedStatement ps = connect.prepareStatement(query);
		ps.setString(1, id);
		ps.setString(2, pwd);
		
		ResultSet rs = ps.executeQuery();
		Member member = null;
		if (rs.next()) {
			member = new Member(id, pwd, rs.getString("name"), rs.getInt("age"));
		}
		return member;
	}
	
	// 회원 검색
	public Member search(String id) throws SQLException {
		Connection connect = connect();
		
		String query = "SELECT * FROM member WHERE id = ?";
		PreparedStatement ps = connect.prepareStatement(query);
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		Member member = null;
		if (rs.next()) {
			member = new Member(id, rs.getString("pwd"), rs.getString("name"), rs.getInt("age"));
		}
		return member;
	}
	
	// 전체 회원 보기
	public List<Member> resultAll() throws SQLException {
		Connection connect = connect();
		
		String query = "SELECT * FROM member";
		PreparedStatement ps = connect.prepareStatement(query);
		
		ResultSet rs = ps.executeQuery();
		List<Member> list = new ArrayList<>();
		
		while (rs.next()) {
			list.add(new Member(rs.getString("id"), rs.getString("pwd"), rs.getString("name"), rs.getInt("age")));
		}
		return list;
	}
		
}
