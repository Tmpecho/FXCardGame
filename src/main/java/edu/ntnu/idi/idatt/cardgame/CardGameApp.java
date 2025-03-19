package edu.ntnu.idi.idatt.cardgame;

import edu.ntnu.idi.idatt.cardgame.controller.CardDeckController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CardGameApp extends Application {
	private final CardDeckController cardDeckController = new CardDeckController();

	@Override
	public void start(Stage stage) {
		Label header = new Label("Card Game");
		header.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");

		Button dealButton = new Button("Deal Cards");
		dealButton.setStyle("-fx-font-size: 16; -fx-padding: 10 20 10 20;");

		Button checkHandButton = new Button("Check Hand");
		checkHandButton.setStyle("-fx-font-size: 16; -fx-padding: 10 20 10 20;");

		FlowPane cardContainer = new FlowPane();
		cardContainer.setHgap(10);
		cardContainer.setVgap(10);
		cardContainer.setAlignment(Pos.CENTER);

		Label resultLabel = new Label();
		resultLabel.setStyle("-fx-font-size: 18;");

		Label sumLabel = new Label();
		sumLabel.setText("Sum: ");
		sumLabel.setStyle("-fx-font-size: 18;");

		dealButton.setOnAction(e -> cardDeckController.handleDeal(cardContainer, resultLabel));
		checkHandButton.setOnAction(e -> cardDeckController.calculateSum(sumLabel));

		BorderPane root = new BorderPane();
		root.setTop(header);
		BorderPane.setAlignment(header, Pos.CENTER);
		root.setCenter(cardContainer);

		VBox bottomBox = new VBox(20, resultLabel, sumLabel, dealButton, checkHandButton);
		bottomBox.setAlignment(Pos.CENTER);
		bottomBox.setPadding(new Insets(10));
		root.setBottom(bottomBox);

		Scene scene = new Scene(root, 800, 600);
		stage.setScene(scene);
		stage.setTitle("Card Game");
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}