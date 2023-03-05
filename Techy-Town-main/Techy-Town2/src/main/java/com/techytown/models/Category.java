package com.techytown.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer categoryId;
	
	@NotNull(message = "Please Provide Category Name !")
	private String type;
	
	@Size(min=0,max = 255,message = "Description Must be less than 255 Characters !")
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category",cascade = CascadeType.REMOVE)
	private List<Product> products = new ArrayList<>();

	public Category(String type,String description) {
		this.type = type;
		this.description = description;
	}

	
	
	
}
