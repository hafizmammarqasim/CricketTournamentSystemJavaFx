package com.example.hblpsl;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;

public class PointsTableDesign {
    private static final String TEAM_FILE = "Teams.txt";

    public Scene createPointsTableScene(Stage stage, Scene scene) {


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

                TableView<String[]> tableView = new TableView<>();

                TableColumn<String[], String> teamNameColumn = new TableColumn<>("Team Name");
                teamNameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[0]));

                TableColumn<String[], String> matchesPlayedColumn = new TableColumn<>("Matches Played");
                matchesPlayedColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[1]));

                TableColumn<String[], String> matchesWonColumn = new TableColumn<>("Matches Won");
                matchesWonColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[2]));

                TableColumn<String[], String> matchesLostColumn = new TableColumn<>("Matches Lost");
                matchesLostColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[3]));

                TableColumn<String[], String> pointsColumn = new TableColumn<>("Points");
                pointsColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()[4]));

                tableView.getColumns().addAll(teamNameColumn, matchesPlayedColumn, matchesWonColumn, matchesLostColumn, pointsColumn);

                tableView.setPrefWidth(600);

                try {
                    BufferedReader reader = new BufferedReader(new FileReader(TEAM_FILE));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] array = line.split(",");
                        String teamName = array[0];
                        String matchesPlayed = array[array.length - 4];
                        String matchesWon = array[array.length - 3];
                        String matchesLost = array[array.length - 2];
                        String points = array[array.length - 1];

                        tableView.getItems().add(new String[]{teamName, matchesPlayed, matchesWon, matchesLost, points});
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                VBox vBox = new VBox(20);
                vBox.getChildren().addAll(backButton,tableView);

                BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(227, 233, 191), null, null);
                vBox.setBackground(new Background(backgroundFill));

                return new Scene(vBox, 1000, 800);
            }
        }

