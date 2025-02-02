package com.example.hblpsl;

public class Batsman extends Player implements RunsScored{
    private int runsScored;

    public Batsman(String name, String role) {
        super(name, role);
    }

    public Batsman(String name, String jerseyNumber, String role, String age, String country, String team, String imagePath) {
        super(name, jerseyNumber, role, age, country, team,imagePath);
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }

    public void assignData(String name, String team, String opposition, String runsScored ,String playStatus){
        setPlayerName(name);
        setTeam(team);
        setOpposition(opposition);
        setRunsScored(Integer.parseInt(runsScored));
        setPlayStatus(Boolean.parseBoolean(playStatus));
    }

    public String writeToPlayersScores(){
        return getPlayerName()+","+getTeam()+","+getOpposition()+","+getRunsScored()+","+getPlayStatus()+"\n";
    }

    public String toString(){
        return getPlayerName()+","+getJerseyNumber()+","+getRole()+","+getAge()+","+getNationality()+","+getTeam()+"\n";
    }

    public void displayData(){
        System.out.println("Player Name: "+getPlayerName()+"  || Player Role: "+getRole()+"  || Jersey Number: "+getJerseyNumber()+" || Age: "+getAge()+" || Country: "+getNationality()+" || Team: "+getTeam());
    }

}
