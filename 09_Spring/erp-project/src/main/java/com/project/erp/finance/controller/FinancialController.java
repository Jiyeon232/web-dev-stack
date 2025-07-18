package com.project.erp.finance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.erp.finance.service.SaleService;
import com.project.erp.finance.vo.Sale;

@Controller
public class FinancialController {
	
	// 로직만 관리하는 컨트롤러
	
	@Autowired
	private SaleService saleService;
	
	@PostMapping("/insert")
	public String insert(Sale sale) {
		saleService.addSale(sale);
		return "redirect:/list";
	}
	
	@GetMapping("/list")
	public String list() {
		List<Sale> list = saleService.viewSale();
		System.out.println(list);
		return "redirect:/";
	}
	
}
