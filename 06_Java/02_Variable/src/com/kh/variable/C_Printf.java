package com.kh.variable;

public class C_Printf {

	public static void main(String[] args) {
		// print : 출력만 함 (줄 바꿈 X)
		System.out.print("hello\n");
		// println : 출력 후 줄 바꿈
		System.out.println("hello");
		
		// printf(출력하고자 하는 형식(포맷), 출력하고자 하는 값, 값, ...)
		int iNum1 = 10;
		int iNum2 = 20;
		
		// 10% 20%
		System.out.println(iNum1 + "% " + iNum2 + "%");
		// 출력하고자 하는 값들이 제시한 형식에 맞춰서 출력만 진행
		System.out.printf("%d%% %d%%", iNum1, iNum2);
		
		/*
		 * 포맷에 쓰이는 키워드
		 * %d : 정수
		 * %f : 실수
		 * %c : 문자
		 * %s : 문자열 (문자도 가능)
		 * */
		System.out.println(); // 줄 바꿈 추가
		System.out.printf("%5d\n", iNum1); // 5칸 확보 후 오른쪽 정렬
		System.out.printf("%-5d\n", iNum2); // 왼쪽 정렬
		
		double dNum1 = 1.23456789;
		double dNum2 = 4.53;
		// 무조건 소수점 아래 6번째 자리까지 표시
		System.out.printf("%f \t %f\n", dNum1, dNum2);
		System.out.printf("%.3f \t %.1f\n", dNum1, dNum2);
		
		char ch = 'a';
		String str = "Hello";
		System.out.printf("%c \t %s \t %s", ch, str, ch);
		System.out.printf("%C %S", ch, str); // 대문자로 출력
	}

}
