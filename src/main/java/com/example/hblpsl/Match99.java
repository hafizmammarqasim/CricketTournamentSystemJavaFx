package com.example.hblpsl;

import java.time.LocalDate;

public class Match99 {
    private String team1;
    private String team2;
    private LocalDate matchDate;
    private String result; // New attribute to store the match result

    public Match99(String team1, String team2, LocalDate matchDate) {
        this.team1 = team1;
        this.team2 = team2;
        this.matchDate = matchDate;
        this.result = "Pending"; // Default value for result
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result; // Allows setting the result later
    }

    @Override
    public String toString() {
        return team1 + " vs " + team2 + "," + matchDate + ",Result: " + result;
    }
}
