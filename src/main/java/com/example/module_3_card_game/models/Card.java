package com.example.module_3_card_game.models;

import javafx.fxml.FXML;
import javafx.scene.image.Image;

public class Card {
    @FXML
    private Face face;
    @FXML
    private Suit suit;

    @FXML
    private Image image;

    public Card(Face face, Suit suit, Image image) {
        this.face = face;
        this.suit = suit;
        this.image = image;
    }

    public int getValue() {
        return face.getValue();
    }

    public String getSuit() {
        return suit.getStr();
    }
}
