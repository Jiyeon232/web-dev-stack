package com.kh.practice;

import java.util.Scanner;

public class OperatorPractice {
	
	Scanner sc = new Scanner(System.in);
    
	public static void main(String[] args) {

		OperatorPractice o = new OperatorPractice();
		//o.method1();
		//o.method2();
		//o.method3();
		//o.method4();
		//o.method5();
		//o.method6();
		//o.method7();
		//o.method8();
		
	}

	//모든 사람이 연필을 골고루 나눠가지려고 한다. 인원 수와 연필 개수를 입력 받고 
	//1인당 동일하게 나눠가진 연필 개수와 나눠주고 남은 연필의 개수를 출력하세요.
	public void method1() {
		System.out.print("인원 수 입력 > ");
		int person = sc.nextInt();
		System.out.print("연필 개수 입력 > ");
		int pencil = sc.nextInt();
		
		System.out.println("나눠가진 연필 개수 : " + pencil / person);
		System.out.println("남은 연필 개수 : " + pencil % person);
	}
	
	//입력 받은 숫자를 산술 연산자만 사용해서 십의 자리 이하는 버리는 코드를 작성하세요.
	//만약 432이라면 400, 111이라면 100이 출력됩니다.
	public void method2() {
		System.out.print("숫자를 입력하세요 > ");
		int num = sc.nextInt();
		
		int result = (num / 100) * 100; // 100의 자리만 출력
		//int result = num - (num % 100); // 10의 자리 이하를 버리기
		
		System.out.println(result);
	}

	//3개의 수를 입력 받아 입력 받은 수가 모두 같으면 true, 아니면 false를 출력하세요.
	public void method3() {
		System.out.print("첫번째 숫자를 입력하세요 > ");
		int num1 = sc.nextInt();
		System.out.print("두번째 숫자를 입력하세요 > ");
		int num2 = sc.nextInt();
		System.out.print("세번째 숫자를 입력하세요 > ");
		int num3 = sc.nextInt();
		
		boolean result = (num1 == num2 && num2 == num3) ? true : false; 
		System.out.println(result);
	}

	//입력 받은 하나의 정수가 짝수이면 "짝수다", 짝수가 아니면 "짝수가 아니다"를 출력하세요.
	public void method4() {
		System.out.print("숫자를 입력하세요 > ");
		int num = sc.nextInt();
		
		// 조건문 사용
		if (num % 2 == 0) System.out.println("짝수다");
		else System.out.println("짝수가 아니다");
		
		// 삼항연산자 사용
		String result = (num % 2 == 0) ? "짝수다" : "짝수가 아니다";
		System.out.println(result);
	}

	//주민번호(-포함)를 입력받아 남자인지 여자인지 구분하여 출력하세요
	public void method5() {
		System.out.print("주민번호 입력 > ");
		String no = sc.nextLine();
	    String noStr = no.substring(7,8);
		
	    // switch문 사용
		switch(noStr) {
		case "1" :
		case "3" :
			System.out.println("남자");
			break;
		case "2" :
		case "4" :
			System.out.println("여자");
			break;
		default : System.out.println("잘못 입력했습니다");
		}
		
		// 삼항연산자 사용
		System.out.println(no == "1" || no == "3" ? "남자"
				         : no == "2" || no == "4" ? "여자" : "잘못 입력했습니다.");
	}

	//나이를 입력 받아 어린이(13세 이하)인지, 청소년(13세 초과 ~ 19세 이하)인지, 
	//성인(19세 초과)인지 출력하세요.
	public void method6() {
		System.out.print("나이 입력 > ");
		int age = sc.nextInt();
		
		// 조건문 사용
		if (age > 19) {
			System.out.println("성인");
		} else if (age > 13) {
			System.out.println("청소년");
		} else {
			System.out.println("어린이");
		}
		
		// 삼항연산자 사용
		System.out.println(age > 19 ? "성인" : age > 13 ? "청소년" : "어린이");
	}

	//사과의 개수와 바구니의 크기(바구니 1개의 들어가는 사과의 개수)를 입력받아
	//필요한 바구니의 수를 출력하세요
	public void method7() {
		System.out.print("사과 개수 입력 > ");
		int apple = sc.nextInt();
		System.out.print("바구니 크기 입력 > ");
		int basket = sc.nextInt();
		
		int result = apple / basket;
		
		// 조건문 사용
		if (apple % basket == 0) {
			System.out.println(result + "개");
		} else {
			System.out.println(result + 1 + "개");
		}
		
		// 삼항연산자 사용
		System.out.println(apple % basket == 0 ? result : result + 1 );
	}
	
	//초 단위 시간을 입력받아 1시간 1분 1초 형식으로 출력
	public void method8() {
		System.out.print("초 단위 시간 입력 > ");
		int time = sc.nextInt();
		
		int hour = time / 3600;
		// 입력한 시간(time)에서 시간(hour)을 마이너스 해서 다시 time 변수에 대입하기
		//time = time - hour * 3600;
		//time -= hour * 3600; --> 복합대입연산자 사용
		int minute = (time % 3600) / 60;
		int second = (time % 3600) % 60;
		
		//System.out.println(hour);
		//System.out.println(minute);
		//System.out.println(second);
		
		System.out.println(hour + "시간 " + minute + "분 " + second + "초");
	}
	
}