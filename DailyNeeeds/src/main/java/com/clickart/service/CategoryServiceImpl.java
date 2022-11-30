package com.clickart.service;

import java.util.List;  
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clickart.exception.CategoryException;
import com.clickart.model.Category;
import com.clickart.model.CurrentAdminSession;
import com.clickart.model.Product;
import com.clickart.repository.AdminSessionRepo;
import com.clickart.repository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private AdminSessionRepo adminSessionRepo;

	@Override
	public Category addCategory(Category category, String key) throws CategoryException {

		List<CurrentAdminSession> list=adminSessionRepo.findByUuid(key);
		
		if(list.size()==0)
			throw new CategoryException("you are not authorised to add category");
		
		List<Category> cat=categoryRepo.findByName(category.getName());
		
		if(cat.size()!=0) {
			throw new CategoryException("category already registered with this name id is "+cat.get(0).getCategoryId());
		}
		
		return categoryRepo.save(category);
	}

	@Override
	public Category viewCategory(int categoryId) throws CategoryException {
		Optional<Category> c=categoryRepo.findById(categoryId);
		if(c.isPresent()) {
			return c.get();
		}
		throw new CategoryException("category not found with id "+categoryId);
	}

	@Override
	public Category deleteCategory(int categoryId, String key) throws CategoryException {

		List<CurrentAdminSession> list=adminSessionRepo.findByUuid(key);
		
		if(list.size()==0)
			throw new CategoryException("you are not authorised to delete category");
		
		Optional<Category> c=categoryRepo.findById(categoryId);
		if(c.isPresent()) {
			categoryRepo.delete(c.get());
			return c.get();
		}
		throw new CategoryException("category not found with id "+categoryId);
	}

	@Override
	public List<Category> allCategory() throws CategoryException {
		List<Category>list=categoryRepo.findAll();
		if(list.size()==0) {
			throw new CategoryException("empty list");
		}
		return list;
	}

	@Override
	public List<Product> productByCategory(int categoryId) throws CategoryException {
		Optional<Category> c=categoryRepo.findById(categoryId);
		if(c.isPresent()) {
			return c.get().getProducts();
		}
		throw new CategoryException("category not found with id "+categoryId);
	}

}
