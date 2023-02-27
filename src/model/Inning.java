package model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Inning {

    private Team battingTeam;
    private Team bowlingTeam;
    private int totalRuns;
    private int totalWickets;
    private List<Character> scoreLine;

    public Inning(Team battingTeam, Team bowlingTeam) {
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.scoreLine = new ArrayList<>();
    }
}
