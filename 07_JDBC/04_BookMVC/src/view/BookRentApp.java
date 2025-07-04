package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.BookController;
import controller.MemberController;
import controller.RentController;
import vo.Book;
import vo.Member;
import vo.Rent;

public class BookRentApp {

	private Scanner sc = new Scanner(System.in);
	
	// 로그인 했을 시 사용자 정보 담을 객체!
	private Member member = null;
	
	private BookController bc = new BookController();
	private MemberController mc = new MemberController();
	private RentController rc = new RentController();
	
	public static void main(String[] args) {
		BookRentApp app = new BookRentApp();
		app.menu();
	}
	
	public void menu() {
		while (true) {
			if (member == null) { // 로그인 X
				menu1();
			} else if (member.getId().equals("admin") && member.getPwd().equals("1234")) {
				// 관리자로 들어온 경우
				menu2();
			} else {
				// 일반 회원인 경우
				menu3();
			}
		}
	}
	
	// 1. 전체 책 조회 - 로그인 X
	// 2. 회원가입 - 로그인 X
	// 3. 로그인 - 로그인 X
	public void menu1() {
		try {
			System.out.println("1. 전체 책 조회");
			System.out.println("2. 회원가입");
			System.out.println("3. 로그인");

			System.out.print("메뉴 선택 > ");
			int select = Integer.parseInt(sc.nextLine());

			switch (select) {
			case 1:
				printBookAll();
				break;
			case 2:
				register();
				break;
			case 3:
				login();
				break;
			}

		} catch (NumberFormatException e) {
			System.out.println("숫자를 입력해주세요");
			menu1();
		}

	}

	// 관리자로 로그인 했을 때
	// 1. 책 등록 - 로그인 O (관리자 : admin, 1234)
	// 2. 책 삭제 - 로그인 O (관리자 : admin, 1234)
	// 3. 로그아웃 - 로그인 O
	public void menu2() {
		try {
			System.out.println("1. 책 등록");
			System.out.println("2. 책 삭제");
			System.out.println("3. 로그아웃");

			System.out.print("메뉴 선택 > ");
			int select = Integer.parseInt(sc.nextLine());

			switch (select) {
			case 1:
				registerBook();
				break;
			case 2:
				sellBook();
				break;
			case 3:
				this.member = null;
				break;
			}

		} catch (NumberFormatException e) {
			System.out.println("숫자를 입력해주세요");
			menu2();
		}

	}

	// 일반회원이 들어왔을 때
	// 1. 회원탈퇴 - 로그인 O (관리자 X)
	// 2. 로그아웃 - 로그인 O
	// 3. 책 대여 - 로그인 O
	// 4. 내가 대여한 책 조회 - 로그인 O
	// 5. 대여 취소 - 로그인 O
	public void menu3() {
		try {
			System.out.println("1. 회원탈퇴");
			System.out.println("2. 로그아웃");
			System.out.println("3. 책 대여");
			System.out.println("4. 내가 대여한 책 조회");
			System.out.println("5. 대여 취소");

			System.out.print("메뉴 선택 > ");
			int select = Integer.parseInt(sc.nextLine());

			switch (select) {
			case 1:
				delete();
				break;
			case 2:
				this.member = null;
				break;
			case 3:
				rentBook();
				break;
			case 4:
				printRentBook();
				break;
			case 5:
				deleteRent();
				break;
			}

		} catch (NumberFormatException e) {
			System.out.println("숫자를 입력해주세요");
			menu3();
		}

	}
	
	// 1. 전체 책 조회
	public void printBookAll() {
		List<Book> list = bc.printBookAll();
		if (list != null && list.size() > 0) {
			for (Book b : list) {
				System.out.println(b);
			}
		} else {
			System.out.println("등록된 책이 없습니다.");
		}
	}
	
