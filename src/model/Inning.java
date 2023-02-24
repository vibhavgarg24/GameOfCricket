package model;

import java.util.ArrayList;
import java.util.List;

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

    public Team getBattingTeam() {
        return battingTeam;
    }

    public void setBattingTeam(Team battingTeam) {
        this.battingTeam = battingTeam;
    }

    public Team getBowlingTeam() {
        return bowlingTeam;
    }

    public void setBowlingTeam(Team bowlingTeam) {
        this.bowlingTeam = bowlingTeam;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
    }

    public int getTotalWickets() {
        return totalWickets;
    }

    public void setTotalWickets(int totalWickets) {
        this.totalWickets = totalWickets;
    }

    public List<Character> getScoreLine() {
        return scoreLine;
    }

    public void setScoreLine(List<Character> scoreLine) {
        this.scoreLine = scoreLine;
    }
}
