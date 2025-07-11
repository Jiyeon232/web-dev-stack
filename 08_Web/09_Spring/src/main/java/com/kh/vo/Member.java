package com.kh.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
public class Member {

	private String id;
	private String pwd;
	private String name;
	private int age;

}