	// 2. 회원가입
	public void register() {
		try {
			Member member = new Member();
			System.out.print("아이디 > ");
			member.setId(sc.nextLine());
			System.out.print("이름 > ");
			member.setName(sc.nextLine());
			System.out.print("비밀번호 > ");
			member.setPwd(sc.nextLine());
			System.out.print("나이 > ");
			member.setAge(Integer.parseInt(sc.nextLine()));
			
			System.out.println(mc.register(member));
			
		} catch(NumberFormatException e) {
			System.out.println("나이를 잘못 입력하셨습니다. 다시 입력해주세요");
			register();
		}
	}
	
	// 3. 로그인
	public void login() {
		System.out.print("아이디 > ");
		String id = sc.nextLine();
		System.out.print("비밀번호 > ");
		String pwd = sc.nextLine();
		
		Member member = mc.login(id, pwd);
		
		if (member != null) {
			this.member = member;
			System.out.println(member.getName() + "님이 로그인 하셨습니다!");
		} else {
			System.out.println("로그인 실패..! 아이디와 비밀번호를 다시 입력해주세요");
		}
	}
	
	
	// 관리자로 로그인 했을 때
	// 1. 책 등록
	public void registerBook() {
		System.out.print("책 제목 > ");
		String title = sc.nextLine();
		System.out.print("저자 > ");
		String author = sc.nextLine();
		try {
			System.out.print("연령 제한 > ");
			int accessAge = Integer.parseInt(sc.nextLine());
			
			if (bc.registerBook(title, author, accessAge)) { // --> 자체가 boolean 값!
				System.out.println(title + "등록이 완료되었습니다.");
			} else {
				System.out.println("책 등록에 실패하셨습니다. 다시 등록해주세요!");
			}

		} catch (NumberFormatException e) {
			bc.registerBook(title, author, 0);
			System.out.println(title + " 등록이 완료되었습니다.");
		}
	}

	// 2. 책 삭제
	public void sellBook() {
		printBookAll();
		
		System.out.print("삭제할 책 제목 > ");
		String title = sc.nextLine();

		if (bc.sellBook(title)) {
			System.out.println(title + " 책 삭제 완료!");
		} else {
			System.out.println("책 삭제하는데 실패했습니다 ㅠㅠ");
		}
	}
	
	
	// 일반회원이 들어왔을 때
	// 1. 회원탈퇴
	public void delete() {
		// 이미 로그인 상태라 아이디 입력이 필요 없음!
		mc.delete(this.member.getId());
		this.member = null;
		System.out.println("회원탈퇴 완료!");

		/* 
		// 사용자에게 아이디를 입력받아서 비교할 때
		System.out.print("아이디 > ");
		String id = sc.nextLine();
		
		if (member.getId().equals(id)) {
			mc.delete(id);
			System.out.println("회원탈퇴 완료!");
		} else {
			System.out.println("일치하는 아이디가 없습니다");
		}
		*/
	}
	
	// 3. 책 대여
	public void rentBook() {
		printBookAll();
		
		System.out.print("대여할 책 제목 > ");
		String title = sc.nextLine();

		if (rc.rentBook(member.getId(), title)) {
			System.out.println(this.member.getName() + "님이 " + title + "을 대여하였습니다..");
		} else {
			System.out.println("대여 실패");
		}
	}
	
	// 4. 내가 대여한 책 조회
	public void printRentBook() {
		//ArrayList<Rent> list = rc.printRentBook(this.member.getId());
		String id = member.getId();
		List<Rent> list = rc.printRentBook(id);
		
		if (list.size() > 0) {
			for (Rent r : list) {
				System.out.println(r.getBook());
			}
		} else {
			System.out.println("대여한 책이 없습니다");
		}
	}
	
	// 5. 대여 취소	
	public void deleteRent() {
		printRentBook();
		System.out.print("취소할 대여 책 제목 > ");
		String title = sc.nextLine();
		if(rc.deleteRent(title, this.member.getId())) {
			System.out.println("대여가 취소되었습니다.");
		} else {
			System.out.println("취소하는데 실패하였습니다.");
		}
	}

}
