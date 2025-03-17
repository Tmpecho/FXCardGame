package edu.ntnu.idi.idatt.cardgame.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DeckOfCards {
	private List<Card> deck;

	public DeckOfCards() {
		resetDeck();
	}

	private void resetDeck() {
		deck = Arrays.stream(Card.Suit.values())
				.flatMap(suit -> Arrays.stream(Card.Rank.values())
						.map(rank -> new Card(suit, rank)))
				.collect(Collectors.toList());
		shuffle();
	}

	public void shuffle() {
		Collections.shuffle(deck);
	}

	public List<Card> dealHand(int count) {
		if (count <= 0) {
			throw new IllegalArgumentException("Count must be positive.");
		}
		if (deck.size() < count) {
			resetDeck();
		}
		List<Card> hand = new ArrayList<>(deck.subList(0, count));
		deck.subList(0, count).clear();
		return hand;
	}

	public static boolean isFlush(List<Card> hand) {
		if (hand == null || hand.isEmpty()) {
			throw new IllegalArgumentException("Hand cannot be null or empty.");
		}
		Card.Suit suit = hand.get(0).suit();
		return hand.stream().allMatch(card -> card.suit() == suit);
	}
}