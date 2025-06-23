package com.kh.array.practice2.view;

import com.kh.array.practice2.controller.MemberController;

public class MemberView {

	public void mainMenu() {
		
		MemberController controller = new MemberController();
		
		System.out.println("최대 등록 가능한 회원 수는 " + controller.members.length + "명입니다.");
	}
}
