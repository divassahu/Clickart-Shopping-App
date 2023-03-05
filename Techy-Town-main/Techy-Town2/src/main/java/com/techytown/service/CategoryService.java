package com.techytown.service;

import java.util.List;
import java.util.Set;

import com.techytown.exceptions.CategoryException;
import com.techytown.exceptions.ProductException;
import com.techytown.models.Category;
import com.techytown.models.Product;

public interface CategoryService {

	public Category addNewCategory(Category category);
	
	public Category updateCategory(Category category) throws CategoryException;
	
	public Category removeCategory(Integer catId) throws CategoryException;
	
	public Category getCategorybyId(Integer catId) throws CategoryException;
	
	public List<Product> getProductsByCategory(Integer catId) throws CategoryException,ProductException;
	
	public Set<Category> getAllCategories() throws CategoryException;
	
	public Set<String> getCategoryTypes() throws CategoryException;
}
