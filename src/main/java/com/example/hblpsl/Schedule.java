package com.example.hblpsl;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Schedule {
    private ArrayList<Match10> matches = new ArrayList<>();

    public void addNewMatch(Match10 match) {
        matches.add(match);
    }

    public ArrayList<Match10> getMatches() {
        return matches;
    }

    public void Schedule() throws IOException {
        Match99 tempMatch;
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
                        tempMatch = new Match99(teams.get(i),teams.get(j),(tempGround!=null)? tempGround : new Ground("Qadafi","Lahore","30000","94m") );
                        matches.add(tempMatch);
                    }}}

            if(!matches.isEmpty()){
                for(Match10 match: matches){
                    remainingMatches.addNewMatch(match); }
            }

            File file = new File("match.txt");
            if(file.exists()) {
                for (Match99 match : remainingMatches.getMatches()) {
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
                for (Match99 remainingmatch : remainingMatches.getMatches()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd,yyyy");
                    tempMatch = remainingmatch;
                    tempMatch.addDate(date.format(formatter));
                    schedule.addNewMatch(tempMatch);
                    date = date.plusDays(1);
                }
            }
            schedule.displayMatches();
            match(schedule.getMatches().getFirst());
        }
    }


}