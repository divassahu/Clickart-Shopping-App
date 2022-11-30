package com.clickart.service;

import java.util.List; 

import com.clickart.exception.CategoryException;
import com.clickart.model.Category;
import com.clickart.model.Product;


public interface CategoryService {
	
	public Category addCategory(Category category, String key)throws CategoryException;
	
	public Category viewCategory(int categoryId)throws CategoryException;
	
	public Category deleteCategory(int categoryId, String key)throws CategoryException;
	
	public List<Category> allCategory()throws CategoryException;
	
	public List<Product> productByCategory(int categoryId)throws CategoryException;

}
