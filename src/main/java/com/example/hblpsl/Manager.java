package com.example.hblpsl;

import javafx.scene.control.Alert;

import java.util.ArrayList;

public class Manager {
    private static Manager instance;
    private static ArrayList <Team> teams;
    private static ArrayList <Match> matches;
    private static Schedule globalSchedule;
    private static ArrayList<Ground> grounds;


    private Manager() {
        teams = new ArrayList<>();
        matches = new ArrayList<>();
        grounds = new ArrayList<>();
        globalSchedule = new Schedule();
    }

    public static Manager getInstance(){
        if(instance== null)
            instance = new Manager();
        return instance;
    }

    public static void addTeamList(ArrayList<Team> incomingTeams){
        teams = incomingTeams;
    }

    public static boolean addTeam(Team team){
        if(teams!=null) {
            if(checkDuplicateTeams(team)) {
                teams.add(team);
                return true;
            }
            else
                return false;
        }
        else{
            teams = new ArrayList<>();
            teams.add(team);
            return true;
        }
    }

    public static boolean checkDuplicateTeams(Team team){
        for (Team checkTeams : teams)
            if(checkTeams.getName().equals(team.getName()))
                return false;
        return true;
    }

    public static ArrayList<Team> getTeamsList(){
        return teams;
    }

    public static void addSchedule(Schedule incomingSchedule){
        globalSchedule = incomingSchedule;
    }

    public static Schedule getSchedule(){
        return globalSchedule;
    }

    public static void initializeTeamList(){
        teams = new ArrayList<>();
    }

    public static void initializeMatchList(){
        matches = new ArrayList<>();
    }

    public static void initializeGrounds(){
        grounds = new ArrayList<>();
    }

    public static void addMatchesList(ArrayList<Match> incomingMatches){
        matches = incomingMatches;
    }

    public static ArrayList getMatchesList(){
        return matches;
    }

    public static void addGround(ArrayList<Ground> incomingGround){
        grounds = incomingGround;
    }

    public static ArrayList<Ground> getGrounds(){
        return grounds;
    }
}
