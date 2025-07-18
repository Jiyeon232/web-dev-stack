package com.project.erp.finance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.erp.finance.mapper.SaleMapper;
import com.project.erp.finance.vo.Sale;

@Service
public class SaleService implements SaleMapper {
	
	@Autowired
	private SaleMapper mapper;

	@Override
	public void addSale(Sale sale) {
		mapper.addSale(sale);
	}

	@Override
	public List<Sale> viewSale() {
		return mapper.viewSale();
	}

}
