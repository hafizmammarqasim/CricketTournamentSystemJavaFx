package com.example.hblpsl;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class Menu {

//    public static void main(String[] args) {
//        launch();
//    }


    public Scene start(Stage stage, Scene scene) throws Exception {


        StackPane stackPane = new StackPane();
        Image image = new Image(getClass().getResource("/Images/background2.jpg").toExternalForm());
        ImageView imageView =  new ImageView(image);
        imageView.setPreserveRatio(false);
        imageView.fitHeightProperty().bind(stage.heightProperty());
        imageView.fitWidthProperty().bind(stage.widthProperty());

        //scene.getStylesheets().add(getClass().getResource("/css/menu.css").toExternalForm());



        Label label =  new Label();
        label.setVisible(false);


        Button teamButton = new Button("Teams");
        teamButton.setId("teamButton");
        teamButton.setMinWidth(190);
        teamButton.setMinHeight(80);
        teamButton.setStyle("-fx-font-size: 18px;-fx-font-weight: bold;-fx-background-color: RED;-fx-text-fill: WHITE;-fx-background-radius: 15px;");
        teamButton.setOnAction(e ->{
            try {
                TeamsDesign teamsDesign = new TeamsDesign();
                Stage currentStage = (Stage) teamButton.getScene().getWindow();
                currentStage.setTitle("Teams of PSL...!!!");

                Scene teamScene = new Scene(new StackPane(), 1000, 800);
                currentStage.setScene(teamsDesign.createTeamScene(currentStage,teamScene));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        });



        Button matchButton = new Button("Schedule");
        matchButton.setId("matchButton");
        matchButton.setMinWidth(190);
        matchButton.setMinHeight(80);
        matchButton.setStyle("-fx-font-size: 19px;-fx-font-weight: bold;-fx-background-color: RED;-fx-text-fill: WHITE;-fx-background-radius: 15px;");
        matchButton.setOnAction(e -> {
           try {
               Tournament tournament = new Tournament();
               Stage currentStage = (Stage) matchButton.getScene().getWindow();
               currentStage.setTitle("Matches of PSL...!!!");

               Scene matchScene = new Scene(new StackPane(),1000,800);
               currentStage.setScene(tournament.createSchedule(currentStage,matchScene));

           } catch (Exception e1) {
               e1.printStackTrace();
           }
        });



        Button pointButton = new Button("Points Table");
        pointButton.setId("pointButton");
        pointButton.setMinWidth(190);
        pointButton.setMinHeight(80);
        pointButton.setStyle("-fx-font-size: 18px;-fx-font-weight: bold;-fx-background-color: RED;-fx-text-fill: WHITE;-fx-background-radius: 15px;");

        pointButton.setOnAction(e -> {
            try{
                PointsTableDesign pointsTableDesign = new PointsTableDesign();
                Stage currentStage5 = (Stage) pointButton.getScene().getWindow();
                currentStage5.setTitle("Points Table of PSL...!!!");

                Scene pointsTableScene = new Scene(new StackPane(),1000,800);
                currentStage5.setScene(pointsTableDesign.createPointsTableScene(currentStage5,pointsTableScene));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });


        VBox vBox1 = new VBox(10);
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setSpacing(20);
        vBox1.getChildren().addAll(teamButton,matchButton,pointButton,label);


        stackPane.getChildren().addAll(imageView,vBox1);
        return new Scene(stackPane,1000,800);
    }


}
