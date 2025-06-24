package com.kh._interface.step2;

public class Tv implements RemoteControl, Searchable {

	@Override
	public void turnOn() {
		System.out.println("TV를 켭니다.");
	}

	@Override
	public void turnOff() {
		System.out.println("TV를 끕니다.");
	}

	@Override
	public void search(String url) {
		System.out.println(url + " 검색");
	}

	@Override
	public void setVolume(int volume) {
	}

}
