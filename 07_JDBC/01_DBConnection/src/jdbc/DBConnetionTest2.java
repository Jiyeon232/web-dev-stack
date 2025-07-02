package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBConnetionTest2 {
	
	// 필요한 것들 상수로 걸어놓기
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/erp";
	public static final String USER = "root";
	public static final String PASSWORD = "qwer1234";

	public static void main(String[] args) {

		try {
			// 1. 드라이버 로딩
			Class.forName(DRIVER);
			System.out.println("드라이버 로딩");
			
			// 2. 데이터베이스 연결 : 스키마 - erp
			Connection connect = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("데이터베이스 연결");
			
			// 3. PreparedStatement 객체 생성 - INSERT - user_info 테이블 사람 한 명 추가 쿼리문 작성
			//PreparedStatement ps = connect.prepareStatement("INSERT INTO user_info(id, password, name) VALUES('userId', 'userPw', 'userName')");
			String query = "INSERT INTO user_info(id, password, name) VALUES(?, ?, ?)";
			PreparedStatement ps = connect.prepareStatement(query);
			
			ps.setString(1, "user03");
			ps.setString(2, "2222");
			ps.setString(3, "김미경");
			
			// 4. 쿼리문 실행 - ps.executeUpdate()
			System.out.println(ps.executeUpdate() + "명 추가!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
