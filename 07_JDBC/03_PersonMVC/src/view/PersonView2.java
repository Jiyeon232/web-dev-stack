package view;

import java.util.List;
import java.util.Scanner;

import controller.PersonController2;
import vo.Person;

public class PersonView2 {
	PersonController2 pc = new PersonController2();
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		PersonView view = new PersonView();
		view.menu();
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
				System.out.println(addPerson());
				break;
			case 2:
				searchAllPerson();
				break;
			case 3:
				searchPerson();
				break;
			case 4:
				System.out.println(updatePerson());
				break;
			case 5:
				System.out.println(removePerson());
				break;
			}
			
		}
	}

	public String addPerson() {
		try {
			System.out.print("이름 > ");
			String name = sc.nextLine();
			System.out.print("나이 > ");
			int age = Integer.parseInt(sc.nextLine());
			System.out.print("주소 > ");
			String addr = sc.nextLine();
			return pc.addPerson(name, age, addr);
		} catch (NumberFormatException e) {
			return "숫자를 입력해주세요~~";
		} 
	}
	
	public void searchAllPerson() {
		List<Person> list = pc.searchAllPerson();
		if (list != null) {
			for(Person p : list) {
				System.out.println(p);
			}
		}
		
	}
	
	public void searchPerson() {
		searchAllPerson();
		System.out.print("아이디 선택 > ");
		int id = Integer.parseInt(sc.nextLine());
		Person person = pc.searchPerson(id);
		if(person!=null) System.out.println(person + "이 조회되었습니다!");
		else System.out.println("아이디가 없습니다..");
	}
	
	public String updatePerson() {
		searchAllPerson();
		
		System.out.print("아이디 > ");
		int id = Integer.parseInt(sc.nextLine());
		
		Person person = pc.searchPerson(id);
		// 아이디가 없는 경우는 내보내기!
		if(person == null) return "조회된 아이디가 없습니다";
		
		// 아이디가 있는 경우만 수정할 수 있도록!
		System.out.print("이름 > ");
		String name = sc.nextLine();
		
		System.out.print("나이 > ");
		int age = Integer.parseInt(sc.nextLine());
		
		System.out.print("주소 > ");
		String addr = sc.nextLine();
		
		return pc.updatePerson(name, age, addr, id);
	}

	public String removePerson() {
		searchAllPerson();
		
		System.out.print("아이디 > ");
		int id = Integer.parseInt(sc.nextLine());
		
		Person person;
		person = pc.searchPerson(id);
		
		// 아이디가 없는 경우는 내보내기!
		if(person == null) return "조회된 아이디가 없습니다";
		// 아이디가 있는 경우만 삭제할 수 있도록!
		return pc.removePerson(id);
	}
}




