package com.project.erp.finance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/finance")
@Controller
public class FinancialPageController {
	
	// 페이지 관리하는 컨트롤러
	
	@GetMapping("/salary")
	public String salary(Model model) {
		model.addAttribute("component", "../component/finance/salary.jsp");
		model.addAttribute("pageTitle", "재무 관리 > 급여 관리");
		return "/common/layout";
	}
	
	@GetMapping("/sale")
	public String sale(Model model) {
		model.addAttribute("component", "../component/finance/sale.jsp");
		return "/common/layout";
	}
	
}
