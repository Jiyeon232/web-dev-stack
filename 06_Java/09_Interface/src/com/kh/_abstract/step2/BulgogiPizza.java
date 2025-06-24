package com.kh._abstract.step2;

public class BulgogiPizza extends Pizza {

	public BulgogiPizza(int price, String brand) {
		super(price, brand);
	}

	@Override
	public void info() {
		System.out.println(this.brand + "의 불고기 피자 가격은 " + this.price + "원");
	}

	@Override
	public void topping() {
		System.out.println("토핑은 불고기를 포함한다.");
	}
	
}
