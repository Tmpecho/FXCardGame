package edu.ntnu.idi.idatt.cardgame.controller;

import edu.ntnu.idi.idatt.cardgame.domain.Card;
import edu.ntnu.idi.idatt.cardgame.domain.Card.Suit;
import edu.ntnu.idi.idatt.cardgame.domain.Card.Rank;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class CardDeckControllerTest {
	static CardDeckController controller;

	@BeforeAll
	public static void initJFX() throws Exception {
		controller = new CardDeckController();

		Thread t = new Thread("JavaFX Init Thread") {
			public void run() {
				Platform.startup(() -> {});
			}
		};
		t.setDaemon(true);
		t.start();
		Thread.sleep(500);
	}

	@Test
	public void testDealHandSize() {
		FlowPane container = new FlowPane();
		Label resultLabel = new Label();
		controller.handleDeal(container, resultLabel);
		// Verify that exactly HAND_SIZE cards have been added.
		assertEquals(CardDeckController.HAND_SIZE, container.getChildren().size());
	}

	@Test
	public void testResultLabelTextFlush() {
		Label resultLabel = new Label();
		List<Card> flushHand = Arrays.asList(
			new Card(Suit.HEARTS, Rank.ACE),
			new Card(Suit.HEARTS, Rank.TWO),
			new Card(Suit.HEARTS, Rank.THREE),
			new Card(Suit.HEARTS, Rank.FOUR),
			new Card(Suit.HEARTS, Rank.FIVE)
		);
		boolean result = controller.isFlush(flushHand);
		CardDeckController.setResultLabel(resultLabel, result);
		assertEquals("Flush!", resultLabel.getText());
	}

	@Test
	public void testResultLabelTextNotFlush() {
		Label resultLabel = new Label();
		List<Card> nonFlushHand = Arrays.asList(
			new Card(Suit.HEARTS, Rank.ACE),
			new Card(Suit.DIAMONDS, Rank.TWO),
			new Card(Suit.HEARTS, Rank.THREE),
			new Card(Suit.HEARTS, Rank.FOUR),
			new Card(Suit.HEARTS, Rank.FIVE)
		);
		boolean result = controller.isFlush(nonFlushHand);
		CardDeckController.setResultLabel(resultLabel, result);
		assertEquals("Not a flush.", resultLabel.getText());
	}

	@Test
	public void testIsFlush() {
		List<Card> flushHand = Arrays.asList(
				new Card(Suit.HEARTS, Rank.ACE),
				new Card(Suit.HEARTS, Rank.TWO),
				new Card(Suit.HEARTS, Rank.THREE),
				new Card(Suit.HEARTS, Rank.FOUR),
				new Card(Suit.HEARTS, Rank.FIVE)
		);
		assertTrue(controller.isFlush(flushHand));
	}

	@Test
	public void testIsNotFlush() {
		List<Card> nonFlushHand = Arrays.asList(
				new Card(Suit.HEARTS, Rank.ACE),
				new Card(Suit.DIAMONDS, Rank.TWO),
				new Card(Suit.HEARTS, Rank.THREE),
				new Card(Suit.HEARTS, Rank.FOUR),
				new Card(Suit.HEARTS, Rank.FIVE)
		);
		assertFalse(controller.isFlush(nonFlushHand));
	}

	@Test
	public void testIsFlushWithNullOrEmptyHand() {
		Exception ex1 = assertThrows(IllegalArgumentException.class, () -> controller.isFlush(null));
		assertEquals("Hand cannot be null or empty.", ex1.getMessage());

		Exception ex2 = assertThrows(IllegalArgumentException.class, () -> controller.isFlush(List.of()));
		assertEquals("Hand cannot be null or empty.", ex2.getMessage());
	}

	@Test
	public void testCalculateSum() {
		Label sumLabel = new Label();
		List<Card> hand1 = Arrays.asList(
				new Card(Suit.HEARTS, Rank.ACE),
				new Card(Suit.HEARTS, Rank.TWO),
				new Card(Suit.HEARTS, Rank.THREE),
				new Card(Suit.HEARTS, Rank.FOUR),
				new Card(Suit.HEARTS, Rank.FIVE)
		);
		controller.setLastHand(hand1);
		controller.calculateSum(sumLabel);
		assertEquals("Sum: 15", sumLabel.getText());

		List<Card> hand2 = Arrays.asList(
				new Card(Suit.HEARTS, Rank.NINE),
				new Card(Suit.HEARTS, Rank.TEN),
				new Card(Suit.HEARTS, Rank.JACK),
				new Card(Suit.HEARTS, Rank.QUEEN),
				new Card(Suit.HEARTS, Rank.KING)
		);
		controller.setLastHand(hand2);
		controller.calculateSum(sumLabel);
		assertEquals("Sum: 55", sumLabel.getText());
	}
}