package com.clickart.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clickart.exception.CartException;
import com.clickart.exception.OrderException;
import com.clickart.model.Order;
import com.clickart.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/addOrder/{cartId}")
	public ResponseEntity<Order> addOrder(@RequestBody Order order, @PathVariable("cartId") int cartId) throws OrderException, CartException {
		Order o=orderService.addOrder(order, cartId);
		return new ResponseEntity<Order>(o,HttpStatus.ACCEPTED);
	}

	@GetMapping("/viewOrder/{orderId}")
	public ResponseEntity<Order> viewOrder(@PathVariable("orderId") int orderId) throws OrderException {
		Order o=orderService.viewOrder(orderId);
		return new ResponseEntity<Order>(o,HttpStatus.ACCEPTED);
	}

	@GetMapping("/viewOrdersByDate/{startDate}/{endDate}")
	public ResponseEntity<List<Order>> viewOrdersByDate(@PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) throws OrderException {
		List<Order> list=orderService.viewOrdersByDate(startDate, endDate);
		return new ResponseEntity<List<Order>>(list,HttpStatus.ACCEPTED);
	}

	@PutMapping("/updateOrderStatus/{orderId}/{status}")
	public ResponseEntity<Order> updateOrderStatus(@PathVariable("orderId") int orderId, @PathVariable("status") String status) throws OrderException {
		Order o=orderService.updateOrderStatus(orderId, status);
		return new ResponseEntity<Order>(o,HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteOrder/{orderId}")
	public ResponseEntity<Order> deleteOrder(@PathVariable("orderId") int orderId) throws OrderException {
		Order o=orderService.deleteOrder(orderId);
		return new ResponseEntity<Order>(o,HttpStatus.ACCEPTED);
	}


}
