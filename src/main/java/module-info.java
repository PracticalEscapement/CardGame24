module com.example.module_3_card_game {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.module_3_card_game to javafx.fxml;
    exports com.example.module_3_card_game;
}