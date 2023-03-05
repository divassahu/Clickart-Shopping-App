package com.techytown.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techytown.models.Card;
import com.techytown.models.User;
import com.techytown.repository.CardRepository;
import com.techytown.repository.UserRepository;

@Service
public class CardServiceImpl implements CardService {
	
	@Autowired
	private CardRepository cardRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public Card addCard(Card card, String username) {
		Optional<User> userOpt = userRepo.findByUsername(username);
		if(userOpt.isPresent()) {
			Card saved = cardRepo.save(card);
			saved.setUser(userOpt.get());
			userOpt.get().getCards().add(saved);
			
			return cardRepo.saveAndFlush(saved);
			
			
		}else {
			throw new UsernameNotFoundException(username+" not Found !");
		}
	}

	@Override
	public Card removeCard(Integer cardID, String username) {
		Optional<User> userOpt = userRepo.findByUsername(username);
		if(userOpt.isPresent()) {
			
			Optional<Card> deleting  = cardRepo.findById(cardID);
			cardRepo.delete(deleting.get());
			return deleting.get();
			
			
		}else {
			throw new UsernameNotFoundException(username+" not Found !");
		}
	}
	



}
