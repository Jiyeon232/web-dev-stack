package controller;

import java.sql.SQLException;

import dao.MemberDAO;
import vo.Member;

public class MemberController {
	
	private MemberDAO dao = MemberDAO.getInstance();

	// 4. 회원가입
	public String register(Member member) {
		// id가 primary key 에러가 납니다! 활용! -> 증복체크!
		try {
			dao.register(member);
			return member.getName() + "님이 회원가입하셨습니다!";
		} catch (SQLException e) {
			return "기존 회원 아이디가 있습니다. 다른 아이디로 가입해주세요.";
		}
	}
	
	// 5. 로그인
	public Member login(String id, String pwd) {
		try {
			return dao.login(id, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 6. 회원탈퇴
	public boolean delete(String id) {
		// 회원탈퇴시 대여중인 책을 모두 기록 삭제
		// MYSQL에서 FOREIGN KEY에 ON DELETE CASCADE 걸기!
		try {
			dao.delete(id);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
}
