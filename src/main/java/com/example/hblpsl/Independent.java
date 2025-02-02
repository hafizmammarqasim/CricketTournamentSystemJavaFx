package com.example.hblpsl;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Independent extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Team Overview Section
        Text teamOverview = new Text("Team Overview\nName: Super Strikers\nCoach: Alex Smith\nFounded: 1995");
        teamOverview.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        VBox overviewBox = new VBox(10, teamOverview);
        overviewBox.setPadding(new Insets(10));
        overviewBox.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-padding: 10;");

        // Player Roster Section
        Text playerRoster = new Text(
                "Player Roster\n1. John Doe (Captain)\n2. Jane Doe\n3. Max Power\n4. Sarah Lee\n5. Liam Strong\n6. Emma Brave\n7. Noah Swift");
        playerRoster.setStyle("-fx-font-size: 14px;");
        VBox rosterBox = new VBox(10, playerRoster);
        rosterBox.setPadding(new Insets(10));
        rosterBox.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-padding: 10;");

        // Team Stats Section with Charts
        PieChart teamStatsChart = new PieChart();
        teamStatsChart.getData().addAll(
                new PieChart.Data("Wins", 20),
                new PieChart.Data("Losses", 5),
                new PieChart.Data("Draws", 3)
        );
        teamStatsChart.setTitle("Team Performance");
        VBox statsBox = new VBox(10, new Text("Team Stats"), teamStatsChart);
        statsBox.setPadding(new Insets(10));
        statsBox.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-padding: 10;");

        // Combine all sections in a VBox
        VBox content = new VBox(20, overviewBox, rosterBox, statsBox);
        content.setPadding(new Insets(10));

        // Add the VBox to a ScrollPane
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);

        // Scene Setup
        Scene scene = new Scene(scrollPane, 400, 600);
        primaryStage.setTitle("Scrollable Team Card");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
