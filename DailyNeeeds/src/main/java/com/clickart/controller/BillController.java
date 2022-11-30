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

import com.clickart.exception.BillException;
import com.clickart.exception.OrderException;
import com.clickart.model.Bill;
import com.clickart.service.BillService;

@RestController
@RequestMapping("/bill")
public class BillController {
	
	@Autowired
	private BillService billService;
	
	@PostMapping("/add/{id}")
	public ResponseEntity<Bill> addBill(@RequestBody Bill bill, @PathVariable("id") int orderId) throws BillException, OrderException {
		Bill b=billService.addBill(bill, orderId);
		return new ResponseEntity<Bill>(b,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/view/{id}")
	public ResponseEntity<Bill> viewBill(@PathVariable("id") int billId) throws BillException {
		Bill b=billService.viewBill(billId);
		return new ResponseEntity<Bill>(b,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/view/{startDate}/{endDate}")
	public ResponseEntity<List<Bill>> viewBills(@PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) throws BillException {
		List<Bill>b=billService.viewBills(startDate, endDate);
		return new ResponseEntity<List<Bill>>(b,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Bill> updateBill(@RequestBody Bill bill) throws BillException {
		Bill b=billService.updateBill(bill);
		return new ResponseEntity<Bill>(b,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Bill> removeBill(@PathVariable("id") int billId) throws BillException {
		Bill b=billService.removeBill(billId);
		return new ResponseEntity<Bill>(b,HttpStatus.ACCEPTED);
	}

}
