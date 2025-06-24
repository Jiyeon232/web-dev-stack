package com.kh._abstract.step2;

public class PineapplePizza extends Pizza {

	public PineapplePizza(int price, String brand) {
		super(price, brand);
	}

	@Override
	public void info() {
		System.out.println(this.brand + "의 파인애플 피자 가격은 " + this.price + "원");
	}

	@Override
	public void topping() {
		System.out.println("토핑은 파인애플을 포함한다.");
	}

}
