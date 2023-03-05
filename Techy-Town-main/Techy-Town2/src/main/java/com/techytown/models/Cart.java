package com.techytown.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartId;
	
	private double mrpExpenditure=0.0;	
	
	private double discountExpenditure=0.0;
	
//	@OneToMany
//	private List<Product> products = new ArrayList<>();
	
	@ElementCollection
	@JoinTable(name = "cart_Products")
	Map<Integer, Integer> prod_Qty = new HashMap<>();
	
	@JsonIgnore
	@OneToOne(mappedBy = "cart",targetEntity = User.class)
	private User user;
	
	
}
