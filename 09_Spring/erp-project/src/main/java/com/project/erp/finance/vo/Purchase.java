package com.project.erp.finance.vo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Purchase {
	
	private int purchaseNo;
	private String productName;
	private String vendor;
	private int unitPrice;
	private int quantity;
	private int varAmount;
	private int totalAmount;
	private LocalDate purchaseDate;

}
