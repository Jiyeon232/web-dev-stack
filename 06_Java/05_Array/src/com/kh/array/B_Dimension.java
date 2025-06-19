package com.kh.array;

import java.util.Arrays;

public class B_Dimension {

	/*
	 * 다차원 배열 : '[]'의 개수가 차원의 수를 의미
	 * 
	 * * 2차원 배열의 선언
	 * 자료형[][] 배열명;
	 * 자료형 배열명[][];
	 * 자료형[] 배열명[];
	 * 
	 * * 2차원 배열의 생성
	 * 배열명 = new 자료형[행크기][열크기];
	 * */
	public void method1() {
		int[][] arr = { 
				{ 1, 2, 3, 4, 5 }, 
				{ 6, 7, 8, 9, 10 } 
		};
		/*
		 * [ 1, 2, 3, 4, 5 ]
		 * [ 6, 7, 8, 9, 10 ] 
		 * */
		
		// arr[0], arr[1] 각각 하나씩 출력!
		for (int i = 0; i < arr.length; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
		
		/*
		 * 1
		 * 2
		 * 3
		 * ...
		 * 8
		 * 9
		 * 10
		 * */
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.println(arr[i][j]);
			}
		}
		
		// 향상된 for문
		for (int[] array : arr) {
			//System.out.println(array); // 각각의 배열의 주소값
			for (int value : array) {
				System.out.println(value);
			}
		}
	}
	
	// 가변 배열 : 다차원 배열에서 마지막 차수의 크기를 지정하지 않고 다르게 지정
	/*
	 * [HTML, CSS, JavaScript, Oracle, MySQL]
	 * [Java, JDBC, Servlet, JSP, MyBatis, SpringBoot]
	 * [React, Redux, React Query]
	 * [SpringBoot API, JPA, QueryDSL]
	 * [AWS, EC2, S3, RDS, Render, Netlify]
	 * */
	public void method2() {
		String arr[][] = { 
				{ "HTML", "CSS", "JavaScript", "Oracle", "MySQL" },
				{ "Java", "Kotlin", "JDBC", "Servlet", "JSP", "MyBatis", "SpringBoot" },
				{ "React", "Redux", "React Query", "TypeScript", "Vue", "React Native", "Next" },
				{ "SpringBoot API", "JPA", "QueryDSL" }, 
				{ "Node", "Express", "Nest" },
				{ "AWS", "EC2", "S3", "RDS", "Render", "Netlify" } 
		};

		for (int i = 0; i < arr.length; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
		
		for (String[] value : arr) {
			System.out.println(Arrays.toString(value));
		}
	}

	public static void main(String[] args) {
		B_Dimension b = new B_Dimension();
		//b.method1();
		b.method2();
	}

}
