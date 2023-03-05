package com.techytown.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techytown.models.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

}
