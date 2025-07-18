package com.project.erp.finance.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Budget {
	
	private int budgetNo;
	private String periodType;
	private String periodValue;
	private int annualBudget;
	private int targetSales;
	private String plan;
	private String achieved;
	private LocalDateTime createdAt;
	private int deptNo;

}
