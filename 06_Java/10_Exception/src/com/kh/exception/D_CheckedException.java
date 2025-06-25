package com.kh.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/*
 * CheckedException
 * - Exception 상속하고 있는 예외들 CheckedException
 * - 컴파일 시 예외 처리 코드가 있는지 검사하는 예외
 * - 예외처리(try ~ catch, throws)가 되어 있지 않으면 컴파일 에러
 * - 소스코드 수정으로도 해결 X
 * - 주로 외부에 매개체와 입출력이 일어날 때 발생
 * */

public class D_CheckedException {

	public static void main(String[] args) {
		try { // -> I/O
			FileInputStream fis = new FileInputStream("a.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
