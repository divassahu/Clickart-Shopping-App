package com.techytown.models;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import com.techytown.enums.OrderStatus;

import lombok.Data;

@Data
@Entity
public class OrderReceipt {
	
	@Id
	private Integer orderId;
	
	private String username;
	
	@ElementCollection
	private Map<Integer,Integer> items = new HashMap<>();
	
	private Double totalMrp;
	
	private Double savings;

	private OrderStatus status;
	
	private LocalDateTime orderingDate;

	private LocalDateTime deliveryDate;

}
