package controller;

import java.sql.SQLException;
import java.util.List;

import dao.PersonDAO;
import vo.Person;

// DAO - Controller - View
public class PersonController2 {

	private PersonDAO dao = PersonDAO.getInstance();

	// person 테이블에 데이터 추가 - INSERT
	public String addPerson(String name, int age, String addr) {
		
		try {
			// 이름, 나이, 주소가 모두 다 같은 경우 "이미 정보가 있습니다." -> 가입 X
			boolean check = dao.checkName(name, age, addr);
			if (check) {
				return "이미 정보가 있습니다.";
			}
			
			return dao.addPerson(name, age, addr);
			
		} catch (SQLException e) {
			return "회원가입 실패 ㅠㅠ" + e.getMessage();
		}
		
	}

	// person 테이블에 있는 데이터 전체 보여주기 - SELECT
	public List<Person> searchAllPerson() {
		try {
			return dao.searchAllPerson();
		} catch (SQLException e) {
			return null;
		}
	}

	// person 테이블에서 데이터 한개만 가져오기 - SELECT -> id로!
	public Person searchPerson(int id) {
		try {
			return dao.searchPerson(id);
		} catch (SQLException e) {
			return null;
		}
	}

	// person 테이블에 데이터 수정 - UPDATE
	public String updatePerson(String name, int age, String addr, int id) {
		try {
			return dao.updatePerson(name, age, addr, id);
		} catch (SQLException e) {
			return "수정 실패했습니다..";
		}
	}

	// person 테이블에 데이터 삭제 - DELETE
	public String removePerson(int id) {
		try {
			if (dao.removePerson(id)) {
				return "아이디가 " + id + "인 회원이 삭제되었습니다. 또 만나요~~";
			}
			return "삭제 실패..";
		} catch (SQLException e) {
			return "관리자에게 물어보세요";
		}
	}
}
