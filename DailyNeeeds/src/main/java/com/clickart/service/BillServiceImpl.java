package com.clickart.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clickart.exception.BillException;
import com.clickart.exception.OrderException;
import com.clickart.model.Bill;
import com.clickart.model.Order;
import com.clickart.repository.BillRepo;
import com.clickart.repository.OrderRepo;

@Service
public class BillServiceImpl implements BillService{
	
	@Autowired
	private BillRepo billRepo;
	
	@Autowired
	private OrderRepo orderRepo;

	@Override
	public Bill addBill(Bill bill, int orderId) throws BillException, OrderException {
		Optional<Order> ord=orderRepo.findById(orderId);
		if(ord.isEmpty())
			throw new OrderException("order not found with id "+orderId);
		
		bill.setOrder(ord.get());
		bill.setTotalPrice(ord.get().getCart().getTotalPrice());
		bill.setTotalItem(ord.get().getCart().getTotalItems());
		return billRepo.save(bill);
	}

	@Override
	public Bill viewBill(int billId) throws BillException {
		Optional<Bill>bil=billRepo.findById(billId);
		if(bil.isEmpty())
			throw new BillException("bill not found with id "+billId);
		
		return bil.get();
	}

	@Override
	public List<Bill> viewBills(LocalDate startDate, LocalDate endDate) throws BillException {
		List<Bill> list=billRepo.billBetweenDate(startDate, endDate);
		if(list.size()==0)
			throw new BillException("bill not found between "+startDate+" and "+endDate);
		return list;
	}

	@Override
	public Bill updateBill(Bill bill) throws BillException {
		return null;
	}

	@Override
	public Bill removeBill(int billId) throws BillException {
		Optional<Bill>bil=billRepo.findById(billId);
		if(bil.isEmpty())
			throw new BillException("bill not found with id "+billId);
		Bill b=bil.get();
		b.setOrder(null);
		billRepo.delete(b);
		return b;
	}

}
