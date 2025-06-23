package com.kh.overriding.model;

public class VipCustomer extends Customer {

	private double saleRatio; // 할인율
	
	public VipCustomer(String name) {
		this.name= name;
		this.grade = "VIP";
		this.bonusRatio = 0.1;
		this.saleRatio = 0.5;
		//this.price = price; -> int price 변수를 받아서
	}

	public double getSaleRatio() {
		return saleRatio;
	}

	public void setSaleRatio(double saleRatio) {
		this.saleRatio = saleRatio;
	}

	/*
	@Override
	public String toString() {
		//return "VipCustomer [saleRatio=" + saleRatio + ", name=" + name + ", grade=" + grade + ", bonusPoint="
		//		+ bonusPoint + ", bonusRatio=" + bonusRatio + "]";
		
		return name + "님의 등급은 " + grade + "이며, 지불해야 하는 금액은 " + (100000 * saleRatio) 
		            + "원이며, 적립된 포인트는 " + (100000 * bonusRatio) + "점입니다.";
	}
	*/
	
	/*
	 * 오버라이딩의 조건
	 * - 부모 클래스의 메서드명, 매개변수, 리턴타입 모두 동일
	 * - 접근제어자를 좁은 범위로 변경할 수 없다
	 *   -> 부모 클래스 메서드가 protected라면, 범위가 같거나 넓은 protected나 public으로만 변경 가능
	 * 
	 * @Override 어노테이션
	 * - 명시 안 해줘도 오버라이딩
	 * - 어노테이션을 붙이는 이유
	 *   - 해당 메서드가 오버라이딩 된 메서드라는 것을 컴파일러에게 알려주는 역할
	 *   - 자식 메서드에서 재정의를 잘못 한 경우 컴파일 에러 표시
	 * */
	@Override
	public void calc(int price) {
		this.bonusPoint = (int) (price * bonusRatio);
		this.price = (int) (price * saleRatio);
	}
	
	public void result() {
		System.out.println(name + "님의 등급은 " + grade + "이며, 지불해야 하는 금액은 " + (price * saleRatio) 
				            + "원이며, 적립된 포인트는 " + (price * bonusRatio) + "점입니다.");
	}
}
