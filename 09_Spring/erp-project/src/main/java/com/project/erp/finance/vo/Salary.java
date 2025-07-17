package com.project.erp.finance.vo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Salary {
	private int salaryNo; // 급여 번호
	private LocalDate salaryDate; // 지급일
	private int baseSalary; // 기본급
	private int bonus; // 보너스
	private int overtime; // 초과근무 수당
	private int deduction; // 공제금액
	private int tax; // 세금
	private int userNo; // 사용자 번호
}
