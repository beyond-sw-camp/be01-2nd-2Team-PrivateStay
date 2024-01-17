package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.entity.Product;
import com.test.entity.Reservation;
import com.test.service.ProductService;
import com.test.service.ReservationService;
import com.test.service.StockService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private ReservationService reservationService;
	
	@GetMapping("/new")
	public String handleSampleRequest(
			@RequestParam(name = "selectedYear") int selectedYear,
			@RequestParam(name = "selectedMonth") int selectedMonth,
			@RequestParam(name = "selectedDay") int selectedDay,
			@RequestParam(name = "productCode") int p, Model model, HttpSession session, Reservation reservation) {

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
	    System.out.println("get1");
	    
	    String stockCode = year + month + day + p;
	    
	    session.setAttribute("sCode", stockCode);
	    model.addAttribute("selectedYear", selectedYear);
	    model.addAttribute("selectedMonth", selectedMonth);
	    model.addAttribute("selectedDay", selectedDay);
	    model.addAttribute("productCode", p);
	    

	    // 여기에서 필요에 따라 추가적인 처리 수행
	    // 예를 들어, 데이터 처리, 뷰 반환 등

	    return "reservationForm"; // 적절한 뷰 이름으로 반환	   
	}
	
	@PostMapping("/complete/{selectedYear}/{selectedMonth}/{selectedDay}/{productCode}")
	public String postReservationRequest(@PathVariable String selectedYear,
	        @PathVariable String selectedMonth,
	        @PathVariable String selectedDay,
	        @PathVariable String productCode,
	        @RequestParam int headCount,
	        @RequestParam String status,
	        HttpSession session,
	        Reservation reservation) {
		
		System.out.println("get2");
		String userId = (String) session.getAttribute("userId");	    
		String stockCode = (String) session.getAttribute("sCode");	
		
		int selectedYears = Integer.parseInt(selectedYear);
		int selectedMonths = Integer.parseInt(selectedMonth);
		int selectedDays = Integer.parseInt(selectedDay);
	    
	    reservationService.saveProduct(reservation, userId, stockCode, selectedYears, selectedMonths, selectedDays, headCount, status);
	    

	    return "complete";
	
	}
	
	@GetMapping("/list")
	public String getListRequest(HttpSession session, Model model) { 
		String userId = (String) session.getAttribute("userId");
		List<Reservation> reservation = reservationService.searchByUserId(userId);
		

		model.addAttribute("reservation", reservation);
//		List<Product> product = productService.searchByCompanyCode(companyCode);
//		System.out.println(product);
//		model.addAttribute("product", product);
//		session.setAttribute("product", product);
//		//session.setAttribute("productCode", product);
//		return "cCodeProduct2"; // This corresponds to the Thymeleaf template file (products.html)
		return "myReservationList";
	}
	
	@GetMapping("/select/{companyCode}")
	public String getCompanyListRequest(@PathVariable String companyCode, Model model, HttpSession session) {
		//String cCode = Integer.toString(companyCode);
		List<Product> product = productService.searchByCompanyCode(companyCode);
		System.out.println(product);
		model.addAttribute("product", product);
		session.setAttribute("product", product);
		//session.setAttribute("productCode", product);
		return "cCodeProduct3"; // This corresponds to the Thymeleaf template file (products.html)
	}
	
	@GetMapping("/proreservation/{productCode}")
	public String getCompanyResListRequest(@PathVariable("productCode") String productCode, Model model, HttpSession session) {
		List<Reservation> reservation = reservationService.searchByProductCode(productCode);
		model.addAttribute("reservation", reservation);
		return "myReservationList2"; // This corresponds to the Thymeleaf template file (products.html)
	}
	
	
	
@GetMapping("/calenderForm")
public String stockCalenderPage() {
	System.out.println("call~~1111");
//    	
	return "response/static/calender";
}
}
