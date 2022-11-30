package com.clickart.service;

import java.util.List;  
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clickart.exception.CartException;
import com.clickart.exception.CustomerException;
import com.clickart.exception.ProductException;
import com.clickart.model.Cart;
import com.clickart.model.CurrentUserSession;
import com.clickart.model.Customer;
import com.clickart.model.Product;
import com.clickart.repository.CartRepo;
import com.clickart.repository.CustomerRepo;
import com.clickart.repository.ProductRepo;
import com.clickart.repository.UserSessionRepo;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private UserSessionRepo userSessoinRepo;
	
	public int cartTotal(List<Product>list) {
		int total=0;
		for(Product p:list) {
			if(p.getQuantity()!=0) {
				total+=p.getPrice()*p.getQuantity();
			}
		}
		return total;
	}
	
	public int cartTotalQuantity(List<Product>list) {
		int total=0;
		for(Product p:list) {
			if(p.getQuantity()!=0) {
				total+=p.getQuantity();
			}
		}
		return total;
	}

	@Override
	public Cart addCart(Cart cart,String key) throws CustomerException{
		List<CurrentUserSession>cUser=userSessoinRepo.findByUuid(key);
		if(cUser.size()==0)
			throw new CustomerException("you are not logged in please log in");
		
		CurrentUserSession currentUser=cUser.get(0);
		
		Optional<Customer>cus=customerRepo.findById(cart.getCustomer().getCustomerId());
		if(cus.isEmpty()) {
			throw new CustomerException("customer not found with id "+cart.getCustomer().getCustomerId());
		}
		if(cus.get().getCart()!=null) {
			throw new CustomerException("customer "+cart.getCustomer().getCustomerId()+" already has cart "+cus.get().getCart().getCartId());
		}
		Customer customer=cus.get();
		
		if(customer.getCustomerId()!=currentUser.getUserId())
			throw new CustomerException("userID doesn't exist please try again");
		
		cart.setCustomer(customer);

		return cartRepo.save(cart);
	}

	@Override
	public Cart viewCart(int cartId,String key) throws CartException {
		List<CurrentUserSession>cUser=userSessoinRepo.findByUuid(key);
		if(cUser.size()==0)
			throw new CartException("you are not logged in please log in");
		
		CurrentUserSession currentUser=cUser.get(0);
		
		Optional<Cart>cart=cartRepo.findById(cartId);
		if(cart.isPresent()) {
			
			if(cart.get().getCustomer().getCustomerId()!=currentUser.getUserId())
				throw new CartException("userID doesn't exist please try again");
			
			return cart.get();
		}
		throw new CartException("cart not found with id "+cartId);
	}

	@Override
	public Cart addItemIntoCart(int productId,String key) throws CartException, ProductException {
		
		List<CurrentUserSession>cUser=userSessoinRepo.findByUuid(key);
		if(cUser.size()==0)
			throw new CartException("you are not logged in please log in");
		
		CurrentUserSession currentUser=cUser.get(0);
		
		Cart cart=cartRepo.findByCustomerId(currentUser.getUserId());
		

		
		Optional<Product>pro=productRepo.findById(productId);
		if(pro.isEmpty()) {
			throw new ProductException("product not found with id "+productId);
		}
		
		Product product=pro.get();
		

		boolean flag=true;
		for(Product p:cart.getProducts()) {
			if(p.getProductId()==productId) {
				flag=false;
				p.setQuantity(p.getQuantity()+1);
			}
		}
		if(flag) {
			product.setQuantity(1);
			cart.getProducts().add(product);
		}
		
		cart.setTotalPrice(cartTotal(cart.getProducts()));
		cart.setTotalItems(cartTotalQuantity(cart.getProducts()));
		
		return cartRepo.save(cart);
		
	}

	@Override
	public Cart removeItemFromCart(int productId, String key) throws CartException, ProductException {
		
		List<CurrentUserSession>cUser=userSessoinRepo.findByUuid(key);
		if(cUser.size()==0)
			throw new CartException("you are not logged in please log in");
		
		CurrentUserSession currentUser=cUser.get(0);
		
		
		Cart cart=cartRepo.findByCustomerId(currentUser.getUserId());
		

		
		boolean flag=cart.getProducts().removeIf(p-> p.getProductId()==productId);
		
		if(!flag) {
			throw new ProductException("product "+productId+" is not there in cart "+cart.getCartId());
		}
		
		cart.setTotalPrice(cartTotal(cart.getProducts()));
		cart.setTotalItems(cartTotalQuantity(cart.getProducts()));
		
		return cartRepo.save(cart);
	}

	@Override
	public Cart increaseQuantity(int productId, int quantity,String key) throws CartException, ProductException {
		

		List<CurrentUserSession>cUser=userSessoinRepo.findByUuid(key);
		if(cUser.size()==0)
			throw new CartException("you are not logged in please log in");
		
		CurrentUserSession currentUser=cUser.get(0);
		
		Optional<Product>pro=productRepo.findById(productId);
		if(pro.isEmpty()) {
			throw new ProductException("product not found with id "+productId);
		}
		
		Cart cart=cartRepo.findByCustomerId(currentUser.getUserId());
		
		if(cart.getCustomer().getCustomerId()!=currentUser.getUserId())
			throw new CartException("userID doesn't exist please try again");

		
		cart.getProducts().forEach(p->{
			if(p.getProductId()==productId) {
				p.setQuantity(p.getQuantity()+quantity);
			}
		});
		cart.setTotalPrice(cartTotal(cart.getProducts()));
		cart.setTotalItems(cartTotalQuantity(cart.getProducts()));
		return cartRepo.save(cart);
	}

	@Override
	public Cart decreaseQuantity(int productId, int quantity,String key) throws CartException, ProductException {
		
		List<CurrentUserSession>cUser=userSessoinRepo.findByUuid(key);
		if(cUser.size()==0)
			throw new CartException("you are not logged in please log in");
		
		CurrentUserSession currentUser=cUser.get(0);
		
		Optional<Product>pro=productRepo.findById(productId);

		if(pro.isEmpty()) {
			throw new ProductException("product not found with id "+productId);
		}
		Cart cart=cartRepo.findByCustomerId(currentUser.getUserId());

		
		cart.getProducts().forEach(p->{
			if(p.getProductId()==productId) {
				p.setQuantity(p.getQuantity()-quantity);
				if(p.getQuantity()<0) {
					p.setQuantity(0);
				}
			}
		});
		cart.setTotalPrice(cartTotal(cart.getProducts()));
		cart.setTotalItems(cartTotalQuantity(cart.getProducts()));
		return cartRepo.save(cart);
	}

	@Override
	public Cart clearCart(String key) throws CartException {
		
		List<CurrentUserSession>cUser=userSessoinRepo.findByUuid(key);
		if(cUser.size()==0)
			throw new CartException("you are not logged in please log in");
		
		CurrentUserSession currentUser=cUser.get(0);
		
		Cart cart=cartRepo.findByCustomerId(currentUser.getUserId());
		
		cart.getProducts().clear();
		cart.setTotalPrice(0);
		cart.setTotalItems(0);
		return cartRepo.save(cart);
	}

	@Override
	public Cart deleteCart(int cartId,String key) throws CartException {
		List<CurrentUserSession>cUser=userSessoinRepo.findByUuid(key);
		if(cUser.size()==0)
			throw new CartException("you are not logged in please log in");
		
		CurrentUserSession currentUser=cUser.get(0);
		
		Optional<Cart>cartOp=cartRepo.findById(cartId);
		if(cartOp.isEmpty()) {
			throw new CartException("cart not found with id "+cartId);
		}
		if(cartOp.get().getCustomer().getCustomerId()!=currentUser.getUserId())
			throw new CartException("userID doesn't exist please try again");

		cartRepo.delete(cartOp.get());
		return cartOp.get();
	}

	@Override
	public Cart cartByCustomerId(String key) throws CartException {
		List<CurrentUserSession>cUser=userSessoinRepo.findByUuid(key);
		if(cUser.size()==0)
			throw new CartException("you are not logged in please log in");
		
		CurrentUserSession currentUser=cUser.get(0);
		
		return cartRepo.findByCustomerId(currentUser.getUserId());
	}

}
