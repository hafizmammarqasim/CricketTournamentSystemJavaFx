package com.example.hblpsl;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;

public class PlayerDesign extends Application {
    public static void main(String[] args) {
        launch();
    }

    ObservableList<Player> playerList = FXCollections.observableArrayList();
    private static final String  PLAYER_FILE  = "PlayerDraft.txt";

    @Override
    public void start (Stage stage) {

//        playerList.add(new Player("Ali","Pak","Multan","Batsman",20,33,563,94,2,8));
//        playerList.add(new Player("John" ,"England","Lahore","Bowler",25,83,457,87,2,6));
//        playerList.add(new Player("musa" ,"England","Lahore","Bowler",25,83,457,87,2,6));
//        playerList.add(new Player("akram" ,"England","Lahore","Bowler",25,83,457,87,2,6));
//        playerList.add(new Player("nabi" ,"England","Lahore","Bowler",25,83,457,87,2,6));
//        playerList.add(new Player("aslam" ,"England","Lahore","Bowler",25,83,457,87,2,6));
//        playerList.add(new Player("azam" ,"England","Lahore","Bowler",25,83,457,87,2,6));
//        playerList.add(new Player("younis" ,"England","Lahore","Bowler",25,83,457,87,2,6));
//        playerList.add(new Player("yousif" ,"England","Lahore","Bowler",25,83,457,87,2,6));
//        playerList.add(new Player("shahbaz" ,"England","Lahore","Bowler",25,83,457,87,2,6));
//        playerList.add(new Player("khizer" ,"England","Lahore","Bowler",25,83,457,87,2,6));
//        playerList.add(new Player("imran" ,"England","Lahore","Bowler",25,83,457,87,2,6));
//        savePlayerstoFile();

        Image image = new Image(getClass().getResource("/Images/background.jpg").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(stage.heightProperty());
        imageView.fitWidthProperty().bind(stage.widthProperty());
        GaussianBlur gaussianBlur = new GaussianBlur();
        gaussianBlur.setRadius(3);
        imageView.setEffect(gaussianBlur);

        HBox hBox = new HBox(10);

        Button back = new Button("Back");

        Button backButton = new Button("Home");

        backButton.setOnAction(e -> {

            try {
                Menu menu = new Menu();
                Scene menuScene = new Scene(new StackPane(), 1000, 800);
                Stage currentStage = (Stage) backButton.getScene().getWindow();
                currentStage.setTitle("Menu Page of PSL");
                currentStage.setScene(menu.start(currentStage, menuScene));
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        });
        hBox.getChildren().addAll(back,backButton);
        hBox.setAlignment(Pos.BASELINE_LEFT);






        Button totalPlayer = new Button("All Players");
        totalPlayer.setMaxWidth(150);
        totalPlayer.setMaxHeight(70);
        totalPlayer.setStyle("-fx-font-weight: bold; -fx-font-size: 16px");
        Button pakPlayers = new Button("Pakistani Players");
        pakPlayers.setMaxWidth(150);
        pakPlayers.setMaxHeight(70);
        pakPlayers.setStyle("-fx-font-weight: bold; -fx-font-size: 16px");
        Button overseasPlayer = new Button("Overseas Players");
        overseasPlayer.setMaxWidth(150);
        overseasPlayer.setMaxHeight(70);
        overseasPlayer.setStyle("-fx-font-weight: bold; -fx-font-size: 16px");
        Button addPlayer = new Button("Add Player");
        addPlayer.setMaxWidth(150);
        addPlayer.setMaxHeight(70);
        addPlayer.setStyle("-fx-font-weight: bold; -fx-font-size: 16px");
        Button removePlayer = new Button("Delete Player");
        removePlayer.setMaxWidth(150);
        removePlayer.setMaxHeight(70);
        removePlayer.setStyle("-fx-font-weight: bold; -fx-font-size: 16px");

        AnchorPane anchorPane = new AnchorPane();
        HBox topButtons = new HBox();
        topButtons.getChildren().addAll(backButton,back);
        HBox bottomButtons = new HBox();
        bottomButtons.getChildren().addAll(addPlayer,removePlayer);

        anchorPane.getChildren().addAll(addPlayer,removePlayer);
        totalPlayer.setOnAction(e -> {
            try{
                Stage currentStage = (Stage) addPlayer.getScene().getWindow();
                Scene scenea = new Scene(new StackPane(),1000,800);
                currentStage.setScene(seeAllPlayers(currentStage, scenea));
                currentStage.setTitle("Total Players in PSL...!!!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        addPlayer.setOnAction(e -> {
            Stage currentStage = (Stage) addPlayer.getScene().getWindow();
            Scene scenea = new Scene(new StackPane(),1000,800);
            currentStage.setScene(saveNewPlayer(currentStage,scenea));
            currentStage.setTitle("Total Players in PSL...!!!");
        });




        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(totalPlayer,pakPlayers,overseasPlayer,addPlayer,removePlayer);
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setPadding(new Insets(0,0,0,100));
        vBox.setSpacing(20);


        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView,hBox,vBox);

        Scene scene = new Scene(stackPane,1000,800);
        scene.setFill(Color.SKYBLUE);
        stage.setTitle("Player Design...!!!");
        stage.setScene(scene);
        stage.show();
        //return new Scene(vBox,1000,800);
    }

    private void savePlayerstoFile () {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(PLAYER_FILE));
            for(Player player : playerList) {
                writer.write(player.toString());
                writer.newLine();
            }
            writer.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }




    public Scene saveNewPlayer(Stage stage,Scene scene) {

        VBox vBox = new VBox(10);
        Label teamLabel = new Label("Enter Player Data");
        teamLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: black;");

        Label pname = new Label("Name: ");
        TextField textField = new TextField();
        pname.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label page = new Label("Age");
        TextField textField1 = new TextField();
        page.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label pteam = new Label("Team/Franchise: ");
        TextField textField2 = new TextField();
        pteam.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label pnationality = new Label("Country: ");
        TextField textField3 = new TextField();
        pnationality.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label pjersey = new Label("Jersey no: ");
        TextField textField4 = new TextField();
        pjersey.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label prole = new Label("Role: ");
        TextField textField5 = new TextField();
        prole.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Button submit = new Button("ADD");





        Button backButton = new Button("Back");
        Image back = new Image(getClass().getResource("/Images/babar.jpeg").toExternalForm());
        ImageView imageView1 = new ImageView(back);
        imageView1.setPreserveRatio(true);
        imageView1.setFitWidth(60);
        imageView1.setFitHeight(40);
        backButton.setGraphic(imageView1);
        backButton.setOnAction(e -> {
            stage.setScene(scene);
        });

        Button homeButton = new Button("Back to Main");
        Image home = new Image(getClass().getResource("/Images/babar.jpeg").toExternalForm());
        ImageView imageView = new ImageView(home);
        imageView.setFitWidth(70);
        imageView.setFitWidth(40);
        imageView.setPreserveRatio(true);
        homeButton.setGraphic(imageView);
        homeButton.setOnAction(e -> {

            try {
                Menu menu = new Menu();
                Scene menuScene = new Scene(new StackPane(), 1000, 800);
                Stage currentStage = (Stage) homeButton.getScene().getWindow();
                currentStage.setTitle("Menu Page of PSL...!!!");
                currentStage.setScene(menu.start(currentStage, menuScene));
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        });

        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(backButton,homeButton);
        hBox.setAlignment(Pos.BASELINE_LEFT);



        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: lightblue;");
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(pname,1,1);  gridPane.add(textField,3,1);
        gridPane.add(page,1,3);    gridPane.add(textField1,3,3);
        gridPane.add(pteam,1,5);   gridPane.add(textField2,3,5);
        gridPane.add(pnationality,1,7);   gridPane.add(textField3,3,7);
        gridPane.add(pjersey,1,9);    gridPane.add(textField4,3,9);
        gridPane.add(prole,1,11);      gridPane.add(textField5,3,11);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(gridPane,hBox);
        //stackPane.setStyle("-fx-background-color: lightblue;");



        return new Scene(stackPane,1000,800);
    }




    public Scene seeAllPlayers1(Stage stage, Scene scene) {

        StackPane stackPane = new StackPane();

        Button backButton = new Button("Back");
        Image back = new Image(getClass().getResource("/Images/babar.jpeg").toExternalForm());
        ImageView imageView1 = new ImageView(back);
        imageView1.setPreserveRatio(true);
        imageView1.setFitWidth(60);
        imageView1.setFitHeight(40);
        backButton.setGraphic(imageView1);
        backButton.setOnAction(e -> {
            stage.setScene(scene);
        });

        Button homeButton = new Button("Back to Main");
        Image home = new Image(getClass().getResource("/Images/babar.jpeg").toExternalForm());
        ImageView imageView = new ImageView(home);
        imageView.setFitWidth(70);
        imageView.setFitWidth(40);
        imageView.setPreserveRatio(true);
        homeButton.setOnAction(e -> {

            try {
                Menu menu = new Menu();
                Scene menuScene = new Scene(new StackPane(), 1000, 800);
                Stage currentStage = (Stage) homeButton.getScene().getWindow();
                currentStage.setTitle("Menu Page of PSL");
                currentStage.setScene(menu.start(currentStage, menuScene));
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        });

        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(backButton,homeButton);
        hBox.setAlignment(Pos.BASELINE_LEFT);


        //           Table of Player
        //showDatalinTable();
        TableView<Player> playerTableView = new TableView<>();

        TableColumn<Player, String> pId = new TableColumn<>("ID");
        pId.setCellValueFactory(new PropertyValueFactory<>("playerID"));

        TableColumn<Player, String> pName = new TableColumn<>("Player Name");
        pName.setCellValueFactory(new PropertyValueFactory<>("playerName"));

        TableColumn<Player, String> pNationality = new TableColumn<>("Player Country");
        pNationality.setCellValueFactory(new PropertyValueFactory<>("nationality"));

        TableColumn<Player, String> pTeam = new TableColumn<>("Player Team");
        pTeam.setCellValueFactory(new PropertyValueFactory<>("franchise"));

        TableColumn<Player, String> pRole = new TableColumn<>("Role");
        pRole.setCellValueFactory(new PropertyValueFactory<>("role"));

        TableColumn<Player, Integer> pAge = new TableColumn<>("Age");
        pAge.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Player, Integer> pJerseyNumber = new TableColumn<>("Jersey Number");
        pJerseyNumber.setCellValueFactory(new PropertyValueFactory<>("jerseyNumber"));

        TableColumn<Player, Integer> pRuns = new TableColumn<>("Total Runs");
        pRuns.setCellValueFactory(new PropertyValueFactory<>("totalRuns"));

        TableColumn<Player, Integer> pHighScore = new TableColumn<>("High Score");
        pHighScore.setCellValueFactory(new PropertyValueFactory<>("highestScores"));

        TableColumn<Player, Integer> pCentury = new TableColumn<>("Century");
        pCentury.setCellValueFactory(new PropertyValueFactory<>("centuries"));

        TableColumn<Player, Integer> pHalfCentury = new TableColumn<>("Half Century");
        pHalfCentury.setCellValueFactory(new PropertyValueFactory<>("playerID"));

        playerTableView.getColumns().addAll(pId,pName,pAge,pTeam,pNationality,pJerseyNumber,pRole,pRuns,pHighScore,pHalfCentury,pCentury);

        playerTableView.setItems(playerList);
        playerTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        playerTableView.setFixedCellSize(25);
        playerTableView.prefHeightProperty().bind(
                playerTableView.fixedCellSizeProperty().multiply(FXCollections.observableArrayList(playerList).size()).add(28) // Header height
        );


        Label welcome = new Label("The Details of All the Players is given beloew:");
        welcome.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");
        welcome.setAlignment(Pos.BASELINE_RIGHT);
        welcome.setPadding(new Insets(0,0,0,500));


        VBox vBox = new VBox(20);
        vBox.setStyle("-fx-background-color: lightblue;");

        vBox.getChildren().addAll(hBox,welcome,playerTableView);


        return new Scene(vBox,1000,800);

    }











        public Scene seeAllPlayers(Stage stage, Scene scene) {
            StackPane stackPane = new StackPane();

            Button backButton = new Button("Back");
            Image back = new Image(getClass().getResource("/Images/Rizwan.jpeg").toExternalForm());
            ImageView imageView1 = new ImageView(back);
            imageView1.setPreserveRatio(true);
            imageView1.setFitWidth(60);
            imageView1.setFitHeight(40);
            backButton.setGraphic(imageView1);
            backButton.setOnAction(e -> stage.setScene(new Scene(new StackPane(), 1000, 800)));

            Button homeButton = new Button("Back to Main");
            Image home = new Image(getClass().getResource("/Images/babar.jpeg").toExternalForm());
            ImageView imageView = new ImageView(home);
            imageView.setFitWidth(70);
            imageView.setFitWidth(40);
            imageView.setPreserveRatio(true);
            homeButton.setGraphic(imageView);
            homeButton.setOnAction(e -> {
                // Code for navigating back to the main menu
            });

            HBox hBox = new HBox(10);
            hBox.getChildren().addAll(backButton, homeButton);
            hBox.setAlignment(Pos.BASELINE_LEFT);

            // Creating Accordion to display player details
            Accordion accordion = new Accordion();


            for (Player player : playerList) {
                TitledPane pane = new TitledPane();
                pane.setText(player.getPlayerName());

                VBox content = new VBox();
                content.getChildren().addAll(
                        new Label("Name: " + player.getPlayerName()),
                        new Label("Country: " + player.getNationality()),
                        new Label("Team: " + player.getTeam()),
                        new Label("Role: " + player.getRole()),
                        new Label("Age: " + player.getAge()),
                        new Label("Jersey Number: " + player.getJerseyNumber())
                );

                pane.setContent(content);
                accordion.getPanes().add(pane);
            }

           // CheckBox[] checkBoxes = new CheckBox[names.length];

            Label welcome = new Label("The Details of All the Players is given below:");
            welcome.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");
            welcome.setAlignment(Pos.BASELINE_RIGHT);
            welcome.setPadding(new Insets(0, 0, 0, 500));

            VBox vBox = new VBox(20);
            vBox.setStyle("-fx-background-color: lightblue;");
            vBox.getChildren().addAll(hBox, welcome, accordion);

            return new Scene(vBox, 1000, 800);
        }


}
