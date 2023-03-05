package com.techytown.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techytown.models.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
	
}
