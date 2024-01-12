package com.test.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.test.entity.Product;
import com.test.entity.Company;

public interface ProductService {
	Product getProductById(int productCode);
	Product saveProduct(Product product);
	Product saveProductBycCode(Product product);
	public List<Product> searchByCompanyCode(String cCode);
	public void saveProductWithCompany(Product product, int companyCode);
	void deleteProductById(int productCode); //상품 삭제
	Product updateProductById(int productCode, Product product); //상품 정보 업데이트
}
 