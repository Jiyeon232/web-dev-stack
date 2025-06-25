package com.kh.exception;

import java.io.IOException;

public class B_Throws {
	
	/*
	 * 리턴타입 메서드명(파라미터, ...) throws 예외클래스, ... {
	 * 
	 * }
	 * 
	 * - main 메서드에서 throws 키워드로 예외를 떠넘길 수 있지만
	 *   결국 JVM이 예외 처리 하게 되므로 비추천!
	 *   --> 프로그램이 알 수 없는 예외 내용을 출력하고 종료하는 것은 좋지 X
	 * */

	public static void main(String[] args) {
		
		try {
			findClass();
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			//System.out.println(e.getMessage());
			System.out.println("클래스가 존재하지 않습니다.");
		}
		
		B_Throws b = new B_Throws();
		try {
			b.method1();
		} catch (ClassNotFoundException | IOException e) {
			//e.printStackTrace();
			System.out.println("예외가 발생했습니다!");
		}
		
	}

	public static void findClass() throws ClassNotFoundException {
		Class.forName("java.lang.String2");
	}
	
	public void method1() throws ClassNotFoundException, IOException {
		System.out.println("method1 호출");
		method2();
		System.out.println("method1 종료");
	}
	
	public void method2() throws ClassNotFoundException, IOException {
		System.out.println("method2 호출");
		method3();
		System.out.println("method2 종료");
	}
	
	public void method3() throws ClassNotFoundException, IOException {
		System.out.println("method3 호출");
		
		int random = (int) (Math.random() * 3 + 1);
		
		if (random == 1) {
			throw new ClassNotFoundException();
		} else if (random == 2) {
			throw new IOException();
		}
		
		System.out.println("method3 종료");
	}
	
}
