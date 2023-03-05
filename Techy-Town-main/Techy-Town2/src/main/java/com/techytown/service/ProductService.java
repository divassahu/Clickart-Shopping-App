package com.techytown.service;

import java.util.List;

import com.techytown.exceptions.CategoryException;
import com.techytown.exceptions.ProductException;
import com.techytown.models.Category;
import com.techytown.models.Product;

public interface ProductService {
	
	public Product saveProduct(Product product,Integer categoryId  ) throws ProductException,CategoryException;

	public Category getCategory(Integer productId) throws ProductException,CategoryException;
	
	public Product searchProductById(Integer productId) throws ProductException;
	
	public Product deleteProduct(Integer productId) throws ProductException;
	
	public List<Product> products() throws ProductException;
}
