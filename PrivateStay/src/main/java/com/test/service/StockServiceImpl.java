package com.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.entity.Company;
import com.test.entity.Product;
import com.test.entity.Stock;
import com.test.service.ProductService;

import jakarta.persistence.EntityNotFoundException;

import com.test.repository.ProductRepository;
import com.test.repository.StockRepository;

import lombok.extern.slf4j.Slf4j;
 
@Service
@Slf4j
public class StockServiceImpl implements StockService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StockRepository stockRepository;
	
	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

//	@Override
//	public Product getStockById(String productcode) {
//		return productRepository.findById(productcode).get();
//	}
	
	@Override
	public Product saveProductBycCode(Product product) {
		return productRepository.save(product);
	}
//	
//	@Override
//	public List<Product> searchByCompanyCode(String companyCode) {
//		 try {
//	            // Convert the String to int
//	            int companyCodeAsInt = Integer.parseInt(companyCode);
//	            // Use the converted int in the repository method
//	            return productRepository.findByCompanyCompanyCode(companyCodeAsInt);
//	        } catch (NumberFormatException e) {
//	            e.printStackTrace(); // Log the exception for debugging purposes
//	            return null; // Or throw a custom exception or return an empty list depending on your logic
//	        }
//       // return productRepository.findByCompanyCode(companyCode);
//    }
	 
	@Override
	public void saveStockWithProduct(Stock stock, int productCode, int year, int month, int day, String stockCode) {
	    // 1. Company 엔터티로부터 Optional<Company> 인스턴스 가져오기
	    Optional<Product> optionalProduct = productRepository.findById(productCode);

	    // 2. Optional에서 실제 값을 추출하고, 값이 존재하지 않을 경우 예외 처리
	    Product product = optionalProduct.orElseThrow(() -> new EntityNotFoundException("Product not found for code: " + productCode));

	    // 3. Product 엔터티 생성 및 데이터 설정
	    stock.setStockCode(stockCode);
	    stock.setYear(year);
        stock.setMonth(month);
        stock.setDay(day);
        stock.setProduct(product);

	    // 4. Repository를 통한 저장
	    stockRepository.save(stock);
	}
	
	@Override
    public void updateStockQuantityByStockCode(String stockCode, boolean newStockQuantity) {
        Stock stock = stockRepository.findByStockCode(stockCode);

        if (stock != null) {
            stock.setStockQuantity(newStockQuantity);
            stockRepository.save(stock);
        } else {
            // 처리할 로직: 해당 stockCode에 대한 Stock이 존재하지 않을 경우
            // 예외처리 등을 수행할 수 있습니다.
        }
    }

	@Override
	public List<Product> searchByCompanyCode(String cCode) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
