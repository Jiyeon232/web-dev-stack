package com.project.erp.finance.vo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Sale {
	
	private int saleNo;
	private LocalDate saleDate;
	private String productName;
	private String category;
	private String gender;
	private int quantity;
	private int varAmount;
	private int totalAmount;

}
