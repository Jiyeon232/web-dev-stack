package com.kh.overriding;

import java.util.Scanner;

import com.kh.inheritance.model.parent.Product;
import com.kh.overriding.model.Customer;
import com.kh.overriding.model.VipCustomer;

public class Application {
	
	/*
	 * SOLID 의 O
	 * Open/Closed Principle (개방-폐쇄 원칙)
	 * - 기존 코드를 변경하지 않고 확장 가능하게
	 * */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 이름
		System.out.print("이름 입력 : ");
		String name = sc.nextLine();
		// 일반 회원 vs VIP
		System.out.print("일반 회원 or VIP 둘 중 하나 선택 : ");
		String grade = sc.nextLine();
		// 가격
		System.out.print("가격 입력 : ");
		int price = sc.nextInt();
		
		Customer customer5 = null;
		
		if (grade.equals("VIP")) {
			customer5 = new VipCustomer(name);
		} else {
			customer5 = new Customer(name);
		}
		customer5.calc(price);
		System.out.println(customer5);
		
		// 포함 관계로 접근하기!
		Product product = new Product();
		product.setBrand("LG");
		customer5.setProduct(product);
		System.out.println(customer5.getProduct().getBrand()); // LG
		
		
		//int price = 100000;
		
		Customer customer1 = new Customer("서지은");
		customer1.calc(price);
		System.out.println(customer1);
		
		VipCustomer customer2 = new VipCustomer("김은진");
		customer2.calc(price);
		System.out.println(customer2);
		
		// 동명이인은 없다고 가정
		Customer customer3 = new Customer("서지은");
		VipCustomer customer4 = new VipCustomer("김은진");
		
		System.out.println(customer1.equals(customer3)); // true
		System.out.println(customer2.equals(customer4)); // true
		
		
		// 100000
		// ~~님의 등급은 ~~이며, 지불해야 하는 금액은 ~~원이며, 적립된 포인트는 ~~점입니다.
		// 1. getter / setter 사용
		/* int price = 100000;
		System.out.println(customer1.getName() + "님의 등급은 " + customer1.getGrade() 
		                + "이며, 지불해야 하는 금액은 " + price + "원이며, 적립된 포인트는 " 
				        + (price * customer1.getBonusRatio()) + "점입니다.");
		System.out.println(customer2.getName() + "님의 등급은 " + customer2.getGrade() 
	                    + "이며, 지불해야 하는 금액은 " + (price * customer2.getSaleRatio()) 
	                    + "원이며, 적립된 포인트는 " + (price * customer2.getBonusRatio()) + "점입니다."); 
	    */
		
		// 2. toString 사용 -> toString을 변경해서 출력!
		// calc 메서드(포인트를 계산하는 메서드) -> 가격을 받아서 적립된 포인트를 계산하고
		// -> VipCustomer에서 지불해야 하는 금액을 재정의(오버라이딩)
		
		// 3. result 메서드를 만들어서 출력! (내가 시도한 방법)
		// 생성자를 만들 때 이름과 가격을 받아서 계산한 내용을 출력하기
		//Customer customer1 = new Customer("서지은", 100000);
		//VipCustomer customer2 = new VipCustomer("김은진", 100000);
		//customer1.result();
		//customer2.result();
	}

}
