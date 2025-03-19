package edu.ntnu.idi.idatt.cardgame.controller;

import edu.ntnu.idi.idatt.cardgame.domain.Card;
import edu.ntnu.idi.idatt.cardgame.domain.DeckOfCards;
import edu.ntnu.idi.idatt.cardgame.view.CardView;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CardDeckController {
	public static final int HAND_SIZE = 5;
	public static final int CARD_FADE_TIME = 50;
	private final DeckOfCards deck;

	public CardDeckController() {
		this.deck = new DeckOfCards();
	}

	public void handleDeal(FlowPane cardContainer, Label resultLabel) {
		List<Card> hand = dealHand(HAND_SIZE);
		cardContainer.getChildren().clear();
		IntStream.range(0, hand.size()).forEach(idx -> {
			CardView cardView = new CardView(hand.get(idx));
			cardContainer.getChildren().add(cardView);
			cardView.playFadeIn(idx * CARD_FADE_TIME);
		});
		boolean flush = isFlush(hand);
		resultLabel.setText(flush ? "Flush!" : "Not a flush.");
	}

	public List<Card> dealHand(int count) {
		if (count <= 0) {
			throw new IllegalArgumentException("Count must be positive.");
		}
		if (deck.getSize() < count) {
			deck.resetDeck();
		}
		return new ArrayList<>(deck.getNCards(count));
	}

	public boolean isFlush(List<Card> hand) {
		if (hand == null || hand.isEmpty()) {
			throw new IllegalArgumentException("Hand cannot be null or empty.");
		}
		Card.Suit suit = hand.get(0).suit();
		return hand.stream().allMatch(card -> card.suit() == suit);
	}
}
