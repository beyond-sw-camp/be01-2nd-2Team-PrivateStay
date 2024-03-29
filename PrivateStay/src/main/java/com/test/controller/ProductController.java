package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	@GetMapping("/form/{companyCode}")
	public String saveForm(Model model, @PathVariable("companyCode") String companyCode, HttpSession session) {
		session.getAttribute(companyCode);
		model.addAttribute("companyCode", companyCode);
		return "productInput";
	}

	@PostMapping("/save")
	// public ResponseEntity<Product> save(Product product, Model model) {
	public String save(Product product, Company company, Model model, HttpServletRequest request) {
		String cCode = request.getParameter("companyCode");
		//Company c = companyService.getCompanyByCode(Integer.parseInt(cCode));
//		System.out.println(cCode);
		productService.saveProductWithCompany(product, Integer.parseInt(cCode));
		
		//Product saveProduct = productService.saveProduct(product);
		// new ResponseEntity<>(saveProduct, HttpStatus.CREATED);
		return "redirect:/product/selectAll/" + cCode;
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
				String userId = (String) session.getAttribute("userId");
		        System.out.println("calender userId = " + userId);
				//session.getAttribute("productCode");
				return "response/static/calender";
			} else {
				System.out.println("유효하지 않은 접근입니다.");
				return "response/static/calender";
			}
		}
		
//		

		@GetMapping("/selectForm2")
		public String selectForm2() {

			return "selectcCode2";
		}
		
		@PostMapping("/select2/{companyCode}")
		public String search2(@RequestParam("companyCode") String companyCode, Model model, HttpSession session) {
			List<Product> product = productService.searchByCompanyCode(companyCode);
			System.out.println(product);
			model.addAttribute("product", product);
			session.setAttribute("product", product);
			//session.setAttribute("productCode", product);
			return "cCodeProduct2"; // This corresponds to the Thymeleaf template file (products.html)
		}
		
		@GetMapping(value = "/calenderForm2/{productCode}")
		public String calenderForm2(Model model, @PathVariable("productCode") String productCode, HttpSession session) {
			
			System.out.println(productCode);
			if (productCode != null) {
				model.addAttribute("productCode", productCode);
				//session.getAttribute("productCode");
				return "response/static/calender2";
			} else {
				System.out.println("유효하지 않은 접근입니다.");
				return "response/static/calender2";
			}
		}
		

		
		@GetMapping("/selectAll/{companyCode}")
		public String searchAllProduct(@PathVariable("companyCode") String companyCode, Model model, HttpSession session) {
			List<Product> product = productService.searchByCompanyCode(companyCode);
			System.out.println(product);
			model.addAttribute("product", product);
			session.setAttribute("product", product);
			//session.setAttribute("productCode", product);
			return "cCodeProduct2"; // This corresponds to the Thymeleaf template file (products.html)
		}
	
		@GetMapping("/selectAll0/{companyCode}")
		public String selectAllProduct(@PathVariable("companyCode") String companyCode, Model model, HttpSession session) {
			List<Product> product = productService.searchByCompanyCode(companyCode);
			System.out.println(product);
			model.addAttribute("product", product);
			session.setAttribute("product", product);
			String userId = (String) session.getAttribute("userId");
	        System.out.println("company code userId = " + userId);
			//session.setAttribute("productCode", product);
			return "cCodeProduct"; // This corresponds to the Thymeleaf template file (products.html)
		}
		
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
	
	
	
	
	// ---------------------------------------------------------------------------------
	
	@DeleteMapping("/delete/{id}") //http://www.localhost.com:8080/product/delete
	public ResponseEntity deleteUser(@PathVariable int id) {
		productService.deleteProductById(id);
		return new ResponseEntity<>("상품 삭제가 성공적으로 완료되었습니다.", HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity update(@PathVariable int id, @RequestBody Product product) {
	    Product updatedProduct = productService.updateProductById(id, product);
	    
	    if (updatedProduct != null) {
	        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
	    }
	}

}