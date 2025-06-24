package com.kh._abstract.step1;

// 추상 클래스!
public abstract class Sports {

	protected int numOfPlayers; // 참여 사람 수

	public Sports(int numOfPlayers) {
		this.numOfPlayers = numOfPlayers;
	}
	
	// 추상메서드! 즉, 미완성된 메서드! 구현부가 X -> 자식 클래스에서 강제 구현!
	public abstract void rule();
}
