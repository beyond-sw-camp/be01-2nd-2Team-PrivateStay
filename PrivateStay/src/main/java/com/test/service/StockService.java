package com.test.service;

import java.util.List;

import com.test.entity.Product;
import com.test.entity.Stock;
import com.test.entity.Company;

public interface StockService {
	//Product getStockById(String productCode);
	Product saveProduct(Product product);
	Product saveProductBycCode(Product product);
	public List<Product> searchByCompanyCode(String cCode);
	
	
	public void saveStockWithProduct(Stock stock, int productCode, 
			int year, int month, int day, String stockCode);
	public void updateStockQuantityByStockCode(String stockCode, boolean newStockQuantity);
}
 