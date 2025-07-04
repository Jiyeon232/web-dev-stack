package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.MemberDAO;
import vo.Member;

class MemberDAOTest {
	
	private MemberDAO dao;
	
	// @BeforeEach : 각 테스트 메서드가 실행되기 전에 무조건 실행
	// 보통 DAO나 Service 객체 초기화
	@BeforeEach
	void setUp() {
		dao = MemberDAO.getInstance();
	}
	
	// @AfterEach : 각 테스트 메서드가 실행된 후에 무조건 실행
	@AfterEach
	void setDown() throws SQLException {
		// 테스트 후에 입력한 정보 지우기
		dao.getConnect().prepareStatement("DELETE FROM member").executeUpdate();
	}

	@Test
	void testRegister() throws SQLException {
		// 회원가입 테스트
		Member member = new Member("test01", "테스트01", "pass01", 1);
		dao.register(member);
	}
	
	@Test
	void testLogin() throws SQLException {
		Member member = new Member("test02", "테스트01", "pass01", 1);
		dao.register(member);
		
		member = dao.login("test02", "pass01"); // null인지 아닌지 판별 불가능
		assertNotNull(member); // 해당 값이 null이 아니라면 테스트 통과!
	}
	
	@Test
	void testDelete() throws SQLException {
		Member member = new Member("test03", "테스트01", "pass01", 1);
		dao.register(member);
		
		dao.delete("test03");
		
		// 탈퇴했다면 회원이 없겠지? -> 회원이 없다면 null
		member = dao.login("test03", "pass01");
		assertNull(member); // 이 값이 null일때 통과
	}

}
