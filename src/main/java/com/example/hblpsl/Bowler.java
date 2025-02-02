package com.example.hblpsl;

public class Bowler extends Player implements WicketsTaken{
    private int wicketsTaken;

    public Bowler(int wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }

    public Bowler(String name, String role, int wicketsTaken) {
        super(name, role);
        this.wicketsTaken = wicketsTaken;
    }

    public Bowler(String name, String jerseyNumber, String role, String age, String country, String team,String imagePath) {
        super(name, jerseyNumber, role, age, country, team,imagePath);
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }

    public void assignData(String name, String team, String opposition, String wicketsTaken,String playStatus){
        setPlayerName(name);
        setTeam(team);
        setOpposition(opposition);
        setWicketsTaken(Integer.parseInt(wicketsTaken));
        setPlayStatus(Boolean.parseBoolean(playStatus));
    }


    public String writeToPlayersScores(){
        return getPlayerName()+","+getTeam()+","+getOpposition()+","+getWicketsTaken()+","+getPlayStatus()+"\n";
    }

    public String toString(){
        return getPlayerName()+","+getJerseyNumber()+","+getRole()+","+getAge()+","+getNationality()+","+getTeam()+"\n";
    }

    public void displayData(){
        System.out.println("Player Name: "+getPlayerName()+"  || Player Role: "+getRole()+"  || Jersey Number: "+getJerseyNumber()+" || Age: "+getAge()+" || Country: "+getNationality()+" || Team: "+getTeam());
    }


}
