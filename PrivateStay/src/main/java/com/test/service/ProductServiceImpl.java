package com.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.entity.Company;
import com.test.entity.Product;
import com.test.service.ProductService;

import jakarta.persistence.EntityNotFoundException;

import com.test.repository.CompanyRepository;
import com.test.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;
 
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
    private CompanyRepository companyRepository;
	
	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product getProductById(int productcode) {
		return productRepository.findById(productcode).get();
	}
	
//	@Override
//	public int getProductCodeById(int productcode) {
//		return productRepository.
//	}
	
	@Override
	public Product saveProductBycCode(Product product) {
		return productRepository.save(product);
	}
	
	@Override
	public List<Product> searchByCompanyCode(String companyCode) {
		 try {
	            // Convert the String to int
	            int companyCodeAsInt = Integer.parseInt(companyCode);
	            // Use the converted int in the repository method
	            return productRepository.findByCompanyCompanyCode(companyCodeAsInt);
	        } catch (NumberFormatException e) {
	            e.printStackTrace(); // Log the exception for debugging purposes
	            return null; // Or throw a custom exception or return an empty list depending on your logic
	        }
       // return productRepository.findByCompanyCode(companyCode);
    }
	
	@Override
	public void saveProductWithCompany(Product product, int companyCode) {
	    // 1. Company 엔터티로부터 Optional<Company> 인스턴스 가져오기
	    Optional<Company> optionalCompany = companyRepository.findById(companyCode);

	    // 2. Optional에서 실제 값을 추출하고, 값이 존재하지 않을 경우 예외 처리
	    Company company = optionalCompany.orElseThrow(() -> new EntityNotFoundException("Company not found for code: " + companyCode));

	    // 3. Product 엔터티 생성 및 데이터 설정
	    product.setCompany(company);

	    // 4. Repository를 통한 저장
	    productRepository.save(product);
	}
	

	@Override
	public void deleteProductById(int productCode) {
		productRepository.deleteById(productCode);
	}

	@Override
	public Product updateProductById(int productCode, Product product) {
		Product changeProduct = productRepository.findById(productCode).get();
		changeProduct.setProductName(product.getProductName());
		changeProduct.setProductPrice(product.getProductPrice());
		changeProduct.setProductDis(product.getProductDis());
		return productRepository.save(changeProduct);
	}

}
