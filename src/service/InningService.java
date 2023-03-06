package service;

import model.Inning;
import model.Player;
import utils.ScoreBoard;

public class InningService {

    private Inning inning;

    private int strikePlayerIndex;
    private int nonStrikePlayerIndex;
    private int nextBatterIndex;
    private int currentBowlerIndex;
    private int targetRuns;

    public Inning simulateInning(Inning inning, int targetRuns, int overs) {
        config(inning, targetRuns);
        simulateOvers(overs);
        ScoreBoard.displayScoreBoard(inning);
        return inning;
    }

    private void config(Inning inning, int targetRuns) {
        this.inning = inning;
        this.targetRuns = targetRuns;
        strikePlayerIndex = 0;
        nonStrikePlayerIndex = 1;
        nextBatterIndex = 2;
        currentBowlerIndex = 6;
    }

    private void simulateOvers(int overs) {
        for (int i = 0; i < overs; i++) {
            for (int j = 0; j < 6; j++) {
                Player strikePlayer = inning.getBattingTeam().getPlayers().get(strikePlayerIndex);
                int runsAtBowl = Player.getRunsAtBowl(strikePlayer);
                strikePlayer.setBallsPlayed(strikePlayer.getBallsPlayed() + 1);
                // add bowlsBowled to bowler
                if (runsAtBowl == 7) {
                    wicketFallen();
                } else {
                    runsScored(runsAtBowl, strikePlayer);
                }
                if (teamAllOut() || targetAchieved()) {
                    return;
                }
            }
            swapPlayers();
            changeBowler();
        }
    }

    private void wicketFallen() {
        inning.getScoreLine().add('W');
        inning.setTotalWickets(inning.getTotalWickets() + 1);
        // add wicket to bowler
        if (!teamAllOut()) {
            strikePlayerIndex = nextBatterIndex;
            nextBatterIndex++;
        }
    }

    private void runsScored(int runsAtBowl, Player strikePlayer) {
        char runsAtBowlChar = (char) ('0' + runsAtBowl);
        inning.getScoreLine().add(runsAtBowlChar);
        inning.setTotalRuns(inning.getTotalRuns() + runsAtBowl);
        strikePlayer.setRuns(strikePlayer.getRuns() + runsAtBowl);
        // add runsGiven to bowler
        if (runsAtBowl % 2 == 1) {
            swapPlayers();
        }
    }

    private void swapPlayers() {
        int temp = strikePlayerIndex;
        strikePlayerIndex = nonStrikePlayerIndex;
        nonStrikePlayerIndex = temp;
    }

    private void changeBowler() {
        if (currentBowlerIndex == 10) {
            currentBowlerIndex = 6;
        } else {
            currentBowlerIndex++;
        }
    }

    private boolean teamAllOut() {
        return (inning.getTotalWickets() == 10);
    }

    private boolean targetAchieved() {
        return (targetRuns != -1 && inning.getTotalRuns() > targetRuns);
    }
}
