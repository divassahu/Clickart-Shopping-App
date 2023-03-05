package com.techytown.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techytown.models.Payment;
import com.techytown.repository.OrderRepository;
import com.techytown.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private PaymentRepository paymentRepo;

	@Override
	public Integer getAllPayments() {
		// TODO Auto-generated method stub
		List<Payment> paid = paymentRepo.findAll();
		return paid.size();
	}
	
	

}
