package com.techytown.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techytown.exceptions.CartException;
import com.techytown.exceptions.ProductException;
import com.techytown.models.Cart;
import com.techytown.models.CartDTO;
import com.techytown.models.User;
import com.techytown.repository.CartRepository;
import com.techytown.repository.ProductRepository;
import com.techytown.repository.UserRepository;
import com.techytown.models.Product;



@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private ProductRepository productRepo;

	@Override
	public Cart addProductToCart(CartDTO items,String username) 
			throws ProductException,CartException{
		
			Optional<User> userOpt = userRepo.findByUsername(username);
			if(userOpt.isPresent() && items.getQuantity() !=0) {
				 User user = userOpt.get();
				 
				Optional<Cart> custCartOpt = cartRepo.findById(user.getCart().getCartId());
				Optional<Product> productOpt = productRepo.findById(items.getProductId());
				
				if(productOpt.isPresent()) {
					if(custCartOpt.isPresent()) {
						Cart custCart = custCartOpt.get();
						Product addProduct = productOpt.get();

						if(!custCart.getProd_Qty().containsKey(addProduct.getProductId())) {
							custCart.getProd_Qty().put(addProduct.getProductId(), items.getQuantity());	
						}else {
							custCart.getProd_Qty().replace(addProduct.getProductId(), items.getQuantity());
						}
						
						Map<Integer,Integer> cartItems = custCart.getProd_Qty();
						
						Double mrp = 0.0;
						
						for (HashMap.Entry<Integer,Integer> entry : cartItems.entrySet()) {
						     Integer key = entry.getKey();
						     Integer value = entry.getValue();
						     
						     Optional<Product> p = productRepo.findById(key);
						     
						     mrp += p.get().getMrp()*value;
						}
						
						Double disc = 0.0;
						
						for (HashMap.Entry<Integer,Integer> entry : cartItems.entrySet()) {
						     Integer key = entry.getKey();
						     Integer value = entry.getValue();
						     
						     Optional<Product> p = productRepo.findById(key);
						     
						     disc += p.get().getDiscountPrice()*value;
						}
						
						custCart.setDiscountExpenditure(disc);
						custCart.setMrpExpenditure(mrp);
						
						
						userRepo.save(user);
						return cartRepo.saveAndFlush(custCart);
					}else {
						throw new CartException("Cart Not Found.");
					}		
					
				}else {
					throw new ProductException("Product Not Found !");
				}
			}else {
				throw new UsernameNotFoundException("User Not Found OR items size is 0!");
			}
			

		
		
		
	}

	@Override
	public Cart removeProductFromCart(Integer productId,String username)
			throws ProductException, CartException{

		System.out.println("remove====================");
		
		Optional<User> userOpt = userRepo.findByUsername(username);
		Optional<Product> prodOpt = productRepo.findById(productId);
		
		 if(userOpt.isPresent() && prodOpt.isPresent()) {
			 Optional<Cart> custCart = cartRepo.findById(userOpt.get().getCart().getCartId());
			
			 
			 Map<Integer,Integer> items = custCart.get().getProd_Qty();
			 
			 System.out.println(items);
			 if(items.containsKey(prodOpt.get().getProductId())) {
				 items.remove(prodOpt.get().getProductId());
					
					Double mrp = 0.0;
					
					for (HashMap.Entry<Integer,Integer> entry : items.entrySet()) {
					     Integer key = entry.getKey();
					     Integer value = entry.getValue();
					     
					     Optional<Product> p = productRepo.findById(key);
					     
					     mrp += p.get().getMrp()*value;
					}
					
					Double disc = 0.0;
					
					for (HashMap.Entry<Integer,Integer> entry : items.entrySet()) {
					     Integer key = entry.getKey();
					     Integer value = entry.getValue();
					     
					     Optional<Product> p = productRepo.findById(key);
					     
					     disc += p.get().getDiscountPrice()*value;
					}
					
					custCart.get().setDiscountExpenditure(disc);
					custCart.get().setMrpExpenditure(mrp);
					
					
				 
			 }else {
				 throw new CartException("Product Is Not Present In Cart !");
			 }
			 
			return cartRepo.save(custCart.get());
		 }else {
			 throw new ProductException("Product Not Found !s");
		 }

		 
		
	}

	@Override
	public Double totalMrpAmount(String username) throws CartException {
		
			Optional<User> userOpt = userRepo.findByUsername(username);
			
			if(userOpt.isPresent()) {
				Optional<Cart> cartOpt =cartRepo.findById(userOpt.get().getCart().getCartId());
				
				if(cartOpt.isPresent()) {
					
					 return cartOpt.get().getMrpExpenditure();
				}else {
					throw new CartException("Cart Not Found !");
				}
			}else {
				throw new UsernameNotFoundException("User Not Found !");
			}
		
	}


	@Override
	public Map<Integer, Integer> allCartItems(String username) {
			
		Optional<User> userOpt = userRepo.findByUsername(username);
		
		if(userOpt.isPresent()) {
			Optional<Cart> cartOpt =cartRepo.findById(userOpt.get().getCart().getCartId());
			
				 return cartOpt.get().getProd_Qty();
		}else {
			throw new UsernameNotFoundException("user Not Found !");
		}
	
		
	}

	@Override
	public Double totalDiscAmount(String username) throws CartException {
		Optional<User> userOpt = userRepo.findByUsername(username);
		
		if(userOpt.isPresent()) {
			Optional<Cart> cartOpt =cartRepo.findById(userOpt.get().getCart().getCartId());
			
			if(cartOpt.isPresent()) {
				
				 return cartOpt.get().getDiscountExpenditure();
			}else {
				throw new CartException("Cart Not Found !");
			}
		}else {
			throw new UsernameNotFoundException("User Not Found !");
		}
	}

	@Override
	public String totalSavings(String username) throws CartException {
		Optional<User> userOpt = userRepo.findByUsername(username);
		
		if(userOpt.isPresent()) {
			Double discount = totalDiscAmount(username);
			Double mrp = totalMrpAmount(username);
			 
			return "You Saved "+(mrp-discount)+" Rs.";
		}else {
			throw new UsernameNotFoundException("User Not Found !");
		}
		 
	}

	@Override
	public String emptyingCart(String username) throws CartException {
		Optional<User> userOpt = userRepo.findByUsername(username);
		
		if(userOpt.isPresent()) {
			Optional<Cart> cartOpt =cartRepo.findById(userOpt.get().getCart().getCartId());
			
			if(cartOpt.isPresent()) {
				System.out.println("Before"+cartOpt.get().getProd_Qty());
				
				cartOpt.get().getProd_Qty().clear();
				cartRepo.save(cartOpt.get());
				
				System.out.println(cartOpt.get().getProd_Qty());
				 return username+" 's Cart have been emptied !";
			}else {
				throw new CartException("Cart Not Found !");
			}
		}else {
			throw new UsernameNotFoundException("User Not Found !");
		}
	}

}
