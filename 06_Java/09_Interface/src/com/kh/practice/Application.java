package com.kh.practice;

import java.time.LocalDate;
import java.util.Scanner;

import com.kh.practice.controller.UserController;
import com.kh.practice.model.Department;
import com.kh.practice.model.DeptType;
import com.kh.practice.model.UserInfo;

public class Application {
	
	Scanner sc = new Scanner(System.in);
	//UserInfo userInfo = new UserInfo(); --> controller 분리!
	UserController uc = new UserController();
	UserInfo user = new UserInfo();

	public static void main(String[] args) {
		
		Application app = new Application();
		boolean check = true;
		while(check) {
			int select = app.employeeMenu();
			switch(select) {
				case 1:
					app.insertEmp();
					break;
				case 2:
					app.updateEmp();
					break;
				case 3:
					app.printEmp();
					break;
				case 9:
					System.out.println("프로그램을 종료합니다.");
					check = false;
					break;
				default:
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요~");
			}
		}
	}
	
	// 메인 메뉴를 출력하는 메서드
	public int employeeMenu() {
		System.out.println("1. 직원 정보 추가");
		System.out.println("2. 직원 정보 수정");
		System.out.println("3. 직원 정보 출력");
		System.out.println("9. 프로그램 종료");
		System.out.print("메뉴 번호를 누르세요 : ");
		return Integer.parseInt(sc.nextLine());
	}
		
	// 저장할 데이터를 사용자에게 받는 메서드
	public void insertEmp() {
		// setter 사용해서 바로 데이터 입력하는 것도 가능하지만
		// controller 분리할 거라서 변수로 입력받음
		// userInfo.~~ 주석 처리한 부분들이 원래 짠 코드
		
		System.out.print("직원 번호 : ");
		int userNo = Integer.parseInt(sc.nextLine());
		//userInfo.setUserNo(Integer.parseInt(sc.nextLine()));
		System.out.print("직원 아이디 : ");
		String id = sc.nextLine();
		//userInfo.setId(sc.nextLine());
		System.out.print("직원 비밀번호 : ");
		String password = sc.nextLine();
		//userInfo.setPassword(sc.nextLine());
		System.out.print("직원 이메일 : ");
		String email = sc.nextLine();
		//userInfo.setEmail(sc.nextLine());
		System.out.print("직원 이름 : ");
		String name = sc.nextLine();
		//userInfo.setName(sc.nextLine());
		
		System.out.print("추가 정보를 더 입력하시겠습니까?(y/n) : ");
		char addInfo = sc.nextLine().charAt(0);
		
		if (addInfo == 'y') {
			// y일 경우만
			System.out.print("직원 전화번호 : ");
			String phone = sc.nextLine();
			//userInfo.setPhone(sc.nextLine());
			System.out.print("직원 주소 : ");
			String addr = sc.nextLine();
			//userInfo.setAddr(sc.nextLine());
			System.out.print("직원 성별 : ");
			String gender = sc.nextLine();
			//userInfo.setGender(sc.nextLine());
			System.out.print("직원 생일 (예 : 2025-06-23) : "); // LocalDate.parse(문자열) <- 문자열을 날짜로
			LocalDate birthDate = LocalDate.parse(sc.nextLine());
			//userInfo.setBirthDate(LocalDate.parse(sc.nextLine()));
			
			//System.out.print("부서 명 : "); // 너무 어렵다면 생략!
			//String deptName = sc.nextLine();
			//Department department = new Department();
			//department.setDeptName(deptName);
			System.out.print("부서 코드 : ");
			int deptNo = Integer.parseInt(sc.nextLine());
			
			// 추가적으로 들어가는 애들
			//uc.addUser(phone, addr, gender, birthDate, department);
			uc.addUser(phone, addr, gender, birthDate, deptNo);
			
		}
		
		// 기본적으로 들어가는 애들
		uc.addUser(userNo, id, password, email, name);
	}
	
	// 수정할 데이터를 사용자에게 받는 메서드
	public void updateEmp() {
		System.out.println("수정하려면 로그인이 필요합니다.");
		System.out.print("아이디 입력 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 입력 : ");
		String password = sc.nextLine();

		// 로그인! 클라이언트(화면)단에도 유저 정보 임시 저장
		user = uc.login(id, password);

		// 아이디랑 비밀번호가 틀리다면!
		if (user == null) {
			System.out.println("로그인 실패! 정보 수정할 수 없습니다");
			return;
		}

		// 아이디와 비밀번호 성공했을시에만!
		System.out.println("직원의 어떤 정보를 수정하시겠습니까?");
		System.out.println("1. 전화 번호");
		System.out.println("2. 생일");
		System.out.println("3. 부서");
		System.out.println("9. 돌아가기");
		System.out.print("메뉴 번호를 누르세요 : ");
		int select = Integer.parseInt(sc.nextLine());

		switch (select) {
		case 1:
			System.out.print("전화 번호 입력 : ");
			String phone = sc.nextLine();
			user.setPhone(phone);
			break;
		case 2:
			System.out.print("생일 입력 (예 : 2025-06-23) : ");
			LocalDate birthDate = LocalDate.parse(sc.nextLine());
			user.setBirthDate(birthDate);
			break;
		case 3:
			System.out.print("부서 입력 : ");
			String deptName = sc.nextLine();
			Department department = new Department();
			department.setDeptName(deptName);
			user.setDepartment(department);
			break;
		case 9:
			break;
		}

		uc.updateUser(user);

		/*
		 * // 아이디 or 비밀번호가 틀린 경우를 조건 걸어두고 return 하는 것도 가능! // else 조건을 안 걸어도 됨! if
		 * (!userInfo.getId().equals(id)|| !userInfo.getPassword().equals(password)) {
		 * System.out.println("로그인 실패! 정보 수정할 수 없습니다"); return; }
		 */
		/*
		boolean userCheck = uc.getUser().getId().equals(id) && uc.getUser().getPassword().equals(password);
		
		if (userCheck == false) {
			// 아이디랑 비밀번호가 틀리다면!
			System.out.println("로그인 실패! 정보 수정할 수 없습니다");
		} else {			
			// 아이디와 비밀번호 성공했을시에만!
			System.out.println("직원의 어떤 정보를 수정하시겠습니까?");
			System.out.println("1. 전화 번호");
			System.out.println("2. 생일");
			System.out.println("3. 부서");
			System.out.println("9. 돌아가기");
			System.out.print("메뉴 번호를 누르세요 : ");
			int select = Integer.parseInt(sc.nextLine());
			
			switch(select) {
				case 1:
					System.out.print("전화 번호 입력 : ");
					String phone = sc.nextLine();
					uc.updateUser(phone);
					//userInfo.setPhone(phone); // 수정하는 부분
					break;
				case 2:
					System.out.print("생일 입력 (예 : 2025-06-23) : ");
					LocalDate birthDate = LocalDate.parse(sc.nextLine());
					uc.updateUser(birthDate);
					//userInfo.setBirthDate(birthDate);
					break;
				case 3:
					System.out.print("부서 입력 : ");
					String deptName = sc.nextLine();
					Department department = new Department();
					department.setDeptName(deptName);
					uc.updateUser(department);
					//userInfo.setDepartment(department);
					break;
				case 9:
					break;
			}
		}
		*/
	}

	// 데이터를 출력하는 메서드
	public void printEmp() {
		System.out.println(user);
	}
}