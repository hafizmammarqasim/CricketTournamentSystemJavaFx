package com.example.hblpsl;

public class AllRounder extends Player implements WicketsTaken, RunsScored{
    private int runsScored;
    private int wicketsTaken;


    public AllRounder(String name, String role) {
        super(name, role);
    }

    public AllRounder(String name, String jerseyNumber, String role, String age, String country, String team, String imagePath) {
        super(name, jerseyNumber, role, age, country, team,imagePath);
    }

    public void assignData(String name, String team, String opposition, String runsScored ,String wicketsTaken, String playStatus){
        setPlayerName(name);
        setTeam(team);
        setOpposition(opposition);
        setRunsScored(Integer.parseInt(runsScored));
        setWicketsTaken(Integer.parseInt(wicketsTaken));
        setPlayStatus(Boolean.parseBoolean(playStatus));
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

    public String writeToPlayersScores(){
        return getPlayerName()+","+getTeam()+","+getOpposition()+","+getRunsScored()+","+getWicketsTaken()+","+getPlayStatus()+"\n";
    }

    public String toString(){
        return getPlayerName()+","+getJerseyNumber()+","+getRole()+","+getAge()+","+getNationality()+","+getTeam()+"\n";
    }

    public void displayData(){
        System.out.println("Player Name: "+getPlayerName()+"  || Player Role: "+getRole()+"  || Jersey Number: "+getJerseyNumber()+" || Age: "+getAge()+" || Country: "+getNationality()+" || Team: "+getTeam());
    }



}
