package com.techytown.service;

import com.techytown.models.Card;

public interface CardService {

	public Card addCard(Card card,String username);
	
//	public Card editCard(C)
	
	public Card removeCard(Integer cardId,String username);
	
}
