package com.kh.condition;

import java.util.Scanner;

public class A_If {

	Scanner sc = new Scanner(System.in);
	
	/*
	 * if문
	 * 
	 * if(조건식) {
	 *       조건식이 참(true)일 때 실행
	 * } else {
	 *       조건식이 거짓(false)일 때 실행
	 * }
	 * 
	 * - 보통 조건식에는 비교연산자, 논리연산자를 주로 사용
	 * */
	
	public void method1() {
		// 입력받은 성적이 60점 이상이면 "합격입니다" 출력
		// 아니면 "불합격입니다" 출력
		System.out.print("성적 입력 >> ");
		int score = sc.nextInt();
		
		if (score >= 60) {
			System.out.println("합격입니다");
		} else {
			System.out.println("불합격입니다");
		}
		
		// 한 줄인 경우만 중괄호({}) 생략 가능
		if (score >= 60) System.out.println("합격입니다");
		else System.out.println("불합격입니다");
		
		// 삼항 연산자
		System.out.println(score >= 60 ? "합격입니다" : "불합격입니다");
		
	}
	
	public void method2() {
		// 본인의 이름을 입력했을 때 본인이면 "본인이다", 아니면 "본인이 아니다" 출력
		System.out.print("이름 입력 >> ");
		String name = sc.nextLine();
		
		System.out.println("name : " + System.identityHashCode(name));
		System.out.println("박지연 : " + System.identityHashCode("박지연"));
		
		if (name.equals("박지연")) {
			System.out.println("본인이다");
		} else {
			System.out.println("본인이 아니다");
		}
	}
	
	/*
	 * if - else if - else 문
	 * 
	 * if(조건식1) {
	 *       조건식1이 참(true)일 때 실행
	 * } else if(조건식2) {
	 *       조건식 1이 거짓(false)이면서 조건식2이 참(true)일 때 실행
	 * } else {
	 *       조건식1, 조건식2 모두 거짓(false)일 때 실행
	 * }
	 * 
	 * - else if는 수가 제한이 없다
	 * */
	
	/*
	 * 사용자에게 점수(0 ~ 100)를 입력받아서 점수별로 등급 출력
	 * - 90점 이상은 A등급
	 * - 90점 미만 80점 이상은 B등급
	 * - 80점 미만 70점 이상은 C등급
	 * - 70점 미만 60점 이상은 D등급
	 * - 60점 미만은 F등급
	 * */
	public void method3() {
		System.out.print("점수를 입력하세요 >> ");
		int score = sc.nextInt();
		char grade = '\u0000'; // char 값 초기화
		// String grade = ""; -> String 값 초기화
		
		if (score < 0 || score > 100) {
			System.out.println("잘못 입력하셨습니다");
			return;		
		}
		else if (score >= 90) grade = 'A';
		else if (score >= 80) grade = 'B';
		else if (score >= 70) grade = 'C';
		else if (score >= 60) grade = 'D';
		else grade = 'F';
		
		System.out.println(grade + "등급");
		
		/*
		if (score >= 90) {
			System.out.println("A등급");
		} else if (score >= 80) {
			System.out.println("B등급");
		} else if (score >= 70) {
			System.out.println("C등급");
		} else if (score >= 60) {
			System.out.println("D등급");
		} else {
			System.out.println("F등급");
		}
		*/
	}
	
	/*
	 * 세 정수를 입력했을 때 짝수만 출력
	 * 
	 * num1 입력 : 3
	 * num2 입력 : 4
	 * num3 입력 : 8
	 * 4
	 * 8
	 * */
	public void method4() {
		System.out.print("첫번째 정수 입력 >> ");
		int num1 = sc.nextInt();
		System.out.print("두번째 정수 입력 >> ");
		int num2 = sc.nextInt();
		System.out.print("세번째 정수 입력 >> ");
		int num3 = sc.nextInt();

		
		if (num1 % 2 == 0) {
			System.out.println(num1);
		} 
		if (num2 % 2 == 0) {
			System.out.println(num2);
		} 
		if (num3 % 2 == 0) {
			System.out.println(num3);
		}
	}
	
	public static void main(String[] args) {
		A_If a = new A_If();
		//a.method1();
		//a.method2();
		//a.method3();
		a.method4();
	}

}
