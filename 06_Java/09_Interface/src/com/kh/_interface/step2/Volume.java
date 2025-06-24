package com.kh._interface.step2;

public interface Volume {

	// 인터페이스에서 멤버변수는 무조건 상수!
	public static final int MIN_VOLUME = 0;
	int MAX_VOLUME = 20; // public static final 생략해도 OK!
	
	public abstract void setVolume(int volume); // public abstract 생략 가능!
}
