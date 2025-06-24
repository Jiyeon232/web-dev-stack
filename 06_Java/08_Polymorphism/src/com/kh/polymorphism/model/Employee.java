package com.kh.polymorphism.model;

// 부모 클래스! 공통적인 특성들만 가지고 있기!
public class Employee {

	private String name;
	private int salary;
	
	public Employee() {
	}

	public Employee(String name, int salary) {
		this.name = name;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + "]";
	}
	
}
