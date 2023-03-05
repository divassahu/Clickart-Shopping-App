package com.techytown.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techytown.models.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer>{

}
