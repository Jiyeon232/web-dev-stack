package com.kh._abstract.step2;

public class PotatoPizza  extends Pizza {

	public PotatoPizza(int price, String brand) {
		super(price, brand);
	}

	@Override
	public void info() {
		System.out.println(this.brand + "의 포테이토 피자 가격은 " + this.price + "원");
	}

	@Override
	public void topping() {
		System.out.println("토핑은 포테이토를 포함한다.");
	}
	
}
