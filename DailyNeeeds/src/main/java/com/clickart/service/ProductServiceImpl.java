package com.clickart.service;

import java.util.List;  
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clickart.exception.CategoryException;
import com.clickart.exception.ProductException;
import com.clickart.model.Category;
import com.clickart.model.CurrentAdminSession;
import com.clickart.model.Product;
import com.clickart.repository.AdminSessionRepo;
import com.clickart.repository.CategoryRepo;
import com.clickart.repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private AdminSessionRepo adminSessionRepo;

	@Override
	public Product addProduct(Product product, int categoryId, String key) throws ProductException, CategoryException {
		
		List<CurrentAdminSession> list=adminSessionRepo.findByUuid(key);
		
		if(list.size()==0)
			throw new ProductException("you don't have authority to add product");
		
		Optional<Category> cat=categoryRepo.findById(categoryId);
		if(!cat.isPresent()) {
			throw new CategoryException("category not found with id "+categoryId);
		}
		Category category=cat.get();
		category.getProducts().add(product);
		
		product.setCategory(category);
		

		
		return productRepo.save(product);
		
	}

	@Override
	public Product viewProduct(int productId) throws ProductException {
		Optional<Product>p=productRepo.findById(productId);
		if(p.isPresent()) {
			return p.get();
		}
		throw new ProductException("product not found with id "+productId);
	}

	@Override
	public List<Product> allProduct() throws ProductException {
		List<Product>list=productRepo.findAll();
		if(list.size()==0) {
			throw new ProductException("list is empty");
		}
		return list;
	}

	@Override
	public Product removeProduct(int productId, String key) throws ProductException {
		
		List<CurrentAdminSession> list=adminSessionRepo.findByUuid(key);
		
		if(list.size()==0)
			throw new ProductException("you don't have authority to remove product");
		
		
		Optional<Product>p=productRepo.findById(productId);
		if(p.isPresent()) {
			p.get().setCategory(null);
			productRepo.delete(p.get());
			return p.get();
		}
		throw new ProductException("product not found with id "+productId);
	}

	@Override
	public Product updateProduct(Product product, String key) throws ProductException {
		
		List<CurrentAdminSession> list=adminSessionRepo.findByUuid(key);
		
		if(list.size()==0)
			throw new ProductException("you don't have authority to update product");
		
		
		Optional<Product>p=productRepo.findById(product.getProductId());
		if(p.isPresent()) {
			Product pro=p.get();
			
			if(product.getDescription()!="")
				pro.setDescription(product.getDescription());
			
			if(product.getPrice()!=null)
				pro.setPrice(product.getPrice());
			
			if(product.getProductName()!="")
				pro.setProductName(product.getProductName());
			
			if(product.getQuantity()!=null)
				pro.setQuantity(product.getQuantity());
			
			if(product.getUrl()!="")
				pro.setUrl(product.getUrl());
			
			return productRepo.save(pro);
		}
		throw new ProductException("product not found with id "+product.getProductId());
	}

	@Override
	public List<Product> productByName(String name) throws ProductException {
		List<Product>list=productRepo.findByProductName(name);
		if(list.size()==0) {
			throw new ProductException("product not found with name "+name);
		}
		return list;
	}

	@Override
	public List<Product> productByNameLike(String name) throws ProductException {
		List<Product>list=productRepo.findByProductNameLike(name);
		if(list.size()==0) {
			throw new ProductException("product not found with name "+name);
		}
		return list;
	}

	@Override
	public List<Product> top5() throws ProductException {
		List<Product>list=productRepo.findTop5ByOrderBySoldCountDesc();
		if(list.size()==0) {
			throw new ProductException("product not found");
		}
		return list;
	}

}
