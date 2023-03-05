package com.techytown.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techytown.exceptions.CategoryException;
import com.techytown.exceptions.ProductException;
import com.techytown.models.Category;
import com.techytown.models.Product;
import com.techytown.service.CategoryService;
import com.techytown.service.OrderService;
import com.techytown.service.ProductService;
import com.techytown.service.UserDetailsServiceImpl;

@RestController
public class DashboardController {
	
	@Autowired
	private UserDetailsServiceImpl serviceImpl; 
	
	@Autowired
	private ProductService productService;
		
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private OrderService orderService;

	//all dashboard details here
	
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/api/dashboard/orders")
	  public ResponseEntity<String> totalOrders() {
	    Integer total = orderService.totalOrders();
	    return new ResponseEntity<String>(total+" orders have been Placed !",HttpStatus.OK);
	  }
	
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/api/dashboard/category")
	  public ResponseEntity<String> totalCats() throws CategoryException {
	    Set<Category> total = categoryService.getAllCategories();
	    return new ResponseEntity<String>(total.size()+" Categories Are Present In Database !",HttpStatus.OK);
	  }
	
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/api/dashboard/products")
	  public ResponseEntity<String> totalProducts() throws CategoryException, ProductException {
	    List<Product> total = productService.products();
	    return new ResponseEntity<String>(total.size()+" Products Are Present In Database !",HttpStatus.OK);
	  }
	
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/api/dashboard/users")
	  public ResponseEntity<String> activeUsers(){
	    Integer total = serviceImpl.activeUsers();
	    return new ResponseEntity<String>(total+" Active Users Are Present On Website !",HttpStatus.OK);
	  }
	
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/api/dashboard/topProducts")
	  public ResponseEntity<Set<Product>> topSellingProducts(){
	    Set<Product> top = orderService.top5Products();
	    return new ResponseEntity<Set<Product>>(top,HttpStatus.OK);
	  }
	
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/api/dashboard/topCategory")
	  public ResponseEntity<Set<Category>> topSellingCategories(){
	    Set<Category> top = orderService.top5Categories();
	    return new ResponseEntity<Set<Category>>(top,HttpStatus.OK);
	  }
	
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/api/dashboard/revenue")
	  public ResponseEntity<String> totalRevenue(){
	    Double total = orderService.totalRevenue();
	    return new ResponseEntity<String>(total+" Revenue Generated From Start of Company !",HttpStatus.OK);
	  }
	
	
}
