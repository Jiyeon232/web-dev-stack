package com.kh.step1.model;

public class Tv {
	
	/*
	 * 1. 추상화
	 * - 공통적인 특성만 뽑아내어 단순화
	 * - 개발하는데 중요한 것만 뽑고 불필요한 정보는 숨긴다
	 * */

	// TV라는 객체의 설계도 클래스
	// 속성(property)
	// - 멤버 변수(member variable), 필드(field), 특성(attribute)
	public boolean power; // 전원 상태(on/off)
	public int channel; // 채널
	
	// 기능(function)
	// - 메서드(method), 함수(function)
	public void power() { // TV를 끄거나 키는 기능
		power = !power;
	}
	
	public void channelUp() { // 채널 번호를 높이는 기능
		channel++;
	}
	
	public void channelDown() { // 채널 번호를 내리는 기능
		channel--;
	}
}
