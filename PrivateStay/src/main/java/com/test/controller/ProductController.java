package com.test.controller;

import java.net.http.HttpRequest;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.entity.Company;
import com.test.entity.Product;
import com.test.service.CompanyService;
import com.test.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController implements ErrorController {

	@Autowired
	private ProductService productService;
	@Autowired
	private CompanyService companyService;

//	@GetMapping("/form")
//    public ModelAndView saveForm() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("productInput");
//        return modelAndView;
//    }
	@GetMapping("/form")
	public String saveForm() {

		return "productInput";
	}

	@PostMapping("/save")
	// public ResponseEntity<Product> save(Product product, Model model) {
	public String save(Product product, Company company, Model model, HttpServletRequest request) {
		String cCode = request.getParameter("companyCode");
		Company c = companyService.getCompanyByCode(Integer.parseInt(cCode));
//		System.out.println(cCode);
		Product saveProduct = productService.saveProduct(product);
		// new ResponseEntity<>(saveProduct, HttpStatus.CREATED);
		return "response/static/calender";
	}

	@GetMapping("/selectForm")
	public String selectForm() {

		return "selectcCode";
	}
//	
//	 @GetMapping("/select")
//	    public String getProductsByCompanyCode(@RequestParam("companyCode") int companyCode, Model model) {
//	        List<Product> products = productService.searchByCompanyCode(Integer.toString(companyCode));
//	        model.addAttribute("products", products);
//	        return "cCodeProduct"; // This corresponds to the Thymeleaf template file (products.html)
//	    }

	@PostMapping("/select")
	public String search(@RequestParam("companyCode") String companyCode, Model model, HttpSession session) {
		List<Product> product = productService.searchByCompanyCode(companyCode);
		System.out.println(product);
		model.addAttribute("product", product);
		session.setAttribute("product", product);
		//session.setAttribute("productCode", product);
		return "cCodeProduct"; // This corresponds to the Thymeleaf template file (products.html)
	}

		@GetMapping(value = "/calenderForm/{productCode}")
		public String calenderForm(Model model, @PathVariable("productCode") String productCode, HttpSession session) {
			
			System.out.println(productCode);
			if (productCode != null) {
				model.addAttribute("productCode", productCode);
				//session.getAttribute("productCode");
				return "response/static/calender";
			} else {
				System.out.println("유효하지 않은 접근입니다.");
				return "response/static/calender";
			}
		}

	
//	@GetMapping("/calenderForm")
//	public String stockCalenderPage() {
//		System.out.println("call~~1111");
////	    	
//		return "response/static/calender";
//	}

	@PostMapping
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		Product saveProduct = productService.saveProduct(product);
		return new ResponseEntity<>(saveProduct, HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") int productCode) {
		Product product = productService.getProductById(productCode);
		return ResponseEntity.ok(product);
	}

}
