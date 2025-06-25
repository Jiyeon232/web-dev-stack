package com.kh.practice.controller;

import java.time.LocalDate;

import com.kh.practice.model.Department;
import com.kh.practice.model.DeptType;
import com.kh.practice.model.UserInfo;

public class UserController {
	
	//private UserInfo userInfo = new UserInfo(); --> 회원 1명
	
	// 회원 3명일 때 --> 배열
	private UserInfo[] userList = { new UserInfo(), new UserInfo(), new UserInfo() }; 
	
	private int count = 0;
		
	// 로그인 -> 유저 정보 클라이언트!
	public UserInfo login(String id, String password) {
		for (UserInfo user : userList) {
			if (id.equals(user.getId()) && password.equals(user.getPassword())) {
				return user;
			}
		}
		return null;
	}
	
	// 오버로딩!
	public void addUser(int userNo, String id, String password, String email, String name) {
		userList[count].setUserNo(userNo);
		userList[count].setId(id);
		userList[count].setPassword(password);
		userList[count].setEmail(email);
		userList[count].setName(name);
		count++;
	}
	
	public void addUser(String phone, String addr, String gender, LocalDate birthDate, int deptNo) {
		userList[count].setPhone(phone);
		userList[count].setAddr(addr);
		userList[count].setGender(gender);
		userList[count].setBirthDate(birthDate);
		userList[count].setDeptNo(deptNo); // --> 부서 코드를 입력받아서 추가하기
		/*
		// 기존에 있는 부서(해당 유저들)이면 해당 부서 정보 그대로 추가
		// --> 부서 명을 입력받아서 추가하기
		for (UserInfo user : userList) {
			Department dept = user.getDepartment();
			if (dept != null && dept.getDeptName().equals(department.getDeptName())) {
				userList[count].setDepartment(dept);
				return;
			}
		}
		// 기존에 없어서 새로 추가
		department.setDeptNo(DeptType.findDeptNo(department.getDeptName()));
		userList[count].setDepartment(department);
		*/
		
		// 지금 테이블이 없는 상태여서 지금부터 추가된 코드 너무 어려우면 괜찮아요
		// 여기서만 보게 될거예요!
		Department dept = new Department(deptNo, DeptType.findDeptName(deptNo));
		userList[count].setDepartment(dept);
	}
	
	// 회원 정보 수정하는 부분 -> 메서드 1개 / 메서드 3개
	// 메서드 1개
	public void updateUser(UserInfo user) {
		for (UserInfo info : userList) {
			if (user.getUserNo() == info.getUserNo()) {
				info = user;
			}
		}
	}
	/*
	// 메서드 3개
	public void updateUser(String phone) {
		userInfo.setPhone(phone);
	}
	
	public void updateUser(LocalDate birthDate) {
		userInfo.setBirthDate(birthDate);
	}
	
	public void updateUser(Department department) {
		userInfo.setDepartment(department);
	}
	*/
}
