package com.kh._abstract.step1;

public class BasketBall extends Sports {

	public BasketBall(int numOfPlayers) {
		super(numOfPlayers);
	}

	@Override
	public void rule() {
		System.out.println("BasketBall의 선수 수는 " + this.numOfPlayers + "명, 공을 던져서 링에 넣는다");
	}
}
