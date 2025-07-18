package com.project.erp.finance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.erp.finance.vo.Sale;

@Mapper
public interface SaleMapper {

	void addSale(Sale sale);
	List<Sale> viewSale();
	
}
