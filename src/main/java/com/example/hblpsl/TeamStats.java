package com.example.hblpsl;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeamStats {
    private static final String TEAM_FILE = "Teams.txt";
    private static final String MATCH_FILE = "Match.txt";

    private int matchesPlayed;
    private int matchesWon;
    private int matchesLost;
    private int runsScored;
    private int wicketsTaken;
    private int points;

    public TeamStats (){

    }
    public TeamStats(TeamStats stats){
        this.matchesPlayed = stats.getMatchesPlayed();
        this.matchesLost = stats.getMatchesLost();
        this.matchesWon = stats.getMatchesWon();
        this.runsScored = stats.getRunsScored();
        this.wicketsTaken = stats.getWicketsTaken();
        this.points = stats.getPoints();
    }
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points= this.points+ points;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getMatchesWon() {
        return matchesWon;
    }

    public void setMatchesWon(int matchesWon) {
        this.matchesWon = matchesWon;
    }

    public int getMatchesLost() {
        matchesLost = matchesPlayed - matchesWon;
        return matchesLost;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }


    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }


    public Scene teamStatsScene(Stage stage, Scene scene, String text) {
        VBox vBox1 = new VBox();
        vBox1.setAlignment(Pos.CENTER);
        // vBox1.setStyle("-fx-background-color: rgb(227,233,191");
        vBox1.setMaxHeight(400);
        vBox1.setMaxWidth(400);

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: rgb(255,253,208); -fx-min-width: 400px; -fx-min-height: 350px; -fx-font-size: 17px; -fx-font-weight: bold; -fx-border-color: black; -fx-border-width: 2px;");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(TEAM_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] overviewList = line.split(",");
                if (overviewList[0].equals(text)) {
                    String[] firstSevenParts = new String[Math.min(4,overviewList.length)];
                    System.arraycopy(overviewList,Math.max(overviewList.length - 4,0), firstSevenParts, 0, firstSevenParts.length);
                    Label overViewTeam = new Label("!!!--- Team Stats ---!!!");
                    overViewTeam.setAlignment(Pos.CENTER);
                    overViewTeam.setStyle("-fx-background-color: rgb(255,253,208);-fx-min-width: 400px; -fx-min-height: 100px; -fx-text-fill: green; -fx-font-size: 18px; -fx-font-weight: bold; -fx-border-color: black; -fx-border-width: 2px;");
                    Label nameLabel = new Label("Matches Played:  ");              Label nameValue = new Label(overviewList[7]);
                    Label captainLabel = new Label("Matches Won:  ");        Label captainLabelValue = new Label(overviewList[8]);
                    Label headCoachLabel = new Label("Matches Lost:  ");        Label headCoachValue = new Label(overviewList[9]);
                    Label battingCoachLabel = new Label("Points:  ");          Label battingCoachValue = new Label(overviewList[10]);


                    HBox played = new HBox();             played.getChildren().addAll(nameLabel,nameValue);                 played.setAlignment(Pos.CENTER);
                    HBox won = new HBox();          won.getChildren().addAll(captainLabel,captainLabelValue);               won.setAlignment(Pos.CENTER);
                    HBox lost = new HBox();        lost.getChildren().addAll(headCoachLabel,headCoachValue);                 lost.setAlignment(Pos.CENTER);
                    HBox points = new HBox();     points.getChildren().addAll(battingCoachLabel,battingCoachValue);          points.setAlignment(Pos.CENTER);

                    vBox.getChildren().addAll(played,won,lost,points);
                    vBox1.getChildren().addAll(overViewTeam,vBox);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Button backButton = new Button("back");
        backButton.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-background-radius: 15px; -fx-background-color: rgb(255,253,208);");
        backButton.setMinHeight(30);
        backButton.setMinWidth(80);
        backButton.setOnAction(e -> {
            TeamDetails teamDetails = new TeamDetails();
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            Scene scene1 = new Scene(new StackPane(),1000,800);
            currentStage.setTitle("Teams " + text +"...!!!");
            currentStage.setScene(teamDetails.createTeamDetailsScene(currentStage,scene1,text));
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

        BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(227,233,191),null,null);

        BorderPane borderPane = new BorderPane();
        borderPane.setBackground(new Background(backgroundFill));
        borderPane.setTop(hBox);
        borderPane.setCenter(vBox1);
        BorderPane.setAlignment(hBox, Pos.TOP_LEFT);

        return new Scene(borderPane,1000,800);
    }




    public Scene teamMatchesScene(Stage stage, Scene scene, String text) {
        VBox vBox = new VBox(5);
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinWidth(400);
        vBox.setMaxWidth(400);
        vBox.setMinHeight(550);
        vBox.setMaxHeight(550);
        vBox.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-padding: 5px;");

        Button backButton = new Button("back");
        backButton.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-background-radius: 15px; -fx-background-color: rgb(255,253,208);");
        backButton.setMinHeight(30);
        backButton.setMinWidth(80);
        backButton.setOnAction(e -> {
            TeamDetails teamDetails = new TeamDetails();
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            Scene scene1 = new Scene(new StackPane(),1000,800);
            currentStage.setTitle("Teams " + text );
            currentStage.setScene(teamDetails.createTeamDetailsScene(currentStage,scene1,text));
        });

        try {
            BufferedReader reader = new BufferedReader(new FileReader(MATCH_FILE));
            String line;
            while((line = reader.readLine()) != null) {
                VBox innerVBox = new VBox(5);
                innerVBox.setStyle("-fx-background-color: rgb(255,253,208); -fx-max-width: 300px; -fx-max-height: 100px; -fx-font-size: 16px; -fx-font-weight: bold;");

                String[] array = line.split(",");
                String teams = array[0];
                Label team1 = new Label(array[0]);
                Label vs = new Label(" vs ");
                Label team2 = new Label(array[1]);

                HBox combineTeams = new HBox();
                combineTeams.getChildren().addAll(team1,vs,team2);
                combineTeams.setAlignment(Pos.CENTER);
               // Label date = new Label(array[1]);
                Label venue = new Label(array[2]);
                String[] arrayOfTeams = teams.split(" vs ");
//                String team1 = arrayOfTeams[0];
//                String team2 = arrayOfTeams[1];
                innerVBox.setAlignment(Pos.CENTER);

                if (text.equalsIgnoreCase(array[0]) || text.equalsIgnoreCase(array[1])){
                    Button button = new Button("Play Match");
                    innerVBox.getChildren().addAll(combineTeams,venue,button);
                    //button.setOnAction(e->{
                      //  MatchDesign matchDesign = new MatchDesign();
                    //    matchDesign.createMatchScene(stage,scene,)
                   // });
                }
                vBox.getChildren().add(innerVBox);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(227,233,191),null,null);
        VBox vBox1 = new VBox(20);
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setBackground(new Background(backgroundFill));
        vBox1.getChildren().addAll(backButton,vBox);

        return new Scene(vBox1,1000,800);

    }


}
