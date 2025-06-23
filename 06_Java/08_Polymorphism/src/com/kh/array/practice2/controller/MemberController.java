package com.kh.array.practice2.controller;

import com.kh.array.practice2.model.Member;

public class MemberController {
	/*
	Member m = new Member();
	
	public void savaData(Member memData) {
		m.setId(memData.getId());
		m.setName(memData.getName());
		m.setPwd(memData.getPwd());
		m.setEmail(memData.getEmail());
		m.setGender(memData.getGender());
		m.setAge(memData.getAge());
	}
	*/
	
	// 공간만 만들거냐
	public Member[] members = new Member[3]; // -> 1번일 때
	
	// 객체부터 생성할거냐
	//Member[] members = {new Member(), new Member(), new Member()}; // -> 2번일 때
	
	// 멤버수
	//public int countMember() {
	//	return members.length;
	//}
	
	// 아이디 체크 -> 배열을 전부 확인하고 아이디가 있는지 없는지 여부
	public void idCheck() {
		for (Member member : members) {
			System.out.println(member != null && member.getId()!=null); // -> 1번으로 체크
			//System.out.println(member.getId()); // -> 2번으로 체크
			//System.out.println(member);
		}
	}
}
