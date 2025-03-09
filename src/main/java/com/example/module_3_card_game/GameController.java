package com.example.module_3_card_game;

import com.example.module_3_card_game.models.Card;
import com.example.module_3_card_game.models.Face;
import com.example.module_3_card_game.models.Suit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class GameController extends Application {

    // Main sections of the game
    @FXML
    private AnchorPane mainContainer;
    // Contains Hint button, shows hint, and refresh button
    @FXML
    private GridPane topMenu;
    // Holds the 4 cards once they are in play
    @FXML
    private HBox cardContainer;
    // Player will input their solution and use the verify button
    @FXML
    private GridPane bottomMenu;

    // ImageViews for the 4 cards in play
    // Should I implement a custom card view fxml file?
    @FXML
    private ImageView card1;
    @FXML
    private ImageView card2;
    @FXML
    private ImageView card3;
    @FXML
    private ImageView card4;


    private static final String IMAGE_DIR = "/com/example/module_3_card_game/images/cards";

    // Deck of all 52 cards
    private static List<Card> deck = new ArrayList<>();
    // List of the 4 cards in play
    private static List<Card> cardsInPlay;
    // method to verify the player input

    // method to give a hint

    // method to refresh; Should this method call drawCards() and somehow refresh the page?


    //TODO Should i put the helper methods for instantiating the deck in another class?

    // Method to create instance of card
    // Takes the image path
    // TODO figure out how to convert the image path into an image
    private static void createCard(Path filePath) {
        String fileName = filePath.getFileName().toString();
        fileName = fileName.replace(".png", "");
        String[] faceAndSuit = fileName.split("_of_");
        Face face = parseFace(faceAndSuit[0]);
        Suit suit = parseSuit(faceAndSuit[1]);

        // Use classpath resource format for the image path
        String resourcePath = IMAGE_DIR + "/" + fileName + ".png";
        Card card = new Card(face, suit, resourcePath);
        deck.add(card);
    }
    private static Face parseFace(String str) {
        // For face cards
        return switch (str) {
            case "ace" -> Face.ACE;
            case "jack" -> Face.JACK;
            case "queen" -> Face.QUEEN;
            case "king" -> Face.KING;
            case "2" -> Face.TWO;
            case "3" -> Face.THREE;
            case "4" -> Face.FOUR;
            case "5" -> Face.FIVE;
            case "6" -> Face.SIX;
            case "7" -> Face.SEVEN;
            case "8" -> Face.EIGHT;
            case "9" -> Face.NINE;
            case "10" -> Face.TEN;
            default -> throw new IllegalArgumentException("Invalid face: " + str);
        };
    }
    private static Suit parseSuit(String str) {
        return switch (str) {
            case "hearts" -> Suit.HEARTS;
            case "diamonds" -> Suit.DIAMONDS;
            case "clubs" -> Suit.CLUBS;
            case "spades" -> Suit.SPADES;
            default -> null;
        };
    }

    //
    // This will be activated if the game is started or the user chooses to refresh the cards
    public void drawCards() {
        // cardsInPlay will be a sublist of the first 4 elements after the deck has been shuffled.
        Collections.shuffle(deck);
        cardsInPlay = deck.subList(0, 4);

        try {
            // Debug information to see what paths are being used
            for (int i = 0; i < cardsInPlay.size(); i++) {
                System.out.println("Card " + (i+1) + " path: " + cardsInPlay.get(i).getImagePath());
                URL resourceURL = getClass().getResource(cardsInPlay.get(i).getImagePath());
                System.out.println("Resource URL: " + resourceURL);
                if (resourceURL == null) {
                    System.err.println("WARNING: Resource not found for card " + (i+1));
                }
            }

            // Now set the images with better error handling
            setCardImage(card1, cardsInPlay.get(0), 0);
            setCardImage(card2, cardsInPlay.get(1), 1);
            setCardImage(card3, cardsInPlay.get(2), 2);
            setCardImage(card4, cardsInPlay.get(3), 3);

        } catch (Exception e) {
            System.err.println("Exception in drawCards: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void setCardImage(ImageView imageView, Card card, int index) {
        try {
            URL resourceURL = getClass().getResource(card.getImagePath());
            if (resourceURL != null) {
                imageView.setImage(new Image(resourceURL.toExternalForm()));
            } else {
                System.err.println("Failed to load image for card " + (index+1) + ": " + card.getImagePath());
            }
        } catch (Exception e) {
            System.err.println("Error setting image for card " + (index+1) + ": " + e.getMessage());
        }
    }

    public List<Card> getCardsInPlay() {
        return cardsInPlay;
    }


    // start method to initialize the deck and cards called from the application
    /* Deck of 52 Cards should be created and a method to select 4 random cards should be called after by default
     to render the cards in play to the user */
    public void startGame() {
        // Clear any existing cards
        deck.clear();
        
        // Create all cards
        for (Face face : Face.values()) {
            for (Suit suit : Suit.values()) {
                String filename = face.getStr() + "_of_" + suit.getStr() + ".png";
                String resourcePath = IMAGE_DIR + "/" + filename;
                Card card = new Card(face, suit, resourcePath);
                deck.add(card);
            }
        }
        
        // Draw cards in this instance
        drawCards();
    }

    @FXML
    public void initialize() {
        // Called by JavaFX after the FXML elements are loaded
        startGame();
    }

    // In your Application class
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Card Game");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        
        // Controller initialization happens automatically via the initialize() method
    }

    public static void main(String[] args) {
        launch(args);
    }

}