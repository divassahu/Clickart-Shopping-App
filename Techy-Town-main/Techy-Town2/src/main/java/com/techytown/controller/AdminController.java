package com.techytown.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techytown.exceptions.CategoryException;
import com.techytown.exceptions.ProductException;
import com.techytown.models.Category;
import com.techytown.models.Product;
import com.techytown.service.CategoryService;
import com.techytown.service.ProductService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;
	
	//category related controlls
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/category")
	public ResponseEntity<Category> addCategory(@Valid @RequestBody Category cat) throws CategoryException{
		System.out.println("adding");
		
		Category savedCat = categoryService.addNewCategory(cat);
		
		return new ResponseEntity<Category>(savedCat,HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/category")
	public ResponseEntity<Category> updateCategory(@RequestBody Category cat) throws ProductException, CategoryException{
		Category updatedCat = categoryService.updateCategory(cat);
		
		return new ResponseEntity<Category>(updatedCat,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/category/{catId}")
	public ResponseEntity<Category> removeCategory(@PathVariable(name = "catId",required = true) Integer catID) throws ProductException, CategoryException{
		Category removedCat = categoryService.removeCategory(catID);
		
		return new ResponseEntity<Category>(removedCat,HttpStatus.OK);
	}
	
	//product related mappings
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/product/{catId}")
	public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product, @PathVariable(value = "catId",required = true) Integer categoryId) throws ProductException, CategoryException{
		Product saved = productService.saveProduct(product, categoryId);
		
		return new ResponseEntity<Product>(saved,HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/product/{productId}")
	public ResponseEntity<Product> delete(@PathVariable(name = "productId",required = true) Integer productID) throws ProductException, CategoryException{
		Product product = productService.deleteProduct(productID);
		
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
}
