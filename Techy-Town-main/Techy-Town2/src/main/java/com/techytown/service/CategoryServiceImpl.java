package com.techytown.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techytown.exceptions.CategoryException;
import com.techytown.exceptions.ProductException;
import com.techytown.models.Category;
import com.techytown.models.Product;
import com.techytown.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	
	@Autowired
	private CategoryRepository catRepo;
	
	@Override
	public Category addNewCategory(Category category){
				return catRepo.save(category);
		
	}

	@Override
	public List<Product> getProductsByCategory(Integer catId) throws CategoryException, ProductException{
		Optional<Category> catOpt = catRepo.findById(catId);
		
		if(catOpt.isPresent()) {
			List<Product> products =  catOpt.get().getProducts();
			
			if(products.size() != 0) {
				return products;
			}else {
				throw new ProductException("No Product Found For This Category !");
			}
		}else {
			throw new CategoryException("Category Not Found !");
		}
	}

	@Override
	public Category updateCategory(Category category) throws CategoryException{
	
		Optional<Category> catOpt = catRepo.findById(category.getCategoryId());
		
		if(catOpt.isPresent()) {
			return catRepo.save(category);
		}else {
			throw new CategoryException("Category Not Found !");
		}
			
		
	}

	@Override
	public Category removeCategory(Integer catId) throws CategoryException {
		
		Optional<Category> catOpt = catRepo.findById(catId);
		
		if(catOpt.isPresent()) {
			Category cat = catOpt.get();
			catRepo.delete(cat);
			return cat;
		}else {
			throw new CategoryException("Category Not Found !");
		}
	
	}

	@Override
	public Category getCategorybyId(Integer catId) throws CategoryException {
		
		Optional<Category> catOpt = catRepo.findById(catId);
		
		if(catOpt.isPresent()) {
			return catOpt.get();
		}else {
			throw new CategoryException("Category Not Found !");
		}
		
	}

	@Override
	public Set<Category> getAllCategories() throws CategoryException {
		
		List<Category> categories = catRepo.findAll();
		
		Set<Category> set = new HashSet<>();
		
		for(Category c:categories) {
			set.add(c);
		}
		
		if(set.size() == 0) {
			throw new CategoryException("Categories Not Found !");
		}
		
		return set;
		
	}

	@Override
	public Set<String> getCategoryTypes() throws CategoryException {
		
		List<Category> categories = catRepo.findAll();
		
		Set<String> types = new HashSet<>();
		
		if(categories.size() != 0) {
			categories.forEach( (type) ->{
				types.add(type.getType());
			});
			
			return types;
		}else {
			throw new CategoryException("Category Not Found !");
		}
	}

}