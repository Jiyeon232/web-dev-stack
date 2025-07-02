package view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import controller.PersonController;
import model.Person;

public class PersonView {
	
	public static void main(String[] args) {
		
		PersonController pc = new PersonController();
		Scanner sc = new Scanner(System.in);
		
		System.out.print("이름 : ");
		String name = sc.nextLine();
		System.out.print("나이 : ");
		int age = Integer.parseInt(sc.nextLine());
		System.out.print("주소 : ");
		String addr = sc.nextLine();
		
		System.out.print("아이디 선택 : ");
		int selectId = Integer.parseInt(sc.nextLine());
		
		System.out.print("수정할 이름 : ");
		String updateName = sc.nextLine();
		System.out.print("수정할 나이 : ");
		int updateAge = Integer.parseInt(sc.nextLine());
		System.out.print("수정할 주소 : ");
		String updateAddr = sc.nextLine();
		
		// 컨트롤러에서 throws 하고 뷰에서 try - catch 잡는 방법도 가능!
		try {
			// 테스트 용도!
			// 1. INSERT
			pc.addPerson(name, age, addr);
			
			// 2. SELECT ALL
			List<Person> list = pc.searchAllPerson();
			for(Person p : list) {
				System.out.println(p);
			}
			
			// 3. SELECT
			pc.searchPerson(selectId);
			
			// 4. UPDATE
			list = pc.updatePerson(selectId, updateName, updateAge, updateAddr);
			for(Person p : list) {
				System.out.println(p);
			}
			
			// 5. REMOVE
			pc.removePerson(selectId);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
}
