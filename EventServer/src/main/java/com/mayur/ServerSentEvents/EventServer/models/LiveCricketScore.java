package com.mayur.ServerSentEvents.EventServer.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LiveCricketScore {
    private int score;
    private int wickets;
    private String team1;
    private String team2;
    private double overs;
    private int target;
    private String battingTeam;

    @Override
    public String toString() {
        return team1+" vs "+team2+" "+battingTeam+" - " + score+"/"+wickets+" "+String.format("%.1f", overs)+" overs"+" target-"+target;
    }
}
