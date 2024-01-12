package com.test.service;

import java.util.List;

import com.test.entity.Product;
import com.test.entity.Company;

public interface ProductService {
	Product getProductById(int productCode);
	Product saveProduct(Product product);
	Product saveProductBycCode(Product product);
	public List<Product> searchByCompanyCode(String cCode);
	public void saveProductWithCompany(Product product, int companyCode);
}
 