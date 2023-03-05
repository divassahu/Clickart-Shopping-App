package com.techytown.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techytown.exceptions.CategoryException;
import com.techytown.exceptions.ProductException;
import com.techytown.models.Category;
import com.techytown.models.Product;
import com.techytown.repository.CategoryRepository;
import com.techytown.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CategoryRepository catRepo;
	
	@Override
	public Product saveProduct(Product product,Integer categoryId) throws ProductException, CategoryException {
		// TODO Auto-generated method stub
			Optional<Category> catOpt = catRepo.findById(categoryId);
			if(catOpt.isPresent()){
				
				Product saved = productRepo.save(product);
				saved.setCategory(catOpt.get());
				
				return productRepo.saveAndFlush(saved);
				
			}else {
				throw new CategoryException("Category Does Not Exists !");
			}
			
		
		
//		Product saved = catRepo.findById(categoryId).map(
//				category ->{
//					product.setCategory(category);
//					return productRepo.save(product);
//				}).orElseThrow(()-> new CategoryException("Category Not found"));
//		
//		return saved;
		
	}

	@Override
	public Category getCategory(Integer productId) throws ProductException, CategoryException {
		
		Optional<Product> productOpt = productRepo.findById(productId);
		
		if(productOpt.isPresent()) {
			Category savedCat = productOpt.get().getCategory();
			if(savedCat != null) {
				return savedCat;
			}else {
				throw new CategoryException("Category Not Found !");
			}
		}else {
			throw new ProductException("Product Not Found !");
		}
		
	}

	@Override
	public Product searchProductById(Integer productId) throws ProductException {
		
		Optional<Product> productOpt =  productRepo.findById(productId);
		
		if(productOpt.isPresent()) {
			return productOpt.get();
		}else {
			throw new ProductException("Product Not Found !");
		}
		
	}

	@Override
	public Product deleteProduct(Integer productId) throws ProductException {
		
		Optional<Product> productOpt =  productRepo.findById(productId);
		
		if(productOpt.isPresent()) {
			Product deleted = productOpt.get();
			productRepo.delete(deleted);
			return deleted;
		}else {
			throw new ProductException("Product Not Found !");
		}
	}

	@Override
	public List<Product> products() throws ProductException {
		List<Product> products = productRepo.findAll();
		
		if(products.size() != 0) {
			return products;
		}else {
			throw new ProductException("Products Not Found !");
		}
	}

}
