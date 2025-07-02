package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import config.ServerInfo;

public class DBConnetionTest3 {
	
	public static void main(String[] args) {
		
		try {
			// 1. 드라이버 로딩
			Class.forName(ServerInfo.DRIVER);
			System.out.println("드라이버 로딩");
			
			// 2. 디비 연결
			Connection connect = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
			System.out.println("데이터베이스 연결");
			
			// 3. PreparedStatement - 쿼리 : UPDATE (user_no를 선택해서 email 수정)
			String query = "UPDATE user_info SET email = ? WHERE user_no = ?";
			PreparedStatement ps = connect.prepareStatement(query);
			
			ps.setString(1, "test@naver.com");
			ps.setInt(2, 4);
			
			// 4. 쿼리문 실행 - executeUpdate
			System.out.println(ps.executeUpdate() + "명 수정!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
