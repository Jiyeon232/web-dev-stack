package com.project.erp.finance.vo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Transaction {

	private int transNo;
	private String transType;
	private int transAmount;
	private String category;
	private String transDesc;
	private LocalDate transDate;
	private int userNo;
	private int deptNo;
	
}
