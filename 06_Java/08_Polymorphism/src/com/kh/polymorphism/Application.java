package com.kh.polymorphism;

import java.util.Scanner;

import com.kh.polymorphism.controller.EmployeeController;
// FQCN(Full Qualified Class Name)
import com.kh.polymorphism.model.Employee;
import com.kh.polymorphism.model.Engineer;
import com.kh.polymorphism.model.Manager;
import com.kh.polymorphism.model.Secretary;

/*
 * 다형성(Polymorphism)
 * - 하나의 객체 변수가 여러가지 모양과 모습을 가지는 능력
 * - 부모 타입으로 자식 객체를 생성하는 것
 * 
 * Liskov Substitution Priciple, LSP
 * - 부모 객체는 자식 객체로 교체해도 문제 없다
 * - 다형성이 제대로 설계되었는지 판단하는 기준 중 하나
 * */
public class Application {

	public static void main(String[] args) {

		Employee em = new Employee("문채은", 3000000);
		Employee en = new Engineer("최다인", 3500000, "자바", 300000);
		Employee ma = new Manager("김은진", 4000000, "개발팀");
		Employee se = new Secretary("전영현", 4500000, "문채은");
		
		System.out.println(em);
		System.out.println(en);
		System.out.println(ma);
		System.out.println(se);
		
		// 다형성 + 객체 배열
		Employee[] emp = {em, en, ma, se};
		
		for (Employee employee : emp) {
			System.out.println(employee);
		}
		
		EmployeeController ec = new EmployeeController();
		Scanner sc = new Scanner(System.in);
		System.out.print("이름을 입력해주세요 > ");
		String name = sc.nextLine();
		// 일단 반복문!
		// 이름으로 사람 찾기(Employee) 찾기 -> 이름은 문자열(equals), getName
		Employee findEmployee = ec.findEmployee(emp, name);
		
		/*
		// 일반 for문
		Employee findEmployee = null;
		for (int i = 0; i < emp.length; i++) {
			//System.out.println(emp[i].getName());
			if (emp[i].getName().equals("최다인")) {
				//System.out.println(emp[i]);
				findEmployee = emp[i];
			}
		}
		
		// 향상된 for문
		Employee findEmployee = null;
		for (Employee employee : emp) {
			if (employee.getName().equals("최다인")) {
				findEmployee = employee;
			}
		}
		*/
		
		// 찾은 사람의 연봉(getSalary * 12)은?
		// 특정 자식 객체 찾는 방법!
		int annual = ec.getAnnualSalary(findEmployee);
		if (annual != -1) {
			System.out.println(annual);
		} else {
			System.out.println("존재하지 않는 사람입니다.");
		}
		
		/*
		if (findEmployee instanceof Engineer) { // Engineer인 경우 bouns 추가
			Engineer engineer = (Engineer) findEmployee;
			System.out.println(engineer.getSalary() * 12 + engineer.getBonus());
		} else {
			System.out.println(findEmployee.getSalary() * 12);
		}
		*/
		
		// 전체 총 월급 (for문 필요! 각 getSalary 더해나가기)
		System.out.println(ec.totalSalary(emp));
		
		/*
		int sum = 0;
		for (Employee employee : emp) {
			//System.out.println(employee.getSalary());
			sum += employee.getSalary();
		}
		System.out.println(sum);
		*/
	}

}
