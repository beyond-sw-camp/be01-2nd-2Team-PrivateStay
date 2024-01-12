package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.service.StockService;
import com.test.entity.Stock;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {
	
	@Autowired
	private StockService stockService;

    @ResponseBody
	@RequestMapping(value = "memberRegi.do", method = RequestMethod.POST)
    public String stockCalenderPage(String selectedYear, Model model, HttpServletRequest request) {
//    	String productCode = (String) session.getAttribute("productCode");
//    	Stock currentStock = stockService.
    	System.out.println(request.getParameter("selectedYear"));
        return "sample";
    }
}