package com.techytown.models;

import lombok.Data;

@Data
public class OrderDTO {

	private Cart items;  
	private Integer cardId;
}
