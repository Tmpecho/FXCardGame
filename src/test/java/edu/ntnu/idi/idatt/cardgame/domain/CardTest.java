package edu.ntnu.idi.idatt.cardgame.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
	@Test
	public void testNullSuitThrowsException() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Card(null, Card.Rank.ACE);
		});
		assertEquals("Suit and rank cannot be null.", exception.getMessage());
	}

	@Test
	public void testNullRankThrowsException() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Card(Card.Suit.SPADES, null);
		});
		assertEquals("Suit and rank cannot be null.", exception.getMessage());
	}
}