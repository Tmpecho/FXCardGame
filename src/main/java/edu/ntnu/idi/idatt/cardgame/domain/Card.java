package edu.ntnu.idi.idatt.cardgame.domain;

public record Card(Suit suit, Rank rank) {
	public enum Suit {
		HEARTS, DIAMONDS, CLUBS, SPADES
	}

	public enum Rank {
		ACE("A"),
		TWO("2"),
		THREE("3"),
		FOUR("4"),
		FIVE("5"),
		SIX("6"),
		SEVEN("7"),
		EIGHT("8"),
		NINE("9"),
		TEN("10"),
		JACK("J"),
		QUEEN("Q"),
		KING("K");

		private final String display;

		Rank(String display) {
			this.display = display;
		}

		public String getDisplay() {
			return display;
		}
	}

	public Card {
		if (suit == null || rank == null) {
			throw new IllegalArgumentException("Suit and rank cannot be null.");
		}
	}

	@Override
	public String toString() {
		return rank.getDisplay() + " of " + suit;
	}
}