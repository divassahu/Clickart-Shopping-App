package com.techytown.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;



import lombok.Data;

@Entity
@Data
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int paymentId;
	
	@NotNull(message = "Please Enter Amount To Pay.")
	private Double amt;

	@OneToOne(cascade = CascadeType.ALL)
	private Card card;
}
