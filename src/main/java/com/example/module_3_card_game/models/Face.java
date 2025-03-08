package com.example.module_3_card_game.models;

public enum Face {
    ACE(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(11, "jack"),
    QUEEN(12, "queen"),
    KING(13, "king");


    private final int value;
    private final String str;

    Face(int value, String str) {
        this.value = value;
        this.str = str;
    }

    // Getters will be used to iterate through all of the card images and instantiate the deck;
    public int getValue() {
        return this.value;
    }
    public String getStr() {
        return this.str;
    }

}
