package com.example.hblpsl;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FontPreviewApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create buttons with different fonts
        Button buttonRoboto = new Button("Roboto");
        buttonRoboto.setFont(Font.font("Roboto", FontWeight.NORMAL, FontPosture.REGULAR, 20));

        Button buttonOswald = new Button("Oswald");
        buttonOswald.setFont(Font.font("Oswald", FontWeight.NORMAL, FontPosture.REGULAR, 20));

        Button buttonAnton = new Button("Anton");
        buttonAnton.setFont(Font.font("Anton", FontWeight.NORMAL, FontPosture.REGULAR, 20));

        Button buttonBangers = new Button("Bangers");
        buttonBangers.setFont(Font.font("Bangers", FontWeight.NORMAL, FontPosture.REGULAR, 20));

        Button buttonPoppins = new Button("Poppins");
        buttonPoppins.setFont(Font.font("Poppins", FontWeight.NORMAL, FontPosture.REGULAR, 20));

        Button buttonLobster = new Button("Lobster");
        buttonLobster.setFont(Font.font("Lobster", FontWeight.NORMAL, FontPosture.REGULAR, 20));

        // Arrange the buttons in a vertical box
        VBox vbox = new VBox(10, buttonRoboto, buttonOswald, buttonAnton, buttonBangers, buttonPoppins, buttonLobster);

        // Set the scene
        Scene scene = new Scene(vbox, 300, 400);
        primaryStage.setTitle("Font Preview");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

