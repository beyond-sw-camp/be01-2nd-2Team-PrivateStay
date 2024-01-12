package com.test.service;

import java.util.List;

import com.test.entity.Product;
import com.test.entity.Company;

public interface StockService {
	//Product getStockById(String productCode);
	Product saveProduct(Product product);
	Product saveProductBycCode(Product product);
	public List<Product> searchByCompanyCode(String cCode);
}
 