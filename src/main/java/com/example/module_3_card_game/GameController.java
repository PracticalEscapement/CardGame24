package com.example.module_3_card_game;

import com.example.module_3_card_game.models.Card;
import com.example.module_3_card_game.models.Face;
import com.example.module_3_card_game.models.Suit;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class GameController {

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

    private static final String IMAGE_DIR = "src/main/resources/com/example/module_3_card_game/images/cards";

    // Deck of all 52 cards
    private static List<Card> deck = new ArrayList<>();
    // List of the 4 cards in play
    private static List<Card> cardsInPlay;
    // method to verify the player input

    // method to give a hint

    // method to refresh


    //TODO Should i put the helper methods for instantiating the deck in another class?

    // Method to create instance of card
    // Takes the image path
    // TODO figure out how to convert the image path into an image
    private static Card createCard(Path filePath) {
        String fileName = filePath.getFileName().toString();
        fileName = fileName.replace(".png", "");
        // [0]=face [1]=suit
        String[] faceAndSuit = fileName.split("_of_");
        Face face = parseFace(faceAndSuit[0]);
        Suit suit = parseSuit(faceAndSuit[1]);
        Card card = new Card(face, suit, filePath.toString());
        deck.add(card);
        return card;
    }
    private static Face parseFace(String str) {
        // For face cards
        return switch (str) {
            case "ace" -> Face.ACE;
            case "jack" -> Face.JACK;
            case "queen" -> Face.QUEEN;
            case "king" -> Face.KING;
            default -> {
                // For numbered cards
                try {
                    int value = Integer.parseInt(str);
                    yield Face.checkStr(value);
                } catch (NumberFormatException e) {
                    yield null;
                }
            }
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

    // This will be activated if the game is started or the user chooses to refresh the cards
    public static void drawCards() {
        // cardsInPlay will be a sublist of the first 4 elements after the deck has been shuffled.
        Collections.shuffle(deck);
        cardsInPlay = deck.subList(0, 4);
    }

    public List<Card> getCardsInPlay() {
        return cardsInPlay;
    }



    // start method to initialize the deck and cards called from the application
    /* Deck of 52 Cards should be created and a method to select 4 random cards should be called after by default
     to render the cards in play to the user */
    public static void startGame() {
        try {
            // Use the name of the image file to create the card type
            Files.list(Paths.get(IMAGE_DIR)).filter(Files::isRegularFile)
                .forEach(GameController::createCard);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        // Draw 4 cards from the deck to start the game by default
        drawCards();
    }






}