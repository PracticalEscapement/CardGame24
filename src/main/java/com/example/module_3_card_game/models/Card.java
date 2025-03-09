package com.example.module_3_card_game.models;

import javafx.fxml.FXML;
import javafx.scene.image.Image;

public class Card {
    @FXML
    private Face face;
    @FXML
    private Suit suit;

    @FXML
    private String imagePath;

    // Constructor used to make the original deck of cards. I don't want the original deck to be mutated.
    public Card(Face face, Suit suit, String imagePath) {
        this.face = face;
        this.suit = suit;
        this.imagePath = imagePath;
    }

    public int getValue() {
        return face.getValue();
    }

    public String getSuit() {
        return suit.getStr();
    }
}
