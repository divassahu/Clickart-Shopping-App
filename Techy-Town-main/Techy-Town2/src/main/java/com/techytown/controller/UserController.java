package com.techytown.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techytown.exceptions.CardException;
import com.techytown.exceptions.CartException;
import com.techytown.exceptions.CategoryException;
import com.techytown.exceptions.OrderException;
import com.techytown.exceptions.ProductException;
import com.techytown.models.Card;
import com.techytown.models.Cart;
import com.techytown.models.CartDTO;
import com.techytown.models.Category;
import com.techytown.models.OrderReceipt;
import com.techytown.models.Orders;
import com.techytown.models.Product;
import com.techytown.security.jwt.JwtUtils;
import com.techytown.service.CardService;
import com.techytown.service.CartService;
import com.techytown.service.CategoryService;
import com.techytown.service.OrderService;
import com.techytown.service.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {


	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;
		
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CardService cardService;
	
	@Autowired
	private JwtUtils jwtUtils;

	
	@GetMapping("/username")
	  public String getUsername(HttpServletRequest request) {
		
		String cookie = jwtUtils.getJwtFromCookies(request);
		String username = jwtUtils.getUserNameFromJwtToken(cookie);
	    return username+" Welcome To Techy Town";
	  }

	
	//--------product controller menu-------------
	
	//search the product
	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> searchProductById(@PathVariable(name = "productId",required = true) Integer productID) throws ProductException, CategoryException{
		Product product = productService.searchProductById(productID);
		
		return new ResponseEntity<Product>(product,HttpStatus.FOUND);
	}
	
	//get products by category
	@GetMapping("/{catId}/products")
	public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable(name = "catId",required = true) Integer catID) throws ProductException, CategoryException{
		List<Product> products = categoryService.getProductsByCategory(catID);
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	//by product get category
	@GetMapping("/{productId}/category")
	public ResponseEntity<Category> getCategoryByProduct(@PathVariable(name = "productId",required = true) Integer productID) throws ProductException, CategoryException{
		Category cat = productService.getCategory(productID);
		
		return new ResponseEntity<Category>(cat,HttpStatus.OK);
	}
	
	
	//----------------see categories list
	@GetMapping("/category")
	public ResponseEntity<Set<Category>> categories() throws CategoryException{
		Set<Category> cats = categoryService.getAllCategories();
		
		return new ResponseEntity<Set<Category>>(cats,HttpStatus.OK);
	}
	
	//get particular category
	@GetMapping("/category/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable(name = "id",required = true) Integer catID) throws ProductException, CategoryException{
		Category cat = categoryService.getCategorybyId(catID);
		
		return new ResponseEntity<Category>(cat,HttpStatus.FOUND);
	}
	
	
	
	//------------------cart menu and order it
	
	//add the product no duplicates are allowed
	
	@PostMapping("/cart")
	public ResponseEntity<Cart> addToCart(@Valid @RequestBody CartDTO dto,HttpServletRequest request) throws ProductException, CartException{
		String cookie = jwtUtils.getJwtFromCookies(request);
		String username = jwtUtils.getUserNameFromJwtToken(cookie);
		
		
		Cart added = cartService.addProductToCart(dto, username);
		return new ResponseEntity<Cart>(added,HttpStatus.CREATED);
		
	}
	
	
	@DeleteMapping("/cart")
	public ResponseEntity<Cart> removeProduct(@RequestParam Integer productId,HttpServletRequest request) throws  ProductException, CartException{
		String cookie = jwtUtils.getJwtFromCookies(request);
		String username = jwtUtils.getUserNameFromJwtToken(cookie);
		
		
		Cart removed = cartService.removeProductFromCart(productId, username);
		return new ResponseEntity<Cart>(removed,HttpStatus.OK);
	
	}
	
	
	
	
	@GetMapping("/cart/total")
	public ResponseEntity<String> totalAmount(HttpServletRequest request) throws CartException{
		
		String cookie = jwtUtils.getJwtFromCookies(request);
		String username = jwtUtils.getUserNameFromJwtToken(cookie);
		
		Double mrp = cartService.totalMrpAmount(username);
		Double disc = cartService.totalDiscAmount(username);
		String savings = cartService.totalSavings(username);
		
		return new ResponseEntity<String>(" Total Max Price:"+mrp
				+"\n Total Discounted Price:"+disc
				+"\n Total Savings :"+savings,HttpStatus.OK);
	
	}
	
	@GetMapping("/cart/items")
	public ResponseEntity<Map<Integer, Integer>> items(HttpServletRequest request) throws CartException{
		
		String cookie = jwtUtils.getJwtFromCookies(request);
		String username = jwtUtils.getUserNameFromJwtToken(cookie);
		
		Map<Integer, Integer> items = cartService.allCartItems(username);
		
		return new ResponseEntity<Map<Integer, Integer>>(items,HttpStatus.OK);
	
	}
	
	@GetMapping("/cart/empty")
	public ResponseEntity<String> empty(HttpServletRequest request) throws CartException{
		
		String cookie = jwtUtils.getJwtFromCookies(request);
		String username = jwtUtils.getUserNameFromJwtToken(cookie);
		
		String msg = cartService.emptyingCart(username);
		
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	
	}

	// to chekout order you need to save card
	
	@PostMapping("/card")
	public ResponseEntity<Card> addCard(@RequestBody Card card,HttpServletRequest request) throws UsernameNotFoundException, CartException, CardException{
		
		String cookie = jwtUtils.getJwtFromCookies(request);
		String username = jwtUtils.getUserNameFromJwtToken(cookie);
		
		Card saved = cardService.addCard(card, username);
		
		return new ResponseEntity<Card>(saved,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/card")
	public ResponseEntity<Card> removeCard(@RequestParam Integer cardId,HttpServletRequest request) throws UsernameNotFoundException, CartException, CardException, OrderException{
		
		String cookie = jwtUtils.getJwtFromCookies(request);
		String username = jwtUtils.getUserNameFromJwtToken(cookie);
		
		Card deleted = cardService.removeCard(cardId, username);
		
		return new ResponseEntity<Card>(deleted,HttpStatus.OK);
	}
	
	
	
	
	//---------------now he can order it as he saved all cart
	
	@PostMapping("/order")
	public ResponseEntity<OrderReceipt> checkoutOrder(@RequestParam Integer cardId,HttpServletRequest request) throws UsernameNotFoundException, CartException, CardException{
		
		String cookie = jwtUtils.getJwtFromCookies(request);
		String username = jwtUtils.getUserNameFromJwtToken(cookie);
		
		OrderReceipt checkout = orderService.checkoutItems(username, cardId);
		cartService.emptyingCart(username);
		return new ResponseEntity<OrderReceipt>(checkout,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}/order")
	public ResponseEntity<OrderReceipt> getOrder(@PathVariable(name = "id",required = true) Integer orderId,HttpServletRequest request) throws UsernameNotFoundException, CartException, CardException, OrderException{
		
		String cookie = jwtUtils.getJwtFromCookies(request);
		String username = jwtUtils.getUserNameFromJwtToken(cookie);
		
		OrderReceipt receipt = orderService.getReceipt(orderId, username);
		
		return new ResponseEntity<OrderReceipt>(receipt,HttpStatus.OK);
	}
	
	@GetMapping("/order")
	public ResponseEntity<List<Orders>> allOrders(HttpServletRequest request) throws UsernameNotFoundException, CartException, CardException, OrderException{
		
		String cookie = jwtUtils.getJwtFromCookies(request);
		String username = jwtUtils.getUserNameFromJwtToken(cookie);
		
		List<Orders> receipt = orderService.allOrdersByUser(username);
		
		return new ResponseEntity<List<Orders>>(receipt,HttpStatus.OK);
	}
	
	@DeleteMapping("/order")
	public ResponseEntity<OrderReceipt> cancelOrder(@RequestParam Integer orderId,HttpServletRequest request) throws UsernameNotFoundException, CartException, CardException, OrderException{
		
		String cookie = jwtUtils.getJwtFromCookies(request);
		String username = jwtUtils.getUserNameFromJwtToken(cookie);
		
		OrderReceipt cancel = orderService.cancelOrder(orderId, username);
		
		return new ResponseEntity<OrderReceipt>(cancel,HttpStatus.OK);
	}
	

	
}
