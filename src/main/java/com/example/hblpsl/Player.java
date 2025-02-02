package com.example.hblpsl;

public abstract class Player {

    private String imagePath;
    private String playerName;
    private String nationality;
    private String team;
    private String role;
    private int age;
    private String jerseyNumber;
    private String opposition;
    private PlayerStats stats = new PlayerStats();
    private static int counter = 0;

    public Player(String playerName, String jerseyNumber,  String role, String age, String nationality, String franchise, String imagePath) {
        this.imagePath = imagePath;
        this.playerName = playerName;
        this.age = Integer.parseInt(age);
        this.nationality = nationality;
        this.team = franchise;
        this.role = role;
        this.jerseyNumber = jerseyNumber;
//        this.opposition = opposition;
    }
        private boolean playStatus = false;

        public Player(){

        }
        public Player(String name, String role) {
            this.playerName = name;
            this.role = role;
        }


        public String getOpposition() {
            return opposition;
        }

        public void setOpposition(String opposition) {
            this.opposition = opposition;
        }

        public void setStats(PlayerStats stats) {
            this.stats = stats;
        }

        public boolean getPlayStatus() {
            return playStatus;
        }

        public void setPlayStatus(boolean playStatus) {
            this.playStatus = playStatus;
        }

     public abstract String toString();

     public abstract void displayData();

     public abstract String writeToPlayersScores();


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(String jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }


//    public String toString() {
//        return imagePath + ";" + playerName + ";" + age + ";" + nationality + ";" + team + ";" + role + ";" + jerseyNumber;
//    }
}
