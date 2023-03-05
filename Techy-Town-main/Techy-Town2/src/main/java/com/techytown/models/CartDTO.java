package com.techytown.models;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class CartDTO {
	
	@NotNull(message = "Enter Valid Product Id !")
	private Integer productId;
	
	@Range(min = 1,max = 10,message = "Enter Valid Quantity Of Products")
	private Integer quantity;

}
