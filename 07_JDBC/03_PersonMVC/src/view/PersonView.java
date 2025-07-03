package view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import controller.PersonController;
import model.Person;

public class PersonView {
	
	PersonController pc = new PersonController();
	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		PersonView view = new PersonView();
		
		// 컨트롤러에서 throws 하고 뷰에서 try - catch 잡는 방법도 가능!
		try {
			view.menu();
			
		} catch (Exception e) {
			e.printStackTrace();
			view.addPerson();
		}
	}
	
	public void menu() {
		while (true) {
			System.out.println("1. 추가");
			System.out.println("2. 전체 조회");
			System.out.println("3. 아이디 조회");
			System.out.println("4. 수정");
			System.out.println("5. 삭제");
			System.out.print("선택 > ");
			int select = Integer.parseInt(sc.nextLine());
			
			switch(select) {
			case 1: 
				// 1. INSERT
				System.out.println(addPerson());
				break;
			case 2: 
				// 2. SELECT ALL
				searchAllPerson();
				break;
			case 3:
				// 3. SELECT
				searchPerson();
				break;
			case 4:
				// 4. UPDATE
				System.out.println(updatePerson());
				break;
			case 5:
				// 5. REMOVE
				System.out.println(removePerson());
				break;
			}
		}
	}
	
	public String addPerson()  {	
		try {
			System.out.print("이름 : ");
			String name = sc.nextLine();
			System.out.print("나이 : ");
			int age = Integer.parseInt(sc.nextLine());
			System.out.print("주소 : ");
			String addr = sc.nextLine();
			
			return pc.addPerson(name, age, addr);
			
		} catch (NumberFormatException e) {
			return "숫자를 입력해주세요~~";
		}
	}
	
	public void searchAllPerson() {
		try {
			List<Person> list = pc.searchAllPerson();
			for (Person p : list) {
				System.out.println(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void searchPerson() {
		try {
			searchAllPerson(); // 전체 사용자 호출
			
			System.out.print("아이디 선택 : ");
			int selectId = Integer.parseInt(sc.nextLine());
			
			Person person = pc.searchPerson(selectId);
			if (person != null) System.out.println(person + "이 조회되었습니다!");
			else System.out.println("아이디가 없습니다..");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String updatePerson() {		
		try {
			searchAllPerson(); // 전체 사용자 호출
			
			System.out.print("아이디 선택 : ");
			int selectId = Integer.parseInt(sc.nextLine());
			
			Person person;
			person = pc.searchPerson(selectId);
			// 아이디가 없는 경우는 내보내기!
			if (person == null) return "조회된 아이디가 없습니다";
			
			// 아이디가 있는 경우만 수정할 수 있도록!
			System.out.print("수정할 이름 : ");
			String updateName = sc.nextLine();
			System.out.print("수정할 나이 : ");
			int updateAge = Integer.parseInt(sc.nextLine());
			System.out.print("수정할 주소 : ");
			String updateAddr = sc.nextLine();
			
			return pc.updatePerson(selectId, updateName, updateAge, updateAddr);

		} catch (NumberFormatException e) {
			return "숫자를 입력해주세요~~";
		} catch (SQLException e) {
			return "수정 실패했습니다... ㅠㅠ";
		}
	}

	public String removePerson() {
		try {
			searchAllPerson(); // 전체 사용자 호출
			
			System.out.print("아이디 선택 : ");
			int selectId = Integer.parseInt(sc.nextLine());
			
			Person person;
			person = pc.searchPerson(selectId);
			
			// 아이디가 없는 경우는 내보내기!
			if (person == null) return "조회된 아이디가 없습니다";
			// 아이디가 있는 경우만 삭제할 수 있도록!
			pc.removePerson(selectId);
			return "아이디가 " + selectId + "인 회원이 삭제되었습니다. 또 만나요~~";
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "문제가 심각하니 관리자에게 문의해주세요";
	}
}
