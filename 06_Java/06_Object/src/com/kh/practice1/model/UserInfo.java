package com.kh.practice1.model;

public class UserInfo {

	public int userNo;
	public String id;
	public String password;
	public String email;
	public String name;
	public String phone;
	public String addr;
	
	public UserInfo() {}
	
	public String printName() { // getter
		return "이름 : " + name; // this.name => this 생략해도 OK!
	}
	
	public void changeName(String name) { // setter
		this.name = name;
	}
}
