package com.example.hblpsl;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Tournament {
    private  ArrayList<Team> teams = new ArrayList<>();
    private Schedule schedule = new Schedule();
    private ArrayList <Match> matches;
    private ArrayList<Ground> grounds = new ArrayList<>();
    private static final String TEAM_FILE = "Teams.txt";
    private static final String MATCH_FILE = "Match.txt";

    public void addTeam(ArrayList<Team> teams){
        this.teams = teams;
    }


    public void Schedule() throws IOException {
        Match tempMatch;
        Ground tempGround;
        schedule = new Schedule();
        Schedule remainingMatches = new Schedule();
        matches = new ArrayList<>();
        //Syntax for Date
        LocalDate date = LocalDate.of(2025,1,15);

        //Creating random matches
        int size = teams.size();
        if(size>=2) {
            for (int i = 0; i < size-1; i++) {
                for (int j=i; j<size; j++) {
                    if (teams.get(i) != teams.get(j)){
                        tempGround = checkGround(teams.get(i),teams.get(j));
                        tempMatch = new Match(teams.get(i),teams.get(j),(tempGround!=null)? tempGround : new Ground("Qadafi","Lahore","30000","94m") );
                        matches.add(tempMatch);
                    }}}

            if(!matches.isEmpty()){
                for(Match match: matches){
                    remainingMatches.addNewMatch(match); }
            }

            File file = new File("match.txt");
            if(file.exists()) {
                for (Match match : remainingMatches.getMatches()) {
                    readWinStatus(match,file);
                }
            } else {
                System.out.println("Past Data is not available. File does not exist");
            }

            //Sorting Matches

            boolean status;
            do{
                status = false;
                for(int i=0; i<remainingMatches.getMatches().size(); i++) {
                    if (schedule.getMatches().isEmpty() || remainingMatches.getMatches().get(i) != null && !(remainingMatches.getMatches().get(i).teamsComparison(schedule.getMatches().getLast()))) {
                        tempMatch = remainingMatches.getMatches().get(i);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd,yyyy");
                        tempMatch.addDate(date.format(formatter));
                        date = date.plusDays(1);
                        schedule.addNewMatch(tempMatch);
                        remainingMatches.getMatches().remove(i);
                        status = true;
                    }
                }
            } while(!remainingMatches.getMatches().isEmpty() && status);

            //Bachay huay matches yaha se assign houn ge
            if(!remainingMatches.getMatches().isEmpty()){
                for (Match remainingmatch : remainingMatches.getMatches()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd,yyyy");
                    tempMatch = remainingmatch;
                    tempMatch.addDate(date.format(formatter));
                    schedule.addNewMatch(tempMatch);
                    date = date.plusDays(1);
                }
            }
            schedule.displayMatches();
//            match(schedule.getMatches().getFirst());
        }
    }

    public void readTeamsFromFile() {
        try {
            File file = new File(TEAM_FILE);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String teamName = parts[0];
                String city = parts[1];

                Team team = new Team(teamName, city);
                teams.add(team);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        scheduleMatches();
    }

    public void scheduleMatches() {
        Match tempMatch;
        LocalDate date = LocalDate.of(2024, 12, 15);

        // Ensure enough teams for matches
        if (teams.size() < 2) {
            System.out.println("Not enough teams to schedule matches.");
            return;
        }

        // Creating matches for teams
        ArrayList<Match> remainingMatches = new ArrayList<>();
        for (int i = 0; i < teams.size() - 1; i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                Ground ground = checkGround(teams.get(i), teams.get(j));
                tempMatch = new Match(
                        teams.get(i),
                        teams.get(j),
                        ground != null ? ground : new Ground("Gadafi", "Lahore", "30000", "94m")
                );
                remainingMatches.add(tempMatch);
            }
        }

        // Sorting and scheduling matches with sequential dates
        int counter = 0;
        boolean status;
        do {
            status = false;
            for (int i = 0; i < remainingMatches.size(); i++) {
                if (schedule.getMatches().isEmpty() ||
                        remainingMatches.get(i) != null &&
                                !remainingMatches.get(i).teamsComparison(schedule.getMatches().get(schedule.getMatches().size() - 1))) {

                    tempMatch = remainingMatches.get(i);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy");
                    tempMatch.addDate(date.format(formatter));
                    date = date.plusDays(1);
                    schedule.addNewMatch(tempMatch);
                    remainingMatches.remove(i);
                    status = true;
                    break; // Exit loop to re-evaluate remaining matches
                }
            }
        } while (!remainingMatches.isEmpty() && status);

        // Assign dates for any remaining matches
        if (!remainingMatches.isEmpty()) {
            for (Match remainingMatch : remainingMatches) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy");
                tempMatch = remainingMatch;
                tempMatch.addDate(date.format(formatter));
                schedule.addNewMatch(tempMatch);
                date = date.plusDays(1);
            }
        }
        writeScheduleToFile();
    }


    public void writeScheduleToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MATCH_FILE))) {
            for (Match match : schedule.getMatches()) {
                writer.write(match.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Ground checkGround(Team team1, Team team2) {
        for (Ground ground : grounds) {
            if (team1.getHomeCity().equals(ground.getCity()) || team2.getHomeCity().equals(ground.getCity()))
                return ground;
        }
        return !grounds.isEmpty() ? grounds.get(0) : null;
    }

    public void readWinStatus(Match match, File file){
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = reader.readLine())!=null){
                String[] data = line.split(",");
                if(match.getTeam1().getName().equals(data[0]) && match.getTeam2().getName().equals(data[1])){
                    match.assignData(data[2],data[3],data[4],data[5],data[6],data[7],data[8],data[9]);
                    match.decideWinner();
                    return;
                }
            }
        } catch (IOException e){
            System.out.println("Error in reading Match Result File "+e.getMessage());
        }
    }


    public Scene createSchedule(Stage stage, Scene scene) {

        //readTeamsFromFile();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(100);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);


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

        try{
            BufferedReader reader = new BufferedReader(new FileReader(MATCH_FILE));
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String[] array =  line.split(",");

                String firstEntry = array[0];
                String[] twoTeams = firstEntry.split(" vs ");
                String team1 = twoTeams[0];
                String team2 = twoTeams[1];
                Label venu = new Label(array[2]);
                Label firstTeam = new Label(twoTeams[0]);
                Label secondTeam = new Label(twoTeams[1]);
                Label abc = new Label("vs");
                HBox hBox = new HBox(20);
                hBox.getChildren().addAll(firstTeam,abc,secondTeam);
                hBox.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
                VBox vBox1 = new VBox(2);
                vBox1.setAlignment(Pos.CENTER);
                vBox1.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
                VBox.setMargin(vBox1,new Insets(2,10,2,10));
                Button addResult = new Button("Result");


                vBox1.getChildren().addAll(hBox,venu,addResult);
                addResult.setOnAction(e -> {
                    try {
                        Scene menuScene1 = new Scene(new StackPane(), 1000, 800);
                        Stage currentStage = (Stage) addResult.getScene().getWindow();
                        currentStage.setTitle("Add Result of Match...!!!");
                        currentStage.setScene(addResultOfMatch(currentStage, menuScene1,team1,team2));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });

                int row0 = index / 2;
                int col = index % 2;
                gridPane.add(vBox1,col,row0);
                index++;
            }
            reader.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }


        VBox vBox = new VBox(backButton,gridPane);

        return new Scene(vBox,1000,800);
    }




    public Scene addResultOfMatch(Stage stage, Scene scene, String team1, String team2){
        Label label = new Label("Enter the Result of Match...!!!");

        Button backButton = new Button("Back");

        backButton.setOnAction(e -> {

            try {
                Scene menuScene = new Scene(new StackPane(), 1000, 800);
                Stage currentStage = (Stage) backButton.getScene().getWindow();
                currentStage.setTitle("Matches of PSL...!!!");
                currentStage.setScene(createSchedule(currentStage, menuScene));
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        });

        Label label2 = new Label(team1+": ");
        TextField textField2 = new TextField();
        textField2.setPromptText("Runs");
        HBox hBox2 = new HBox(10);  hBox2.getChildren().addAll(label2,textField2);

        Label label4 = new Label(team2+": ");
        TextField textField4 = new TextField();
        textField4.setPromptText("Runs");
        HBox hBox4 = new HBox(10);  hBox4.getChildren().addAll(label4,textField4);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            String team1Runs = textField2.getText();
            String team2Runs = textField4.getText();
            updateResultInTeamsRecord(team1,team1Runs,team2,team2Runs);
        });

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(backButton,label,hBox2,hBox4,saveButton);

        return new Scene(vBox,1000,800);
    }



    public void updateResultInTeamsRecord(String team1, String team1Runs, String team2, String team2Runs) {
        StringBuilder updatedContent = new StringBuilder();
        boolean team1Updated = false;
        boolean team2Updated = false;

        try{
            BufferedReader reader = new BufferedReader(new FileReader(TEAM_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] array = line.split(",");

                if (array[0].equalsIgnoreCase(team1)) {
                    int runs1 = Integer.parseInt(team1Runs);
                    int runs2 = Integer.parseInt(team2Runs);

                    int matchesPlayed = Integer.parseInt(array[array.length - 4]) + 1;
                    int matchesWon = Integer.parseInt(array[array.length - 3]);
                    int matchesLost = Integer.parseInt(array[array.length - 2]);
                    int points = Integer.parseInt(array[array.length - 1]);

                    if (runs1 > runs2) {
                        matchesWon++;
                        points += 2;
                    } else if (runs1 < runs2) {
                        matchesLost++;
                    }

                    array[array.length - 4] = String.valueOf(matchesPlayed);
                    array[array.length - 3] = String.valueOf(matchesWon);
                    array[array.length - 2] = String.valueOf(matchesLost);
                    array[array.length - 1] = String.valueOf(points);

                    team1Updated = true;

                } else if (array[0].equalsIgnoreCase(team2)) {
                    int runs1 = Integer.parseInt(team1Runs);
                    int runs2 = Integer.parseInt(team2Runs);

                    int matchesPlayed = Integer.parseInt(array[array.length - 4]) + 1;
                    int matchesWon = Integer.parseInt(array[array.length - 3]);
                    int matchesLost = Integer.parseInt(array[array.length - 2]);
                    int points = Integer.parseInt(array[array.length - 1]);
                    
                    if (runs2 > runs1) {
                        matchesWon++;
                        points += 2;
                    } else if (runs2 < runs1) {
                        matchesLost++;
                    }

                    array[array.length - 4] = String.valueOf(matchesPlayed);
                    array[array.length - 3] = String.valueOf(matchesWon);
                    array[array.length - 2] = String.valueOf(matchesLost);
                    array[array.length - 1] = String.valueOf(points);

                    team2Updated = true;
                }
                updatedContent.append(String.join(",", array)).append("\n");
            }

            if (!team1Updated || !team2Updated) {
                System.out.println("One or both teams not found in the file.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEAM_FILE))) {
            writer.write(updatedContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
