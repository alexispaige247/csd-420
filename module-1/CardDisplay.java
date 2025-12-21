// Alexis Mitchell
// December 20, 2025
// This program displays 4 random playing cards from a standard deck using JavaFX.

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDisplay extends Application {

    private HBox cardBox;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        cardBox = new HBox(10); // Horizontal box to hold the 4 cards
        cardBox.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Button to refresh the cards
        Button refreshButton = new Button("Refresh Cards");
        refreshButton.setOnAction(e -> displayRandomCards()); // Lambda expression

        VBox root = new VBox(20, cardBox, refreshButton);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Display initial cards
        displayRandomCards();

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Random Card Display");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void displayRandomCards() {
        cardBox.getChildren().clear();

        // Create a list of card numbers 1-52
        List<Integer> cardNumbers = new ArrayList<>();
        for (int i = 1; i <= 52; i++) {
            cardNumbers.add(i);
        }

        // Shuffle and pick first 4 cards
        Collections.shuffle(cardNumbers);
        List<Integer> selectedCards = cardNumbers.subList(0, 4);

        for (int cardNum : selectedCards) {
            try {
                // Load image from "cards" folder
                Image cardImage = new Image(new FileInputStream("cards/" + cardNum + ".png"));
                ImageView imageView = new ImageView(cardImage);
                imageView.setFitWidth(100); // Set card width
                imageView.setPreserveRatio(true);
                cardBox.getChildren().add(imageView);
            } catch (FileNotFoundException ex) {
                System.out.println("Card image not found: " + cardNum + ".png");
            }
        }
    }
}
