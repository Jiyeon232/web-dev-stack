package com.kh.step3;

public class Application {

	public static void main(String[] args) {
		
		Thread beep = new Thread(new BeepThread());
		Thread text = new Thread(new TextThread());
		
		beep.start();
		text.start();
	}

}
