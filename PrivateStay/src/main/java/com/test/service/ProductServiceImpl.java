package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.entity.Product;
import com.test.service.ProductService;
import com.test.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;
 
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product getProductById(int productcode) {
		return productRepository.findById(productcode).get();
	}
	
	@Override
	public int getProductCodeById(int productcode) {
		return productRepository.
	}
	
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

}
