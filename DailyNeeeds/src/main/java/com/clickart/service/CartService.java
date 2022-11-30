package com.clickart.service;

import com.clickart.exception.CartException; 
import com.clickart.exception.CustomerException;
import com.clickart.exception.ProductException;
import com.clickart.model.Cart;

public interface CartService {
	
	public Cart addCart(Cart cart,String key)throws CustomerException;
	
	public Cart viewCart(int cartId,String key)throws CartException;
	
	public Cart addItemIntoCart(int productId,String key)throws CartException,ProductException;
	
	public Cart removeItemFromCart(int productId,String key)throws CartException,ProductException;
	
	public Cart increaseQuantity(int productId, int quantity,String key)throws CartException,ProductException;
	
	public Cart decreaseQuantity(int productId, int quantity,String key)throws CartException,ProductException;
	
	public Cart clearCart(String key)throws CartException;
	
	public Cart deleteCart(int cartId,String key)throws CartException;
	
	public Cart cartByCustomerId(String key)throws CartException;
	
}
