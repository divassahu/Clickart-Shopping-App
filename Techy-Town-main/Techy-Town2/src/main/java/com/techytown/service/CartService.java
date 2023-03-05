package com.techytown.service;

import java.util.Map;

import com.techytown.exceptions.CartException;
import com.techytown.exceptions.ProductException;
import com.techytown.models.Cart;
import com.techytown.models.CartDTO;

public interface CartService {
	
	public Cart addProductToCart(CartDTO items,String username) 
			throws ProductException,CartException;
	
	public Cart removeProductFromCart(Integer ProductId,String username) 
			throws ProductException,CartException;
	
	public Map<Integer,Integer> allCartItems(String username);
	
	public String emptyingCart(String username) throws CartException;

	public Double totalMrpAmount(String username) throws CartException;
	
	public Double totalDiscAmount(String username) throws CartException;
	
	public String totalSavings(String username) throws CartException;
}
