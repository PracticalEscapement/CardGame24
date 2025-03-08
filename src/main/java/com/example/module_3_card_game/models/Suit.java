package com.example.module_3_card_game.models;

public enum Suit {
    HEARTS("hearts"),
    DIAMONDS("diamonds"),
    CLUBS("clubs"),
    SPADES("spades");

    private final String str;

    Suit(String str) {
        this.str = str;
    }

    public String getStr() {
        return this.str;
    }
}
