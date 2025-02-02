package com.example.hblpsl;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TeamsDesign {
//
//    public static void main(String[] args) {
//        launch(args);
//    }

    private List<Team> teamList = new ArrayList<>();
    private static final String TEAM_FILE = "Teams.txt";
    VBox teamBox = new VBox(10);

    Stage main;
    public Scene createTeamScene(Stage stage, Scene scene) {

        main = stage;
        creatNewButton();
        Tournament tournament = new Tournament();
        tournament.readTeamsFromFile();

        Image teamImage = new Image(getClass().getResource("/Images/background2.jpg").toExternalForm());
        ImageView imageView = new ImageView(teamImage);
        imageView.fitHeightProperty().bind(stage.heightProperty());
        imageView.fitWidthProperty().bind(stage.widthProperty());
        imageView.setMouseTransparent(true);

        Button backButton = new Button("Back to Main");
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14));
        backButton.setStyle(" -fx-background-radius: 25px;  -fx-background-color: BLUE;  -fx-text-fill: white;");
        backButton.setMinWidth(140);
        backButton.setMinHeight(35);
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


        Button removeTeam = new Button("Delete Team");
        removeTeam.setFont(Font.font("Oswald", FontWeight.BOLD, FontPosture.REGULAR, 18));
        removeTeam.setStyle(" -fx-background-radius: 25px;  -fx-background-color: white;  -fx-text-fill: BLUE;");
        removeTeam.setMinWidth(190);
        removeTeam.setMinHeight(45);

        removeTeam.setOnAction(e -> {
            Stage currrentStage = (Stage) removeTeam.getScene().getWindow();
            currrentStage.setTitle("Delete Team...!!!");
            Scene addNewTeamScene = new Scene(new StackPane(), 1000, 800);
            currrentStage.setScene(deleteTeamFromTournament(currrentStage, addNewTeamScene));
        });

        Button addNewTeam = new Button("Add New Team");
        addNewTeam.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 18));
        addNewTeam.setStyle(" -fx-background-radius: 25px;  -fx-background-color: BLUE;  -fx-text-fill: white;");
        addNewTeam.setMinWidth(190);
        addNewTeam.setMinHeight(45);


        addNewTeam.setOnAction(e -> {
            try {
                Stage currrentStage = (Stage) addNewTeam.getScene().getWindow();
                currrentStage.setTitle("Add New Team...!!!");
                Scene addNewTeamScene = new Scene(new StackPane(), 1000, 800);
                currrentStage.setScene(addNewTeam(currrentStage, addNewTeamScene));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        HBox topHBox = new HBox(10);
        HBox bottomHBox = new HBox(10);
        topHBox.getChildren().addAll(backButton);
        bottomHBox.getChildren().addAll(addNewTeam,removeTeam);
        BorderPane.setMargin(topHBox, new Insets(5, 0, 0, 10));
        AnchorPane bottomAnchorPane = new AnchorPane();
        bottomAnchorPane.getChildren().add(bottomHBox);
        bottomAnchorPane.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        AnchorPane.setBottomAnchor(bottomHBox,10.0);
        AnchorPane.setRightAnchor(bottomHBox,10.0);
        teamBox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(teamBox);
        borderPane.setTop(topHBox);
        BorderPane.setAlignment(topHBox, Pos.TOP_LEFT);

        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(borderPane);
        mainPane.setBottom(bottomAnchorPane);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView,mainPane);
        stackPane.setPickOnBounds(true);
        return new Scene(stackPane, 1000, 800);
    }




    public Scene addNewTeam(Stage stage, Scene scene) {

        Label label1 = new Label("Team Name: ");
        label1.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        TextField textField = new TextField();
        textField.setStyle("-fx-min-height: 30px; -fx-min-width: 160px; -fx-background-radius: 10px");
        HBox hBox = new HBox(36);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(label1, textField);

        Label label0 = new Label("Team Captain:");
        label0.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        TextField textField0 = new TextField();
        textField0.setStyle("-fx-min-height: 30px; -fx-min-width: 160px; -fx-background-radius: 10px");
        HBox hBox0 = new HBox(30);
        hBox0.setAlignment(Pos.CENTER);
        hBox0.getChildren().addAll(label0, textField0);

        Label label2 = new Label("Head Coach: ");
        label2.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        TextField textField1 = new TextField();
        textField1.setStyle("-fx-min-height: 30px; -fx-min-width: 160px; -fx-background-radius: 10px");
        HBox hBox1 = new HBox(36);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(label2, textField1);


        Label label3 = new Label("Batting Coach: ");
        label3.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        TextField textField2 = new TextField();
        textField2.setStyle("-fx-min-height: 30px; -fx-min-width: 160px; -fx-background-radius: 10px");
        HBox hBox2 = new HBox(21);
        hBox2.setAlignment(Pos.CENTER);
        hBox2.getChildren().addAll(label3, textField2);

        Label label4 = new Label("Bowling Coach: ");
        label4.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        TextField textField3 = new TextField();
        textField3.setStyle("-fx-min-height: 30px; -fx-min-width: 160px; -fx-background-radius: 10px");
        HBox hBox3 = new HBox(14);
        hBox3.setAlignment(Pos.CENTER);
        hBox3.getChildren().addAll(label4, textField3);

        Label label5 = new Label("Team Owner: ");
        label5.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        TextField textField4 = new TextField();
        textField4.setStyle("-fx-min-height: 30px; -fx-min-width: 160px; -fx-background-radius: 10px");
        HBox hBox4 = new HBox(30);
        hBox4.setAlignment(Pos.CENTER);
        hBox4.getChildren().addAll(label5, textField4);

        Label label6 = new Label("Home City: ");
        label6.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        ComboBox<String> cityBox = new ComboBox<>();
        cityBox.getItems().addAll("Lahore", "Multan", "Karachi", "Peshawar", "Quetta", "Islamabad");
        cityBox.setPromptText("Select City");
        cityBox.setStyle("-fx-min-height: 30px; -fx-min-width: 160px; -fx-background-radius: 10px");
        HBox hBox5 = new HBox(47);
        hBox5.setAlignment(Pos.CENTER);
        hBox5.getChildren().addAll(label6, cityBox);


        Button saveButton = new Button("Save");
        saveButton.setPrefHeight(20);
        saveButton.setPrefWidth(100);
        saveButton.setStyle("-fx-text-fill: white; -fx-background-color: green; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 25px;");

        Button backButton = new Button("Back");
        backButton.setPrefHeight(20);
        backButton.setPrefWidth(100);
        backButton.setStyle("-fx-text-fill: white; -fx-background-color: green; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 25px;");

        saveButton.setOnAction(e -> {
            String teamName = textField.getText();
            String captainName = textField0.getText();
            String headCoach = textField1.getText();
            String battingCoach = textField2.getText();
            String bowlingCoach = textField3.getText();
            String teamOwner = textField4.getText();
            String teamCity = cityBox.getValue();
            Team tempTeam = new Team(teamName, captainName, headCoach, bowlingCoach, battingCoach, teamOwner, teamCity, 0, 0, 0, 0);
            teamList.add(tempTeam);

            File file = new File("Teams.txt");

            if (file.exists()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file,true))) {
                    writer.write(tempTeam.addToFile());
                    writer.newLine();
                } catch (IOException e1) {
                    System.out.println(e1.getMessage());
                }
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Team added successfully...!!!");
            alert.show();

            Tournament tournament = new Tournament();
            tournament.readTeamsFromFile();

            textField0.clear(); textField.clear();   textField1.clear();   textField2.clear();   textField3.clear();   textField4.clear();   cityBox.setValue(null);
        });

        backButton.setOnAction(e -> {
            Stage currrentStage = (Stage) backButton.getScene().getWindow();
            currrentStage.setTitle("Add New Team...!!!");
            Scene addNewTeamScene = new Scene(new StackPane(), 1000, 800);
            currrentStage.setScene(createTeamScene(currrentStage, addNewTeamScene));
        });

        BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(215, 223, 163), null, null);
        BackgroundFill backgroundFill1 = new BackgroundFill(Color.rgb(227, 233, 191), null, null);

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefHeight(500);
        vBox.setMaxWidth(400);
        vBox.setStyle("-fx-border-color: black; -fx-order-width: 1px;");
        vBox.setBackground(new Background(backgroundFill1));
        vBox.getChildren().addAll(hBox, hBox0, hBox1, hBox2, hBox3, hBox4, hBox5, saveButton, backButton);

        Label label = new Label("Enter Team Details");
        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font("Oswald", FontWeight.BOLD, FontPosture.REGULAR, 18));
        label.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: black ; -fx-border-color: black; -fx-border-width: 1px;");
        label.setBackground(new Background(backgroundFill1));
        label.setPrefHeight(100);
        label.setPrefWidth(400);

        VBox vBox1 = new VBox();
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setBackground(new Background(backgroundFill));
        vBox1.getChildren().addAll(label, vBox);

        return new Scene(vBox1, 1000, 800);


    }





    private void creatNewButton() {
        teamBox.getChildren().clear();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(30);
        gridPane.setVgap(30);
        gridPane.setAlignment(Pos.CENTER);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(TEAM_FILE));
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String[] team = line.split(",");
                String teamName = team[0];

                Button newTeamButton = new Button(teamName);
                newTeamButton.setFont(Font.font("Roboto", FontWeight.BOLD, FontPosture.REGULAR, 19));
                newTeamButton.setMinWidth(220);
                newTeamButton.setMinHeight(50);
                newTeamButton.setStyle("-fx-background-radius: 25px; -fx-background-color: red; -fx-text-fill: white;");

                newTeamButton.setOnAction(e -> {
                    try {
                        TeamDetails teamDetails = new TeamDetails();
                        Stage currentStage = (Stage) newTeamButton.getScene().getWindow();
                        currentStage.setTitle("Team " + teamName + "...!!!");
                        Scene teamDetailScene = new Scene(new StackPane(), 1000, 800);
                        currentStage.setScene(teamDetails.createTeamDetailsScene(currentStage, teamDetailScene, teamName));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });

                int row = index / 3;
                int col = index % 3;
                gridPane.add(newTeamButton, col, row);
                index++;

                //teamBox.getChildren().add(newTeamButton);
            }
            reader.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        teamBox.getChildren().add(gridPane);
    }





    public Scene deleteTeamFromTournament(Stage stage, Scene scene) {
        Label label = new Label("Enter the Team Name you want to Delete...!!!");
        label.setPrefWidth(500);
        label.setPrefHeight(100);
        label.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: green; -fx-border-color: black; -fx-border-width: 1px; -fx-background-color: rgb(227,233,191);");
        label.setAlignment(Pos.CENTER);

        Label nameLabel = new Label("Name:  ");
        nameLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        TextField textField = new TextField();
        textField.setStyle("-fx-min-height: 30px; -fx-min-width: 160px; -fx-background-radius: 10px");
        HBox hBox = new HBox(40);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(0, 0, 20, 130));
        hBox.getChildren().addAll(nameLabel, textField);

        Button deleteButton = new Button("Delete");
        deleteButton.setPrefHeight(20);
        deleteButton.setPrefWidth(100);
        deleteButton.setStyle("-fx-text-fill: white; -fx-background-color: green; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 25px;");
        deleteButton.setOnAction(e -> {
            String teamName = textField.getText();

            if (deleteTeamFromFile(teamName)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Team Deleted successfully...!!!");
                alert.show();
                textField.clear();
                Tournament tournament = new Tournament();
                tournament.readTeamsFromFile();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Team not Found...!!!");
                alert.show();
            }
        });


        Button backButton = new Button("Back");
        backButton.setPrefHeight(20);
        backButton.setPrefWidth(100);
        backButton.setStyle("-fx-text-fill: white; -fx-background-color: green; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 25px;");
        backButton.setOnAction(e -> {
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            Scene scene1 = new Scene(new StackPane(), 1000, 800);
            currentStage.setTitle("Teams of PSL...!!!");
            currentStage.setScene(createTeamScene(currentStage, scene1));
        });

        VBox vBox = new VBox(20);
        vBox.setStyle("-fx-background-color: rgb(227,233,191); -fx-border-color: black; -fx-order-width: 1px;");
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinWidth(500);
        vBox.setMinHeight(400);
        vBox.setMaxWidth(500);
        vBox.setMaxHeight(400);
        vBox.getChildren().addAll(hBox, deleteButton, backButton);

        VBox vBox1 = new VBox();
        vBox1.setStyle("-fx-background-color: rgb(215,223,163);");
        vBox1.setAlignment(Pos.CENTER);
        vBox1.getChildren().addAll(label, vBox);

        return new Scene(vBox1, 1000, 800);
    }




    public boolean deleteTeamFromFile(String teamName) {
        List<String> TeamList1 = new ArrayList<>();
        boolean playerFound = false;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(TEAM_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                String team = details[0];

                if (!(team.equalsIgnoreCase(teamName))) {
                    TeamList1.add(line);
                } else {
                    playerFound = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (playerFound) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(TEAM_FILE));
                for (String teams : TeamList1) {
                    writer.write(teams);
                    writer.newLine();
                }
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return playerFound;
    }


}
