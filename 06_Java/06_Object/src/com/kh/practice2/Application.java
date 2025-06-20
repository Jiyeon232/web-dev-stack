package com.kh.practice2;

import java.util.Scanner;

import com.kh.practice2.controller.SnackController;

public class Application {

	public static void main(String[] args) {
		
		SnackController controller = new SnackController();
		Scanner sc = new Scanner(System.in);

		System.out.println("스낵류를 입력하세요.");
		
		System.out.print("종류 : ");
		String inputKind = sc.nextLine();
		
		System.out.print("이름 : ");
		String inputName = sc.nextLine();
		
		System.out.print("맛 : ");
		String inputFlavor = sc.nextLine();
		
		System.out.print("개수 : ");
		int inputNumOf = Integer.parseInt(sc.nextLine());
		
		System.out.print("가격 : ");
		int inputPrice = Integer.parseInt(sc.nextLine());
		
		System.out.println(controller.saveData(inputKind, inputName, inputFlavor, inputNumOf, inputPrice));
		
		System.out.println(controller.confirmData());
		
	}

}
