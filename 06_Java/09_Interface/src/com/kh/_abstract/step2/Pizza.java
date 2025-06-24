package com.kh._abstract.step2;

public abstract class Pizza {

	protected int price;
	protected String brand;
	
	public Pizza(int price, String brand) {
		this.price = price;
		this.brand = brand;
	}
	
	// 여기서부터는 자유롭게!
	
	public void bake() {
		info();
		System.out.println("피자 반죽과 함께 도우를 빚는다.");
		topping();
		System.out.println("피자를 180도에서 10분간 구운다");
		System.out.println("피자를 8등분 한다.");
		System.out.println("피자를 포장한다.");
	}
	
	public abstract void info();
	
	public abstract void topping();
	
}