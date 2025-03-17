package edu.ntnu.idi.idatt.cardgame.view;

import edu.ntnu.idi.idatt.cardgame.domain.Card;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.util.Objects;

public class CardView extends BorderPane {
	public CardView(Card card) {
		this.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-radius: 10; -fx-background-radius: 10; -fx-padding: 10;");
		this.setMinWidth(100);
		this.setMinHeight(140);

		String suitImageFile = "/images/" + card.suit().toString().toLowerCase() + ".png";
		Image suitImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(suitImageFile)));

		Label topRankLabel = new Label(card.rank().getDisplay());
		topRankLabel.setStyle("-fx-font-size: 12;");
		ImageView topSuitImage = new ImageView(suitImage);
		topSuitImage.setFitWidth(12);
		topSuitImage.setPreserveRatio(true);
		HBox topBox = new HBox(topRankLabel, topSuitImage);
		topBox.setSpacing(5);
		topBox.setAlignment(Pos.TOP_LEFT);
		BorderPane.setMargin(topBox, new Insets(5, 0, 0, 5));

		// Center region: Large suit image
		ImageView centerImageView = new ImageView(suitImage);
		centerImageView.setFitWidth(40);
		centerImageView.setPreserveRatio(true);

		ImageView bottomSuitImage = new ImageView(suitImage);
		bottomSuitImage.setFitWidth(12);
		bottomSuitImage.setPreserveRatio(true);
		Label bottomRankLabel = new Label(card.rank().getDisplay());
		bottomRankLabel.setStyle("-fx-font-size: 12;");
		HBox bottomBox = new HBox(bottomSuitImage, bottomRankLabel);
		bottomBox.setSpacing(5);
		bottomBox.setAlignment(Pos.BOTTOM_RIGHT);
		BorderPane.setMargin(bottomBox, new Insets(0, 5, 5, 0));

		this.setTop(topBox);
		this.setCenter(centerImageView);
		this.setBottom(bottomBox);

		this.setOpacity(0);
	}

	public void playFadeIn(double delayMillis) {
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(250), this);
		fadeTransition.setFromValue(0);
		fadeTransition.setToValue(1);
		fadeTransition.setDelay(Duration.millis(delayMillis));
		fadeTransition.play();
	}
}