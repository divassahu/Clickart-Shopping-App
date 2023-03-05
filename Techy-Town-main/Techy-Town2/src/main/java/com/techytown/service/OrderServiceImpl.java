package com.techytown.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techytown.enums.OrderStatus;
import com.techytown.exceptions.CardException;
import com.techytown.exceptions.CartException;
import com.techytown.exceptions.OrderException;
import com.techytown.models.Card;
import com.techytown.models.Cart;
import com.techytown.models.Category;
import com.techytown.models.OrderReceipt;
import com.techytown.models.Orders;
import com.techytown.models.Product;
import com.techytown.models.User;
import com.techytown.repository.CardRepository;
import com.techytown.repository.CartRepository;
import com.techytown.repository.CategoryRepository;
import com.techytown.repository.OrderRepository;
import com.techytown.repository.ProductRepository;
import com.techytown.repository.ReceiptRepository;
import com.techytown.repository.UserRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private CardRepository cardRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ReceiptRepository receiptRepo;

	@Override
	public OrderReceipt checkoutItems(String username,Integer cardId) throws UsernameNotFoundException, CartException, CardException{
		Optional<User> userOpt = userRepo.findByUsername(username);
		Optional<Card> cardOpt = cardRepo.findById(cardId);
		
		if(userOpt.isPresent()) {
			if(cardOpt.isPresent()) {
				Optional<Cart> shoppingCartOpt =  cartRepo.findById(userOpt.get().getCart().getCartId());
				
				if(shoppingCartOpt.isPresent() 
						&& shoppingCartOpt.get().getProd_Qty().size() >0) {
					Orders ord = new Orders();
					
					ord.setCart(shoppingCartOpt.get());
					ord.setOrderingDate(LocalDateTime.now());
					ord.setDeliveryDate(LocalDateTime.now().plusMinutes(10));
					ord.setStatus(OrderStatus.ORDER_IN_PROCESS);
					
					Orders saved = orderRepo.save(ord);
					
					OrderReceipt receipt = new OrderReceipt();
					
					Map<Integer,Integer> cartItems = shoppingCartOpt.get().getProd_Qty();
					
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
					
					
					receipt.setOrderId(saved.getId());
					receipt.setUsername(username);
					receipt.setOrderingDate(LocalDateTime.now());
					receipt.setDeliveryDate(LocalDateTime.now().plusMinutes(10));
					receipt.setItems(cartItems);
					receipt.setStatus(saved.getStatus());
					receipt.setSavings(mrp-disc);
					receipt.setTotalMrp(mrp);
					
					OrderReceipt savedReceipt =  receiptRepo.save(receipt);
					return savedReceipt;
					
					
				}else {
					throw new CartException("Check Cart Once Again !");
				}
			
			}else {
				throw new CardException(username+" is not associated with any Credit/Debit Card !");
			}
			
		}else {
			throw new UsernameNotFoundException("No Customer Found !");
		}
	}

	@Override
	public OrderReceipt cancelOrder(Integer orderId,String username) throws OrderException {
		Optional<Orders> orderOpt = orderRepo.findById(orderId);
		Optional<User> userOpt = userRepo.findByUsername(username);
		
		if(orderOpt.isPresent()&& userOpt.isPresent() && orderOpt.get().getCart().getCartId() == userOpt.get().getCart().getCartId()) {
			orderRepo.delete(orderOpt.get());
			Orders saved = orderOpt.get();
			
			
			Optional<OrderReceipt> receipt = receiptRepo.findById(orderId);
			
			
			return receipt.get();
			
		}else {
			throw new OrderException("Order Associated with User "+username+" Not Found !");
		}
	}

	@Override
	public Integer totalOrders() {
		Integer max = 0;
		
		List<Orders> orders = orderRepo.findAll();
		max = orders.size();
		
		return max;
	}

	@Override
	public OrderReceipt getReceipt(Integer orderId, String username) throws OrderException {
		Optional<OrderReceipt> orderROpt = receiptRepo.findById(orderId);
		Optional<User> userOpt = userRepo.findByUsername(username);
		
		if(orderROpt.isPresent()&& userOpt.isPresent()) {
			
			OrderReceipt saved = orderROpt.get();	
			return saved;
			
			
		}else {
			throw new OrderException("Order Associated with User "+username+" Not Found !");
		}
	}

	@Override
	public List<Orders> allOrdersByUser(String username) throws UsernameNotFoundException {
		return orderRepo.findAll();
				
	}

	@Override
	public Set<Product> top5Products() {
		
		
		List<OrderReceipt> orders = receiptRepo.findAll();
		Set<Integer> topPros = new HashSet<>();
		
		orders.forEach((item)->{
			Set<Integer> top = item.getItems().keySet();
			topPros.addAll(top);
		});
		
		Set<Product> topSellingProducts = new HashSet<>();
		
		topPros.forEach((item)->{
			Optional<Product> pro = productRepo.findById(item);
			topSellingProducts.add(pro.get());
		});
		
		return topSellingProducts;
	}

	@Override
	public Set<Category> top5Categories() {
		
		List<OrderReceipt> orders = receiptRepo.findAll();
		Set<Integer> topPros = new HashSet<>();
		
		orders.forEach((item)->{
			Set<Integer> top = item.getItems().keySet();
			topPros.addAll(top);
		});
		
		Set<Category> topSellingCats = new HashSet<>();
		
		topPros.forEach((item)->{
			Optional<Product> pro = productRepo.findById(item);
			Optional<Category> cat = categoryRepo.findById(pro.get().getCategory().getCategoryId());
			topSellingCats.add(cat.get());
		});
		
		return topSellingCats;
	}

	@Override
	public Double totalRevenue() {
		// TODO Auto-generated method stub
		
		List<OrderReceipt> orders = receiptRepo.findAll();
		Double revenue =0.0;
		
		for (OrderReceipt o:orders) {
			revenue += o.getTotalMrp();
		    
		}
		
		return revenue;
	}


}
