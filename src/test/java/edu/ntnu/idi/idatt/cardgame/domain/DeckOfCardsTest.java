package edu.ntnu.idi.idatt.cardgame.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;

public class DeckOfCardsTest {
	private DeckOfCards deck;

	@BeforeEach
	public void setUp() {
		deck = new DeckOfCards();
	}

	@Test
	public void testResetDeckSize() {
		deck.resetDeck();
		// A full deck should contain 52 cards
		assertEquals(52, deck.getSize());
	}

	@Test
	public void testGetNCardsReducesDeckSize() {
		deck.resetDeck();
		List<Card> hand = deck.getNCards(5);
		assertEquals(5, hand.size());
		assertEquals(47, deck.getSize());
	}

	@Test
	public void testDeckContainsUniqueCards() {
		deck.resetDeck();
		List<Card> allCards = deck.getNCards(52);
		assertEquals(52, allCards.size());
		// HashSet to make sure all cards are unique
		HashSet<Card> uniqueCards = new HashSet<>(allCards);
		assertEquals(52, uniqueCards.size());
	}

	@Test
	public void testGetNCardsThrowsExceptionWhenInsufficientCards() {
		deck.resetDeck();
		// Remove 48 cards. Only 4 should remain.
		deck.getNCards(48);
		assertEquals(4, deck.getSize());

		// Attempting to get 5 cards from remaining 4 should throw an exception.
		assertThrows(IndexOutOfBoundsException.class, () -> deck.getNCards(5));
	}
}