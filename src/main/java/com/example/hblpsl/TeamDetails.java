package com.example.hblpsl;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class TeamDetails {

    private static final String TEAM_FILE = "Teams.txt";
    private static final String PLAYER_IN_TEAM_FILE = "PlayersInTeams.txt";
    private List<Player> playerList = new ArrayList<>();
    private static final String MATCH_FILE = "Match.txt";
    VBox playerBox = new VBox(10);


    public Scene createTeamDetailsScene(Stage stage, Scene scene, String text) {

        Label teamLabel = new Label(text);
        teamLabel.setStyle("-fx-font-size: 26px; -fx-text-fill: white; -fx-font-weight: bold;");

        Image teamImage = new Image(getClass().getResource("/Images/background2.jpg").toExternalForm());
        ImageView imageView = new ImageView(teamImage);
        imageView.fitHeightProperty().bind(stage.heightProperty());
        imageView.fitWidthProperty().bind(stage.widthProperty());
        imageView.setMouseTransparent(true);

        Button back = new Button("Back");
        back.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-background-radius: 15px; -fx-background-color: rgb(227,233,191);");
        back.setMinHeight(30);
        back.setMinWidth(80);
        back.setOnAction(e -> {
            TeamsDesign teamsDesign = new TeamsDesign();
            Stage currentStage = (Stage) back.getScene().getWindow();
            Scene scene1 = new Scene(new StackPane(),1000,800);
            currentStage.setTitle("Teams of PSL...!!!");
            currentStage.setScene(teamsDesign.createTeamScene(currentStage,scene1));
        });

        Button backButton = new Button("Back to Main");
          backButton.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-background-radius: 15px; -fx-background-color: rgb(227,233,191);");
        backButton.setMinHeight(30);
        backButton.setMinWidth(110);
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

        HBox hBox = new HBox(10);
        BorderPane.setMargin(hBox, new Insets(10,0,0,10));
        hBox.getChildren().addAll(back,backButton);

        Button teamOverview = new Button("Team Overview");
        teamOverview.setStyle("-fx-min-height: 100px; -fx-min-width: 200px; -fx-font-size: 18px; -fx-font-weight: bold; -fx-background-radius: 20px; -fx-background-color: rgb(227,233,191)");
        Button teamPlayers =new Button("Players");
        teamPlayers.setStyle("-fx-min-height: 100px; -fx-min-width: 200px; -fx-font-size: 18px; -fx-font-weight: bold; -fx-background-radius: 20px; -fx-background-color: rgb(227,233,191)");

        HBox twoButtonBox = new HBox(20);
        twoButtonBox.getChildren().addAll(teamOverview,teamPlayers);


        Button teamMatches = new Button("Team Matches");
        teamMatches.setStyle("-fx-min-height: 100px; -fx-min-width: 200px; -fx-font-size: 18px; -fx-font-weight: bold; -fx-background-radius: 20px; -fx-background-color: rgb(227,233,191)");
        teamMatches.setOnAction(e -> {
            TeamStats teamStats1 = new TeamStats();
            Stage currentStage = (Stage) teamMatches.getScene().getWindow();
            Scene scene1 = new Scene(new StackPane(), 1000, 800);
            currentStage.setTitle("Team Matches");
            currentStage.setScene(teamStats1.teamMatchesScene(currentStage,scene1,text));
        });
        

        Button teamStats = new Button("Team Stats");
        teamStats.setStyle("-fx-min-height: 100px; -fx-min-width: 200px; -fx-font-size: 18px; -fx-font-weight: bold; -fx-background-radius: 20px; -fx-background-color: rgb(227,233,191)");
        teamStats.setOnAction(e -> {
            TeamStats teamStats1 = new TeamStats();
            Stage currentStage = (Stage) teamStats.getScene().getWindow();
            Scene scene1 = new Scene(new StackPane(), 1000, 800);
            currentStage.setTitle("Team Stats...!!!");
            currentStage.setScene(teamStats1.teamStatsScene(currentStage,scene1,text));
        });

        HBox twoButtonBox2 = new HBox(30);
        twoButtonBox2.getChildren().addAll(teamMatches,teamStats);

        teamOverview.setOnAction(e -> {
            try {
                Stage currentStage = (Stage) teamOverview.getScene().getWindow();
                Scene scene1 = new Scene(new StackPane(), 1000, 800);
                currentStage.setTitle("Team Overview");
                currentStage.setScene(teamOverviewScene(currentStage,scene1,text));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        teamPlayers.setOnAction(e -> {
            try {
                Stage currentStage = (Stage) teamPlayers.getScene().getWindow();
                Scene scene1 = new Scene(new StackPane(), 1000, 800);
                currentStage.setTitle("Team " + text + " Players...!!!");
                currentStage.setScene(teamPlayersScene(currentStage, scene1, text));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        VBox vBox = new VBox(40);
        vBox.setAlignment(Pos.CENTER);
        vBox.setMaxHeight(400);   vBox.setMaxWidth(400);
        vBox.setMaxHeight(400);   vBox.setMinWidth(400);

        vBox.getChildren().addAll(teamLabel,twoButtonBox,twoButtonBox2);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hBox);
        borderPane.setCenter(vBox);
        BorderPane.setAlignment(vBox, Pos.CENTER);
        BorderPane.setAlignment(hBox, Pos.TOP_LEFT);

        StackPane stackPane = new StackPane(imageView,borderPane);

        return new Scene(stackPane,1000,800);
    }




    public Scene teamOverviewScene(Stage stage, Scene scene, String text) {
        Image image = new Image(getClass().getResource("/Images/trophy.jpeg").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.fitHeightProperty().bind(stage.heightProperty());
        imageView.fitWidthProperty().bind(stage.widthProperty());
        VBox vBox1 = new VBox();
        vBox1.setAlignment(Pos.CENTER_RIGHT);
        vBox1.setStyle("-fx-background-color: red");
        vBox1.setMaxHeight(400);
        vBox1.setMaxWidth(400);

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: red; -fx-min-width: 400px; -fx-min-height: 350px; -fx-font-size: 17px; -fx-font-weight: bold; -fx-border-color: black; -fx-border-width: 2px;");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(TEAM_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] overviewList = line.split(",");
                if (overviewList[0].equals(text)) {
                    String[] firstSevenParts = new String[Math.min(7,overviewList.length)];
                    System.arraycopy(overviewList, 0, firstSevenParts, 0, firstSevenParts.length);
                    Label overViewTeam = new Label("Team OverView");
                    overViewTeam.setAlignment(Pos.CENTER);
                    overViewTeam.setStyle("-fx-background-color: red;-fx-min-width: 400px; -fx-min-height: 100px; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold; -fx-border-color: black; -fx-border-width: 2px; -fx-text-fill: white");
                    Label nameLabel = new Label("Team Name:  ");
                    nameLabel.setStyle("-fx-text-fill: white");
                    Label nameValue = new Label(overviewList[0]);
                    nameValue.setStyle("-fx-text-fill: white");

                    Label captainLabel = new Label("Team Captain:  ");
                    captainLabel.setStyle("-fx-text-fill: white");
                    Label captainLabelValue = new Label(overviewList[1]);
                    captainLabelValue.setStyle("-fx-text-fill: white");

                    Label headCoachLabel = new Label("Head Coach:  ");
                    headCoachLabel.setStyle("-fx-text-fill: white");
                    Label headCoachValue = new Label(overviewList[2]);
                    headCoachValue.setStyle("-fx-text-fill: white");

                    Label battingCoachLabel = new Label("Batting Coach:  ");
                    battingCoachLabel.setStyle("-fx-text-fill: white");
                    Label battingCoachValue = new Label(overviewList[3]);
                    battingCoachValue.setStyle("-fx-text-fill: white");

                    Label bowlingCoachLabel = new Label("Bowling Coach:  ");
                    bowlingCoachLabel.setStyle("-fx-text-fill: white");
                    Label bowlingCoachValue = new Label(overviewList[4]);
                    bowlingCoachValue.setStyle("-fx-text-fill: white");

                    Label ownerLabel = new Label("Team Owner:  ");
                    ownerLabel.setStyle("-fx-text-fill: white");
                    Label ownerValue = new Label(overviewList[5]);
                    ownerValue.setStyle("-fx-text-fill: white");

                    Label teamCity = new Label("Home City:  ");
                    teamCity.setStyle("-fx-text-fill: white");
                    Label teamCityValue = new Label(overviewList[6]);
                    teamCityValue.setStyle("-fx-text-fill: white");

                    HBox nameBox = new HBox();             nameBox.getChildren().addAll(nameLabel,nameValue);                           nameBox.setAlignment(Pos.CENTER);
                    HBox captainBox = new HBox();          captainBox.getChildren().addAll(captainLabel,captainLabelValue);             captainBox.setAlignment(Pos.CENTER);
                    HBox headCoachBox = new HBox();        headCoachBox.getChildren().addAll(headCoachLabel,headCoachValue);            headCoachBox.setAlignment(Pos.CENTER);
                    HBox battingCoachBox = new HBox();     battingCoachBox.getChildren().addAll(battingCoachLabel,battingCoachValue);   battingCoachBox.setAlignment(Pos.CENTER);
                    HBox bowlingCoachBox = new HBox();     bowlingCoachBox.getChildren().addAll(bowlingCoachLabel,bowlingCoachValue);   bowlingCoachBox.setAlignment(Pos.CENTER);
                    HBox ownerBox = new HBox();            ownerBox.getChildren().addAll(ownerLabel,ownerValue);                        ownerBox.setAlignment(Pos.CENTER);
                    HBox teamCityBox = new HBox();         teamCityBox.getChildren().addAll(teamCity,teamCityValue);                    teamCityBox.setAlignment(Pos.CENTER);

                    vBox.getChildren().addAll(nameBox,captainBox,headCoachBox,battingCoachBox,bowlingCoachBox,ownerBox,teamCityBox);
                    vBox1.getChildren().addAll(overViewTeam,vBox);



                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-background-radius: 15px; -fx-background-color: rgb(255,253,208);");
        backButton.setMinHeight(30);
        backButton.setMinWidth(80);
        backButton.setOnAction(e -> {
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            Scene scene1 = new Scene(new StackPane(),1000,800);
            currentStage.setTitle("Teams " + text);
            currentStage.setScene(createTeamDetailsScene(currentStage,scene1,text));
        });

        Button homeButton = new Button("Back to Main");
        homeButton.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-background-radius: 15px; -fx-background-color: rgb(255,253,208);");
        homeButton.setMinHeight(30);
        homeButton.setMinWidth(110);
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
        BorderPane.setMargin(hBox, new Insets(10,0,0,10));
        hBox.getChildren().addAll(backButton,homeButton);

//        BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(227,233,191),null,null);

//        BorderPane borderPane = new BorderPane();
//        borderPane.setTop(hBox);
//        borderPane.setCenter(vBox1);
//        BorderPane.setAlignment(hBox,Pos.TOP_LEFT);
//        BorderPane.setAlignment(vBox1,Pos.CENTER_RIGHT);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(vBox1,hBox);
        AnchorPane.setLeftAnchor(hBox,10.0);
        AnchorPane.setTopAnchor(hBox,10.0);
        AnchorPane.setRightAnchor(vBox1,130.0);
        AnchorPane.setBottomAnchor(vBox1,100.0);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView,anchorPane);
        return new Scene(stackPane,1000,800);
    }



    public Scene teamPlayersScene(Stage stage, Scene scene, String text) {

        Image image = new Image(getClass().getResource("/Images/cricketG.jpg").toExternalForm());
        ImageView backGroundImageOfPlayers = new ImageView(image);
        backGroundImageOfPlayers.fitHeightProperty().bind(stage.heightProperty());
        backGroundImageOfPlayers.fitWidthProperty().bind(stage.widthProperty());

        Button removePlayer = new Button("Delete Player");
        removePlayer.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-background-radius: 15px; -fx-background-color: rgb(255,253,208);");
        removePlayer.setMinHeight(35);
        removePlayer.setMinWidth(130);

        Button addPlayerManually = new Button("Add Player");
        addPlayerManually.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-background-radius: 15px; -fx-background-color: rgb(255,253,208);");
        addPlayerManually.setMinHeight(35);
        addPlayerManually.setMinWidth(130);


        playersOfSpecificTeam(text);


        removePlayer.setOnAction(e -> {
            try {
                Stage currentStage = (Stage) removePlayer.getScene().getWindow();
                Scene scene1 = new Scene(new StackPane(), 1000, 800);
                currentStage.setTitle("Delete Player From " + text + "...!!!");
                currentStage.setScene(deletePlayerFromTeam(currentStage, scene1, text));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });



        addPlayerManually.setOnAction(e -> {
                Stage currentStage = (Stage) addPlayerManually.getScene().getWindow();
                Scene scene1 = new Scene(new StackPane(), 1000,800);
                currentStage.setTitle("Add Player " + text + " Team");
                currentStage.setScene(addPlayerInTeamManually(currentStage,scene1,text));
                currentStage.show();
        });


        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-background-radius: 15px; -fx-background-color: rgb(255,253,208);");
        backButton.setMinHeight(30);
        backButton.setMinWidth(80);
        backButton.setOnAction(e -> {
            try {
                Scene menuScene = new Scene(new StackPane(), 1000, 800);
                Stage currentStage = (Stage) backButton.getScene().getWindow();
                currentStage.setTitle("Team " + text + "...!!!");
                currentStage.setScene(createTeamDetailsScene(currentStage, menuScene, text));
            }catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Button homeButton = new Button("Back to Main");
        homeButton.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-background-radius: 15px; -fx-background-color: rgb(255,253,208);");
        homeButton.setMinHeight(30);
        homeButton.setMinWidth(110);
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

        HBox topButtons = new HBox(10);
        topButtons.getChildren().addAll(backButton,homeButton);
        AnchorPane topBox = new AnchorPane(topButtons);
        AnchorPane.setTopAnchor(topButtons,10.0);
        AnchorPane.setLeftAnchor(topButtons,10.0);
        HBox bottomButtons = new HBox(10);
        bottomButtons.getChildren().addAll(addPlayerManually,removePlayer);
        AnchorPane bottomBox = new AnchorPane(bottomButtons);
        AnchorPane.setBottomAnchor(bottomButtons,15.0);
        AnchorPane.setRightAnchor(bottomButtons,10.0);
        HBox imageBox = new HBox(10);
        imageBox.setAlignment(Pos.CENTER);
        imageBox.getChildren().addAll(playerBox);


        BorderPane upperLayout = new BorderPane();
        upperLayout.setTop(topBox);
        upperLayout.setCenter(imageBox);
        upperLayout.setBottom(bottomBox);
        BorderPane.setMargin(topBox,new Insets(0,0,10,0));

        //Layout to set BackGround Image
        StackPane mainLayout = new StackPane();
        mainLayout.getChildren().addAll(backGroundImageOfPlayers,upperLayout);

        return new Scene(mainLayout, 1000,800);
    }


    public void playersOfSpecificTeam(String text) {
        playerBox.getChildren().clear();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20); // Horizontal gap between images
        gridPane.setVgap(20); // Vertical gap between rows
        gridPane.setAlignment(Pos.CENTER);

        try{
            BufferedReader reader = new BufferedReader(new FileReader(PLAYER_IN_TEAM_FILE));
            String line;
            int index = 0;
            while((line = reader.readLine()) != null) {
                String[] players = line.split(",");
                if(players[5].equals(text)){
                    String imagePath = players[0];
                    String playerDetails = line;
                    String playerName = players[2];
                    Image playerImage = new Image(getClass().getResource(imagePath).toExternalForm());
                    ImageView playerImageView = new ImageView(playerImage);
                    playerImageView.setFitWidth(180);
                    playerImageView.setFitHeight(180);


                    Circle clip = new Circle(80);
                    clip.setCenterX(80);
                    clip.setCenterY(80);
                    playerImageView.setClip(clip);

                    int row = index / 4;
                    int col = index % 4;

                    gridPane.add(playerImageView,col,row);
                    index++;

                    playerImageView.setOnMouseClicked(e -> {
                        Stage currentStage = (Stage) playerImageView.getScene().getWindow();
                        Scene scene = new Scene(new StackPane(), 1000, 800);
                        currentStage.setTitle(playerName + " details");
                        currentStage.setScene(showPlayerDetails(currentStage,scene,playerDetails, text));
                    });

                    //playerBox.getChildren().add(gridPane);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        playerBox.getChildren().add(gridPane);
    }




    public Scene showPlayerDetails(Stage stage, Scene scene, String playerDetails,String text1) {

        String[] details = playerDetails.split(",");
        String imagePath = details[0];
        String id = details[1];     String name = details[2];    String age = details[3];
        String nationality = details[4];  String team = details[5];   String role = details[6];
        String jersey = details[7];


        Label label = new Label(name);
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-background-color: rgb(255,253,208);-fx-min-width: 400px; -fx-min-height: 100px; -fx-text-fill: green; -fx-font-size: 18px; -fx-font-weight: bold; -fx-border-color: black; -fx-border-width: 2px;");


//        Label playerID = new Label("ID:  ");     Label playerIDValue = new Label(id);
        Label nameLabel = new Label("Name:  ");       Label nameValue = new Label(name);
        Label ageLabel = new Label("Age:  ");         Label ageValue = new Label(age);
        Label countryLabel = new Label("Country:  ");       Label countryValue = new Label(nationality);
        Label teamLabel = new Label("Team:  ");        Label teamValue = new Label(team);
        Label roleLabel = new Label("Role:  ");       Label roleValue = new Label(role);
        Label jerseyLabel = new Label("Jersey Number:   ");  Label jerseyValue = new Label(jersey);

       // HBox hBox1 = new HBox(87);    hBox1.getChildren().addAll(playerID,playerIDValue);   hBox1.setPadding(new Insets(0,0,0,110));   // hBox1.setAlignment(Pos.CENTER);
        HBox hBox2 = new HBox(60);    hBox2.getChildren().addAll(nameLabel,nameValue);    hBox2.setPadding(new Insets(0,0,0,110));    // hBox2.setAlignment(Pos.CENTER);
        HBox hBox3 = new HBox(80);    hBox3.getChildren().addAll(ageLabel,ageValue);      hBox3.setPadding(new Insets(0,0,0,110));     //hBox3.setAlignment(Pos.CENTER);
        HBox hBox4 = new HBox(47);    hBox4.getChildren().addAll(countryLabel,countryValue);   hBox4.setPadding(new Insets(0,0,0,110));  //hBox4.setAlignment(Pos.CENTER);
        HBox hBox5 = new HBox(65);    hBox5.getChildren().addAll(teamLabel,teamValue);          hBox5.setPadding(new Insets(0,0,0,110));  //hBox5.setAlignment(Pos.CENTER);
        HBox hBox6 = new HBox(72);     hBox6.getChildren().addAll(roleLabel,roleValue);        hBox6.setPadding(new Insets(0,0,0,110)); //hBox6.setAlignment(Pos.CENTER);
        HBox hBox7 = new HBox(32);     hBox7.getChildren().addAll(jerseyLabel,jerseyValue);        hBox7.setPadding(new Insets(0,0,0,110)); //hBox7.setAlignment(Pos.CENTER);


        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(hBox2,hBox3,hBox4,hBox5,hBox6,hBox7);
        vBox.setMaxHeight(500);     vBox.setMaxWidth(400);
        vBox.setMinHeight(500);      vBox.setMinWidth(400);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: rgb(255,253,208); -fx-font-size: 17px; -fx-font-weight: bold; -fx-border-color: black; -fx-border-width: 2px;");

        Image image = new Image(getClass().getResource(imagePath).toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(400);   imageView.setFitWidth(400);
        Circle clip = new Circle(200, 200, 200);
        imageView.setClip(clip);

        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(label,vBox);
        vBox1.setMaxHeight(600);     vBox1.setMaxWidth(400);
        vBox1.setMinHeight(600);      vBox1.setMinWidth(400);
        vBox1.setPadding(new Insets(0,50,0,50));
        vBox1.setAlignment(Pos.CENTER);

        HBox hBox = new HBox(50);    hBox.getChildren().addAll(imageView,vBox1); hBox.setAlignment(Pos.CENTER);

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-background-radius: 15px; -fx-background-color: rgb(255,253,208);");
        backButton.setMinHeight(30);
        backButton.setMinWidth(80);
        backButton.setOnAction(e -> {
            try {
                Scene menuScene = new Scene(new StackPane(), 1000, 800);
                Stage currentStage = (Stage) backButton.getScene().getWindow();
                currentStage.setTitle("Team " + text1 + " Players...!!!");
                currentStage.setScene(teamPlayersScene(currentStage, menuScene,text1));
            }catch (Exception e1) {
                e1.printStackTrace();
            }
        });


        Button homeButton = new Button("Back to Main");
        homeButton.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-background-radius: 15px; -fx-background-color: rgb(255,253,208);");
        homeButton.setMinHeight(30);
        homeButton.setMinWidth(110);
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

        HBox backBox = new HBox(20);
        backBox.setAlignment(Pos.CENTER);
        backBox.getChildren().addAll(backButton,homeButton);

        BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(227,233,191),null,null);
        BorderPane borderPane = new BorderPane();
        borderPane.setBackground(new Background(backgroundFill));
        borderPane.setTop(backBox);
        BorderPane.setAlignment(backBox,Pos.TOP_LEFT);
        BorderPane.setMargin(backBox,new Insets(10,0,0,10));
        borderPane.setCenter(hBox);
       BorderPane.setAlignment(hBox,Pos.CENTER);

        return new Scene(borderPane,1000,800);

    }





    public Scene addPlayerInTeamManually(Stage stage, Scene scene, String text) {

        Image teamImage = new Image(getClass().getResource("/Images/background2.jpg").toExternalForm());
        ImageView imageView = new ImageView(teamImage);
        imageView.fitHeightProperty().bind(stage.heightProperty());
        imageView.fitWidthProperty().bind(stage.widthProperty());
        imageView.setMouseTransparent(true);

        int players = readPlayersFromFile(text);
        VBox vBox1 = new VBox();

        if (players < 8) {
            Label label1 = new Label("Name:  ");
            label1.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            TextField textField1 = new TextField();
            textField1.setStyle("-fx-min-height: 30px; -fx-min-width: 160px; -fx-background-radius: 10px");
            HBox hBox1 = new HBox(75);
            hBox1.setAlignment(Pos.CENTER_LEFT);
            hBox1.setPadding(new Insets(0,0,0,100));
            hBox1.getChildren().addAll(label1, textField1);

            Label label2 = new Label("Country:  ");
            label2.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            TextField textField2 = new TextField();
            textField2.setStyle("-fx-min-height: 30px; -fx-min-width: 160px; -fx-background-radius: 10px");
            HBox hBox2 = new HBox(30);
            hBox2.setAlignment(Pos.CENTER_LEFT);
            hBox2.setPadding(new Insets(0,0,0,100));
            hBox2.getChildren().addAll(label2, textField2);

            Label label3 = new Label("Player Role:  ");
            label3.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            ComboBox<String> roleBox = new ComboBox<>();
            roleBox.getItems().addAll("Batsman", "Bowler", "All Rounder");
            roleBox.setPromptText("Select your Role");
            roleBox.setStyle("-fx-min-height: 30px; -fx-min-width: 160px; -fx-background-radius: 10px");
            HBox hBox3 = new HBox(88);
            hBox3.setAlignment(Pos.CENTER_LEFT);
            hBox3.setPadding(new Insets(0,0,0,100));
            hBox3.getChildren().addAll(label3, roleBox);

            Label label4 = new Label("Age:  ");
            label4.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            TextField textField4 = new TextField();
            textField4.setStyle("-fx-min-height: 30px; -fx-min-width: 160px; -fx-background-radius: 10px");
            HBox hBox4 = new HBox(93);
            hBox4.setAlignment(Pos.CENTER_LEFT);
            hBox4.setPadding(new Insets(0,0,0,100));
            hBox4.getChildren().addAll(label4, textField4);

            Label label5 = new Label("Jersey No: ");
            label5.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            TextField textField5 = new TextField();
            textField5.setStyle("-fx-min-height: 30px; -fx-min-width: 160px; -fx-background-radius: 10px");
            HBox hBox5 = new HBox(45);
            hBox5.setAlignment(Pos.CENTER_LEFT);
            hBox5.setPadding(new Insets(0,0,0,100));
            hBox5.getChildren().addAll(label5, textField5);

//            Label label6 = new Label("Total Runs:  ");
//            label6.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
//            TextField textField6 = new TextField();
//            textField6.setStyle("-fx-min-height: 30px; -fx-min-width: 160px; -fx-background-radius: 10px");
//            HBox hBox6 = new HBox(40);
//            hBox6.setAlignment(Pos.CENTER_LEFT);
//            hBox6.setPadding(new Insets(0,0,0,100));
//            hBox6.getChildren().addAll(label6, textField6);

            Label label10 = new Label("Image Path:  ");
            label10.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            TextField textField10 = new TextField();
            textField10.setStyle("-fx-min-height: 30px; -fx-min-width: 160px; -fx-background-radius: 10px");
            HBox hBox10 = new HBox(36);
            hBox10.setAlignment(Pos.CENTER_LEFT);
            hBox10.setPadding(new Insets(0,0,0,100));
            hBox10.getChildren().addAll(label10, textField10);

            Button addPlayer = new Button("Add");
            addPlayer.setPrefHeight(20);
            addPlayer.setPrefWidth(100);
            addPlayer.setStyle("-fx-text-fill: white; -fx-background-color: black; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 25px;");
            addPlayer.setOnAction(e -> {
                try {
                    String playerImagePath = textField10.getText();
                    String playerName = textField1.getText();
                    String playerNationality = textField2.getText();
                    String playerRole = roleBox.getValue();
                    int playerAge = Integer.parseInt(textField4.getText());
                    int playerJersey = Integer.parseInt(textField5.getText());
                    //                   int playerRunsTotal = Integer.parseInt(textField6.getText());
//                    int playerHighRuns = Integer.parseInt(textField7.getText());
//                    int playerFifty = Integer.parseInt(textField8.getText());
//                    int playerCentury = Integer.parseInt(textField9.getText());
                    if (!playerImagePath.isEmpty() || playerName.isEmpty() || playerNationality.isEmpty() || playerRole.isEmpty() || textField4.getText().isEmpty() || textField5.getText().isEmpty()) {
                        Alert emptyCredentialsAlert = new Alert(Alert.AlertType.ERROR);
                        emptyCredentialsAlert.setContentText("Incomplete Credentials!");
                        emptyCredentialsAlert.show();
                    } else {
                        Player tempPlayer = identifyPlayerType(playerName, playerNationality, text, playerRole, textField4.getText(), textField5.getText(), //playerRunsTotal, playerHighRuns, playerFifty, playerCentury,
                                playerImagePath);
                        playerList.add(tempPlayer);
                        savePlayersToFile(tempPlayer);

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Player added successfully!");
                        alert.show();
                        Stage currentStage = (Stage) addPlayer.getScene().getWindow();
                        Scene scene1 = new Scene(new StackPane(), 1000,800);
                        currentStage.setTitle("Team Players...!!!");
                        currentStage.setScene(teamPlayersScene(currentStage,scene1,text));
                    }
                        textField1.clear();
                        textField2.clear();
                        roleBox.setValue(null);
                        textField4.clear();
                        textField5.clear();
                        // textField6.clear();
                        // textField7.clear();   textField8.clear();     textField9.clear();
                        textField10.clear();
                    } catch(NumberFormatException ex){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Invalid input. Please enter correct values.");
                        alert.show();
                    }

            });


            Button backButton = new Button("Back");
            backButton.setPrefHeight(20);
            backButton.setPrefWidth(100);
            backButton.setStyle("-fx-text-fill: white; -fx-background-color: grey; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 25px;");
            backButton.setOnAction(e -> {
                Stage currentStage = (Stage) backButton.getScene().getWindow();
                Scene scene1 = new Scene(new StackPane(), 1000,800);
                currentStage.setTitle("Team Players...!!!");
                currentStage.setScene(teamPlayersScene(currentStage,scene1,text));
            });

            Label label = new Label("Enter Player Details");
            label.setPrefWidth(500);
            label.setPrefHeight(100);
            label.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 1px; -fx-background-color: rgb(227,233,191);");
            label.setAlignment(Pos.CENTER);


            VBox vBox = new VBox(10);
            vBox.setStyle("-fx-background-color: rgb(227,233,191); -fx-border-color: black; -fx-order-width: 1px;");
            vBox.setAlignment(Pos.CENTER);
            vBox.setMinWidth(500);
            vBox.setMinHeight(550);
            vBox.setMaxWidth(500);
            vBox.setMaxHeight(550);
            vBox.getChildren().addAll(hBox1,hBox2,hBox3,hBox4,hBox5,//hBox6,hBox7,hBox8,hBox9,
                     hBox10,addPlayer,backButton);

            vBox1.getChildren().addAll(label,vBox);
            vBox1.setAlignment(Pos.CENTER);

            StackPane enterPlayerDetailsLayout = new StackPane();
            enterPlayerDetailsLayout.getChildren().addAll(imageView,vBox1);
            return new Scene(enterPlayerDetailsLayout,1000,800);

        } else {
            Label label = new Label("Team" + text + "players are Complete");
            vBox1.getChildren().add(label);
            label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: red;");
            return new Scene(vBox1,1000,800);
        }
    }

    public Player identifyPlayerType(String playerName, String country, String team, String role, String age, String jerseyNumber, String playerImagePath){
        if(role.contains("at"))
             return new Batsman(playerName,jerseyNumber,role,age,country,team,playerImagePath);
        else if(role.contains("owl"))
            return new Bowler(playerName,jerseyNumber,role,age,country,team,playerImagePath);
        else
            return new AllRounder(playerName, jerseyNumber,role,age,country,team,playerImagePath);

    }

//File se data read karnay ke liya ye function bnaya hai
//public void readPlayerData(Team team){
//    File file = new File("players.txt");
//    if(file.exists()) {
//        //Assigning new array list to avoid duplicating players
//        team.setPlayerList();
//        //reading Players from file
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line;
//            while (((line = reader.readLine()) != null)) {
//                String[] tempPlayerArray = line.split(",");
//                int size = tempPlayerArray.length;
//                if (team.getName().equals(tempPlayerArray[size - 1])) {
//                    if(tempPlayerArray[2].contains("at"))
//                        team.setPlayer(new Batsman(tempPlayerArray[0], tempPlayerArray[1], tempPlayerArray[2], tempPlayerArray[3], tempPlayerArray[4], tempPlayerArray[5]));
//                    else if(tempPlayerArray[2].contains("wl"))
//                        team.setPlayer(new Bowler(tempPlayerArray[0], tempPlayerArray[1], tempPlayerArray[2], tempPlayerArray[3], tempPlayerArray[4], tempPlayerArray[5]));
//                    else if (tempPlayerArray[2].contains("oun")) {
//                        team.setPlayer(new AllRounder(tempPlayerArray[0], tempPlayerArray[1], tempPlayerArray[2], tempPlayerArray[3], tempPlayerArray[4], tempPlayerArray[5]));
//                    } else
//                        team.setPlayer(new Keeper(tempPlayerArray[0], tempPlayerArray[1], tempPlayerArray[2], tempPlayerArray[3], tempPlayerArray[4], tempPlayerArray[5]));
//                }
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            System.out.println("catch block of readPlayerData");
//        }
//    }
//}


public int readPlayersFromFile(String text) {
        int counter = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(PLAYER_IN_TEAM_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] players = line.split(",");
                if(players[4].equals(text)) {
                    counter++;
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return counter;
    }


    public void savePlayersToFile(Player player) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(PLAYER_IN_TEAM_FILE,true));
            writer.newLine();
            writer.write(player.toString());
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }



    public Scene deletePlayerFromTeam(Stage stage, Scene scene, String text) {
        Label label = new Label("Enter the Player Name");
        label.setPrefWidth(500);
        label.setPrefHeight(100);
        label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: green; -fx-border-color: black; -fx-border-width: 1px; -fx-background-color: rgb(227,233,191);");
        label.setAlignment(Pos.CENTER);

//        Label idLabel = new Label("ID:  ");
//        idLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
//        TextField textField1 = new TextField();
//        textField1.setStyle("-fx-min-height: 30px; -fx-min-width: 160px; -fx-background-radius: 10px");
//        HBox hBox1 = new HBox(71);
//        hBox1.setAlignment(Pos.CENTER_LEFT);
//        hBox1.setPadding(new Insets(0,0,0,130));
//        hBox1.getChildren().addAll(idLabel, textField1);


        Label nameLabel = new Label("Name:  ");
        nameLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        TextField textField2 = new TextField();
        textField2.setStyle("-fx-min-height: 30px; -fx-min-width: 160px; -fx-background-radius: 10px");
        HBox hBox2 = new HBox(40);
        hBox2.setAlignment(Pos.CENTER_LEFT);
        hBox2.setPadding(new Insets(0,0,20,130));
        hBox2.getChildren().addAll(nameLabel, textField2);


        Button deleteButton = new Button("Delete");
        deleteButton.setPrefHeight(20);
        deleteButton.setPrefWidth(100);
        deleteButton.setStyle("-fx-text-fill: white; -fx-background-color: green; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 25px;");
        deleteButton.setOnAction(e -> {
//            String playerId = textField1.getText();
            String playerName = textField2.getText();

            if(deletePlayerFromFile(playerName,text)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Player Deleted successfully");
                alert.show();

//                textField1.clear();   textField2.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Player not Found");
                alert.show();
            }
        });

        Button backButton = new Button("Back");
        backButton.setPrefHeight(20);
        backButton.setPrefWidth(100);
        backButton.setStyle("-fx-text-fill: white; -fx-background-color: green; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 25px;");
        backButton.setOnAction(e -> {
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            Scene scene1 = new Scene(new StackPane(), 1000,800);
            currentStage.setTitle("Team Players...!!!");
            currentStage.setScene(teamPlayersScene(currentStage,scene1,text));
        });

        VBox vBox = new VBox(20);
        vBox.setStyle("-fx-background-color: rgb(227,233,191); -fx-border-color: black; -fx-order-width: 1px;");
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinWidth(500);
        vBox.setMinHeight(400);
        vBox.setMaxWidth(500);
        vBox.setMaxHeight(400);
        vBox.getChildren().addAll(hBox2,deleteButton,backButton);

        VBox vBox1 = new VBox();
        vBox1.setStyle("-fx-background-color: rgb(215,223,163);");
        vBox1.setAlignment(Pos.CENTER);
        vBox1.getChildren().addAll(label,vBox);

        return new Scene(vBox1,1000,800);
    }



    public boolean deletePlayerFromFile(String name, String teamName) {
        List<String> playerList1 = new ArrayList<>();
        boolean playerFound = false;

        try{
            BufferedReader reader = new BufferedReader(new FileReader(PLAYER_IN_TEAM_FILE));
            String line;
            while((line = reader.readLine()) != null) {
                String[] details = line.split(",");
//                String playerID = details[1];
                String playerName = details[2];
                String playerTeam = details[5];

                if(!(playerName.equalsIgnoreCase(name) && playerTeam.equalsIgnoreCase(teamName))) {
                    playerList1.add(line);
                } else {
                    playerFound = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (playerFound) {
            try{
                BufferedWriter writer = new BufferedWriter(new FileWriter(PLAYER_IN_TEAM_FILE));
                for(String player: playerList1) {
                    writer.write(player);
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
