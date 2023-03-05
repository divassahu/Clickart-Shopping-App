package com.techytown.service;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.techytown.exceptions.CardException;
import com.techytown.exceptions.CartException;
import com.techytown.exceptions.OrderException;
import com.techytown.models.Category;
import com.techytown.models.OrderReceipt;
import com.techytown.models.Orders;
import com.techytown.models.Product;

public interface OrderService {

	public OrderReceipt checkoutItems(String username,Integer cardId) throws 
						UsernameNotFoundException,CartException,CardException;
	
	public OrderReceipt cancelOrder(Integer orderId,String username) throws  OrderException;
	
	public OrderReceipt getReceipt(Integer orderId,String username)throws  OrderException;
	
	public List<Orders> allOrdersByUser(String username) throws UsernameNotFoundException;
	
	public Integer totalOrders();
	
	public Set<Product> top5Products();
	
	public Set<Category> top5Categories();
	
	public Double totalRevenue();
	
}
