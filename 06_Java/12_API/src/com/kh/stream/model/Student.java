package com.kh.stream.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
public class Student implements Comparable<Student> {

	private String name;
	private int age;
	private String gender;
	private int math;
	private int english;
	
	@Override
	public int compareTo(Student o) {
		return this.age - o.age;
	}
}
