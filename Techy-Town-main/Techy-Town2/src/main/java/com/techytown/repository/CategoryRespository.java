package com.techytown.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techytown.models.Category;

@Repository
public interface CategoryRespository extends JpaRepository<Category, Integer> {

}
