package view;

import java.util.List;
import java.util.Scanner;

import controller.BookController;
import controller.MemberController;
import controller.RentController;
import vo.Book;
import vo.Member;

public class BookRentApp {

	private Scanner sc = new Scanner(System.in);
	
	// 로그인 했을 시 사용자 정보 담을 객체!
	private Member member = new Member();
	
	private BookController bc = new BookController();
	private MemberController mc = new MemberController();
	private RentController rc = new RentController();
	
	public static void main(String[] args) {
		BookRentApp app = new BookRentApp();
		app.menu();
	}
	
	public void menu() {
		// 1. 전체 책 조회 - 로그인 X
		// 2. 회원가입 - 로그인 X
		// 3. 로그인 - 로그인 X

		// 관리자로 로그인 했을 때
		// 1. 책 등록 - 로그인 O (관리자 : admin, 1234)
		// 2. 책 삭제 - 로그인 O (관리자 : admin, 1234)
		// 3. 로그아웃 - 로그인 O
		
		// 일반회원이 들어왔을 때
		// 1. 회원탈퇴 - 로그인 O (관리자 X)
		// 2. 로그아웃 - 로그인 O
		// 3. 책 대여 - 로그인 O
		// 4. 내가 대여한 책 조회 - 로그인 O
		// 5. 대여 취소 - 로그인 O

		while (true) { 
			try {
				System.out.println("1. 전체 책 조회");
				System.out.println("2. 회원가입");
				System.out.println("3. 로그인");
				
				System.out.print("메뉴 선택 > ");
				int menu = Integer.parseInt(sc.nextLine());

				switch (menu) {
				case 1:
					printBookAll();
					break;
				case 2:
					register();
					break;
				case 3:
					break;
				}

			} catch (NumberFormatException e) {
				System.out.println("숫자를 입력해주세요");
				menu();
			}
		}
		
		/*
		// 관리자로 로그인 했을 때
		System.out.println("1. 책 등록");
		System.out.println(registerBook());
		System.out.println("2. 책 삭제");
		System.out.println("3. 로그아웃");
		
		// 일반회원이 들어왔을 때
		System.out.println("1. 회원탈퇴");
		System.out.println("2. 로그아웃");
		System.out.println("3. 책 대여");
		System.out.println("4. 내가 대여한 책 조회");
		System.out.println("5. 대여 취소");
		*/
		
	}
	
	// 1. 전체 책 조회
	public void printBookAll() {
		List<Book> list = bc.printBookAll();
		if (list != null) {
			for (Book b : list) {
				System.out.println(b);
			}
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
			
			mc.register(member);
			
		} catch(NumberFormatException e) {
			System.out.println("나이를 잘못 입력하셨습니다. 다시 입력해주세요");
			register();
		}
	}
	
	// 관리자로 로그인 했을 때
	// 1. 책 등록
	public String registerBook() {
		System.out.print("책 제목 > ");
		String title = sc.nextLine();
		System.out.print("저자 > ");
		String author = sc.nextLine();
		try {
			System.out.print("나이 제한 > ");
			int accessAge = Integer.parseInt(sc.nextLine());
			return bc.registerBook(title, author, accessAge);
			
		} catch(NumberFormatException e) {
			return bc.registerBook(title, author, 0);
		}
	}
	

}
