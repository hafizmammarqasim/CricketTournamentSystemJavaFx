package com.example.hblpsl;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MatchDesign {

    public Scene createMatchScene (Stage stage, Scene scene){

        VBox vBox = new VBox(10);
        Label teamLabel = new Label("Welcome to Player Page!");
        teamLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: blue;");

        Button backButton = new Button("Back to Main");

        backButton.setOnAction(e -> {

            try {
                Menu menu = new Menu();
                Scene menuScene = new Scene(new StackPane(), 1000, 800);
                Stage currentStage = (Stage) backButton.getScene().getWindow();
                currentStage.setTitle("Menu Page of PSL...!!!");
                currentStage.setScene(menu.start(currentStage, menuScene));
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        });

        vBox.getChildren().addAll(teamLabel,backButton);
        return new Scene(vBox,1000,800);

    }
}
