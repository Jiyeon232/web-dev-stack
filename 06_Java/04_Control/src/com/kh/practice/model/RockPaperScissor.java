package com.kh.practice.model;

import java.util.Arrays;

// 캡슐화! -> Single Responsibility Principle (SRO 원칙)
public class RockPaperScissor {
	private String[] rps = { "가위", "바위", "보" };
	private int win = 0;
	private int draw = 0;
	private int lose = 0;
	
	public RockPaperScissor() {
		
	}

	public RockPaperScissor(String[] rps, int win, int draw, int lose) {
		this.rps = rps;
		this.win = win;
		this.draw = draw;
		this.lose = lose;
	}

	public String[] getRps() {
		return rps;
	}

	public void setRps(String[] rps) {
		this.rps = rps;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}

	@Override
	public String toString() {
		return "RockPaperScissor [rps=" + Arrays.toString(rps) + ", win=" + win + ", draw=" + draw + ", lose=" + lose
				+ "]";
	}
}
