package com.example.hblpsl;


import org.w3c.dom.ls.LSOutput;

//import java.io.*;
//import java.nio.Buffer;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class Tournament {
//    private ArrayList <Team> teams = new ArrayList<>();
//    private ArrayList <Match> matches = new ArrayList<>();
//
//
//    Scanner myObj = new Scanner(System.in);
//
//    public void Teams(){
//        System.out.println("Lahore Qalandars");
//        System.out.println("Karachi Kings");
//        System.out.println("Islamabad United");
//        System.out.println("Multan Sultans");
//        System.out.println("Team overview");
//        System.out.println("Add Team");
//
////        Player();
//    }
//
//
//
//
//
//
//    public void Player() throws IOException {
//        System.out.println("Add Player");
//        System.out.println("Display Players");
//        System.out.println("Delete Player");
//        File file = new File("players.txt");
//        if(file.exists()) {
//            addPlayer(teams.getFirst(), file);   //Ye button ke andar ho ga na, to jis button k andar ho ga uska function yaha pass kr dena
//            //E.g Lahore ke andar lahore ka
//            //jo object banay wohi pass krna ha
//        } else {
//            if(file.createNewFile()){
//                addPlayer(teams.getFirst(),file);
//            }
//        }
//        System.out.println("Do you want to delete player:" );
//        int choice = myObj.nextInt();
//        myObj.nextLine();
//
//        switch (choice){
//            case 1:
//                deletePlayer(teams.getFirst(), file);
//        }
//
//    }
//
//
//    public void addPlayer(Team team,File file){
//        boolean flag = false;
//        readPlayerData(team,file);
//        String name, jerseyNumber,role,age,country;
//        Player tempPlayer;
//        if( team.getPlayer().isEmpty()){ //agar array list empty hai tou ye call ho ga
//            System.out.println("Add Captain");
//            System.out.println("-----------------------");
//            tempPlayer = addCaptain(team);
//            flag = true;
//        }
//        else if((!(team.getPlayer().isEmpty()))  && team.getPlayer().size()<=11){
//            if(team.getPlayer().getFirst() == null) {
//                System.out.println("Add Captain");
//                System.out.println("-----------------------");
//                tempPlayer = addCaptain(team);
//                flag = true;
//            }
//            System.out.println("Enter player Details");
//            System.out.println("----------------------");
//            System.out.println("Give player's name:");
//            name = myObj.nextLine();
//            System.out.println("Enter Jersey Number: ");
//            jerseyNumber = myObj.nextLine();
//            System.out.println("Enter Role");
//            role = myObj.nextLine();
//            System.out.println("Enter age: ");
//            age = myObj.nextLine();
//            System.out.println("Enter country");
//            country = myObj.nextLine();
//            tempPlayer = new Player(name, jerseyNumber, role, age, country,team.getName());
//            flag = true;
//        } else {
//            System.out.println("Your team already has Eleven Players, You cannot add more player");
//            return;
//        }
//        try (FileWriter writer = new FileWriter(file, true)) {
//            writer.write(tempPlayer.addToFile());
//            team.setPlayer(tempPlayer);
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            System.out.println("Try block 3");
//        }
//    }
//
//
//
//    public Player addCaptain(Team team){
//        System.out.println("Enter Details of Captain("+team.getCaptain()+")");
//        System.out.println("Enter Jersey Number: ");
//        String jerseyNumber = myObj.nextLine();
//        System.out.println("Enter Role");
//        String role = myObj.nextLine();
//        System.out.println("Enter age: ");
//        String age = myObj.nextLine();
//        System.out.println("Enter country");
//        String country = myObj.nextLine();
//
//        return new Player(team.getCaptain(),jerseyNumber,role,age,country,team.getName());
//    }
//
//
//
//
//    public void readPlayerData(Team team, File file){

//        team.setPlayerList();
//        boolean flag = false;
//        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
//            String line;
//            while (((line = reader.readLine())!=null)){
//                String[] tempPlayerArray = line.split(",");
//                int size = tempPlayerArray.length;
//                if(team.getName().equals(tempPlayerArray[size-1])){
//                    team.setPlayer(new Player(tempPlayerArray[0],tempPlayerArray[1],tempPlayerArray[2],tempPlayerArray[3],tempPlayerArray[4],tempPlayerArray[5]));
//                    flag = true;
//                }
//            }
//        } catch (IOException e){
//            System.out.println(e.getMessage());
//            System.out.println("catch block of readPlayerData");
//        }
//        if(!flag){
//            System.out.println("Data of "+team.getName()+" is not already available. Enter Players for this team");
//            return;
//        }
//        team.displayTeam();
//    }
//
//}