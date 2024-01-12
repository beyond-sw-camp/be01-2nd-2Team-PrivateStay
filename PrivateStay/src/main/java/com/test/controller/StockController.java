package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.service.StockService;
import com.test.entity.Stock;
import com.test.service.ProductService;
import com.test.entity.Product;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {
	
	@Autowired
	private StockService stockService;
	@Autowired
	private ProductService productService;
	
	@GetMapping("/save")
	public String handleSampleRequest(
	        @RequestParam(name = "selectedYear") int selectedYear,
	        @RequestParam(name = "selectedMonth") int selectedMonth,
	        @RequestParam(name = "selectedDay") int selectedDay,
	        @RequestParam(name = "productCode") int p,
	        Stock stock, Product product, Model model, HttpServletRequest request) {
		

	    // 가져온 값 사용 예시
	    System.out.println("Selected Year: " + selectedYear);
	    System.out.println("Selected Month: " + selectedMonth);
	    System.out.println("Selected Day: " + selectedDay);
	    System.out.println("p: " + p);
	    
	    String year = Integer.toString(selectedYear);
	    String month = Integer.toString(selectedMonth);
	    String day = Integer.toString(selectedDay);
	    
	    if(month.length() == 1) {
	    	month = "0" + month;
	    }
	    if(day.length() == 1) {
	    	day = "0" + day;
	    }
	    
	    String stockCode = year + month + day + p;
	    
	    stockService.saveStockWithProduct
	    (stock, p, selectedYear, selectedMonth, selectedDay, stockCode);
	    model.addAttribute("stockCode", stockCode);

	    return "stockInput"; // 적절한 뷰 이름으로 반환
	}
	
	@PostMapping("/insert")
	public String insertStock(@RequestParam("stockQuantity") boolean stockQuantity, Stock stock, Model model, HttpServletRequest request) {
		String stockCode = request.getParameter("stockCode");
		
//		boolean a = false;
//		
//		if(quan == 1) a = true;
//		
		stockService.updateStockQuantityByStockCode(stockCode, stockQuantity);
		
		return "index";
	}
	
	
	
	

	
}