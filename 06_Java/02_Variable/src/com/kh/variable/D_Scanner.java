package com.kh.variable;

import java.util.Scanner;

public class D_Scanner {
	
	/*
	 * Scanner
	 * - 화면(콘솔)에서 데이터를 "입력"받는 클래스
	 * 
	 * 1. import java.util.Scanner; 추가
	 * 
	 * 2. Scanner 객체 생성
	 *    Scanner sc = new Scanner(System.in);
	 *    
	 * 3. Scanner 메서드 사용
	 *    입력 값이
	 *       - 문자열일 때 : sc.next() - 공백 있을 경우 공백 이전까지
	 *                    sc.nextLine() - 공백 포함 엔터 전까지
	 *       - 정수일 때 : sc.nextInt()
	 *       - 실수일 때 : sc.nextFloat(), sc.nextDouble()
	 *       
	 *       => 권장하기는 sc.nextLine()만 사용하는 것을 권장!
	 *          얘만 enter 처리
	 * */

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.print("당신의 이름은 무엇입니까? > ");
		String name = sc.next(); // enter 처리 X
		sc.nextLine(); // 남아있는 enter 처리
		
		System.out.print("당신의 나이는 몇살입니까? > ");
		//int age = sc.nextInt(); // enter 처리 X
		//sc.nextLine(); // enter 처리
		
		// String -> int
		int age = Integer.parseInt(sc.nextLine());
		
		System.out.print("당신의 집은 어디십니까? > ");
		String addr = sc.nextLine();
		
		System.out.print("당신의 키는 몇입니까? (소수점 첫째 자리까지) > ");
		//float height = sc.nextFloat();
		//double height = sc.nextDouble();
		//sc.nextLine();
		
		double height = Double.parseDouble(sc.nextLine());
		
		System.out.print("당신의 성별은 무엇입니까 (남/여) > ");
		String input = sc.nextLine();
		char gender = input.charAt(0);
		//char gender = sc.nextLine().charAt(0);
		
		/*System.out.println("당신의 이름은 " + name + 
				               "이고, 나이는 " + age + 
				               "살이고, 사는 곳은 " + addr + 
				               "이고, 키는 " + height + 
				               "cm이고, 성별은 " + gender);*/
		
		// printf 사용
		System.out.printf("당신의 이름은 %s이고, 나이는 %d살이고, 사는 곳은 %s이고, 키는 %.1fcm이고, 성별은 %c ", 
				           name, age, addr, height, gender);
	}

}
