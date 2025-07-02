package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import config.ServerInfo;
import model.Person;

public class PersonController {
	
	List<Person> list = new ArrayList<>();

	// 리턴 타입이나 파라미터 자유롭게 변경 가능!
	// 메서드 추가 가능!
	
	public PersonController() {
		try {
			// 1. 드라이버 로딩
			Class.forName(ServerInfo.DRIVER);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 고정적으로 반복 -- 디비 연결, 자원 반납
	// 2. 디비 연결
	public Connection getConnect() throws SQLException {
		return DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
	}
	// 5. 자원 반납
	// 오버로딩!
	public void close(PreparedStatement ps, Connection connect) throws SQLException {
		ps.close();
		connect.close();
	}
	
	public void close(ResultSet rs, PreparedStatement ps, Connection connect) throws SQLException {
		rs.close();
		//ps.close();
		//connect.close();
		close(ps, connect); // 위에 두개 합쳐서 하나로 쓸 수 있음!
	}
	
	// -------- 변동적인 반복 : DAO(Database Access Object)
	
	// person 테이블에 데이터 추가 - INSERT
	public void addPerson(String name, int age, String addr) throws SQLException {
		// 2. 데이터베이스 연결 -> 호출!
		Connection connect = getConnect();

		// 3. PreparedStatement 객체 생성 - 쿼리
		String query = "INSERT INTO person(name, age, addr) VALUES(?, ?, ?)";
		PreparedStatement ps = connect.prepareStatement(query);
		ps.setString(1, name);
		ps.setInt(2, age);
		ps.setString(3, addr);

		// 4. 쿼리 실행
		ps.executeUpdate();
		
		// 5. 자원 반납
		close(ps, connect);
	}

	// person 테이블에 있는 데이터 전체 보여주기 - SELECT
	public List<Person> searchAllPerson() throws SQLException {
		Connection connect = getConnect();

		PreparedStatement ps = connect.prepareStatement("SELECT * FROM person");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			String addr = rs.getString("addr");

			list.add(new Person(id, name, age, addr));
		}
		close(rs, ps, connect);
		return list;
	}
	
	// person 테이블에서 데이터 한개만 가져오기 - SELECT -> id로!
	public void searchPerson(int selectId) throws SQLException {
		Connection connect = getConnect();

		String query = "SELECT * FROM person WHERE id = ?";
		PreparedStatement ps = connect.prepareStatement(query);
		ps.setInt(1, selectId);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			String addr = rs.getString("addr");

			System.out.println(id + " / " + name + " / " + age + " / " + addr);
		}
		close(rs, ps, connect);
	}
	
	// person 테이블에 데이터 수정 - UPDATE
	public List<Person> updatePerson(int selectId, String updateName, int updateAge, String updateAddr) 
			throws SQLException {
		Connection connect = getConnect();

		String query = "UPDATE person SET name = ?, age = ?, addr = ? WHERE id = ?";
		PreparedStatement ps = connect.prepareStatement(query);
		ps.setString(1, updateName);
		ps.setInt(2, updateAge);
		ps.setString(3, updateAddr);
		ps.setInt(4, selectId);
		ps.executeUpdate();
		
		list.set((selectId -1), new Person(selectId, updateName, updateAge, updateAddr));
		System.out.println("수정 완료!");
		
		close(ps, connect);
		return list;
	}

	// person 테이블에 데이터 삭제 - DELETE
	public void removePerson(int selectId) throws SQLException {
		Connection connect = getConnect();

		String query = "DELETE FROM person WHERE id = ?";
		PreparedStatement ps = connect.prepareStatement(query);
		ps.setInt(1, selectId);

		ps.executeUpdate();
		
		close(ps, connect);
	}

}
