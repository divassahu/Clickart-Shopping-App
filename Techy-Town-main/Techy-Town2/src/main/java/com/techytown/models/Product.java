package com.techytown.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	
	@Column(unique = true,nullable = false)
	private String name;
	
	@Size(min=0,max=255,message = "Describe your product in 255 characters !")
	private String description;
	
	@NotNull(message = "Please Enter Max Retail Price.")
	private Double mrp;
	
	@NotNull(message = "Please Enter Discounted Price.")
	private Double discountPrice;
	
	@NotNull(message = "Provide IMG url !")
	private String img;
	
	@JsonIgnore
	@ManyToOne(targetEntity = Category.class)
	private Category category;

	public Product(String name,String description,Double mrp,Double discountPrice,String img) {
		this.name = name;
		this.description = description;
		this.mrp = mrp;
		this.discountPrice = discountPrice;
		this.img = img;
	}
	
	
	
	
}
