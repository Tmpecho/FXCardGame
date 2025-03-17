package edu.ntnu.idi.idatt.cardgame.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class DeckOfCards {
	private List<Card> cards;

	public DeckOfCards() {
		resetDeck();
	}

	public void resetDeck() {
		cards = Arrays.stream(Card.Suit.values())
				.flatMap(suit -> Arrays.stream(Card.Rank.values())
						.map(rank -> new Card(suit, rank)))
				.collect(Collectors.toList());
		shuffle();
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public int getSize() {
		return cards.size();
	}

	public List<Card> getNCards(int n) {
		List<Card> selectedCards = new ArrayList<>(cards.subList(0, n));
		cards.subList(0, n).clear();
		return selectedCards;
	}
}