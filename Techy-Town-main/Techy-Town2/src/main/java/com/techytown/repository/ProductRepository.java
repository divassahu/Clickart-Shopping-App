package com.techytown.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techytown.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
