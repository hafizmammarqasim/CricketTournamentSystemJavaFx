package com.example.hblpsl;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Match1 {
    private static final String TEAM_FILE = "Teams.txt";
    private static final String MATCH_FILE = "Match.txt";
    List<String> teams = new ArrayList<>();

    public Scene readTeamsFromFile(Stage stage, Scene scene) {
        try {
        BufferedReader reader = new BufferedReader(new FileReader(TEAM_FILE));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] teamName = line.split(",");
            teams.add(teamName[0]);
        }

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        writeScheduleInFile(makeMatchSchedule(teams));


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
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(teamLabel,backButton);
        return new Scene(vBox,1000,800);

    }


    public List<Match99> makeMatchSchedule(List<String> teams) {
        List<Match99> matches = new ArrayList<>();
        LocalDate localDate = LocalDate.now();

        for(int i = 0; i < teams.size() - 1; i++) {
            for (int j = i+1; j<teams.size(); j++) {
                matches.add(new Match99(teams.get(i), teams.get(j), localDate));
                localDate = localDate.plusDays(1);
            }
        }

        Collections.shuffle(matches);
        return matches;

    }

    public void writeScheduleInFile(List<Match99> matches) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(MATCH_FILE));
            List<String> existMatches = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] array = line.split(",");
                String matchTeams = array[0];
                existMatches.add(normalizeTeams(matchTeams));
            }
            reader.close();


            BufferedWriter writer = new BufferedWriter(new FileWriter(MATCH_FILE,true));
            for (Match99 match: matches) {
                String matchString = match.toString();
                String teamsPart = matchString.split(",")[0];
                String normalizedTeams = normalizeTeams(teamsPart);

                if (!existMatches.contains(normalizedTeams)) {
                    writer.write(matchString);
                    writer.newLine();
                    //existMatches.add(normalizedTeams);
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private String normalizeTeams(String teamsPart) {
        String[] teams = teamsPart.split("vs");
        if (teams.length < 2) {
            return teamsPart.trim();
        }
        Arrays.sort(teams);
        return teams[0].trim() + " vs " + teams[1].trim();
    }

}
