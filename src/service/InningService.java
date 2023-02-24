package service;

import model.Inning;
import model.Player;

public class InningService {

    private Inning inning;

    private int strikePlayerIndex;
    private int nonStrikePlayerIndex;
    private int nextBatterIndex;
    private int currentBowlerIndex;
    private int targetRuns;

    private PlayerService playerService;
    private ScoreBoardService scoreBoardService;

    public InningService() {
        this.playerService = new PlayerService();
        this.scoreBoardService = new ScoreBoardService();
    }

    private void config(Inning inning, int targetRuns) {
        this.inning = inning;
        this.targetRuns = targetRuns;
        strikePlayerIndex = 0;
        nonStrikePlayerIndex = 1;
        nextBatterIndex = 2;
        currentBowlerIndex = 6;
    }

    public Inning simulateInning(Inning inning, int targetRuns, int overs) {
        config(inning, targetRuns);
        simulateOvers(overs);
        scoreBoardService.displayScoreBoard(inning);
        return inning;
    }

    private void simulateOvers(int overs) {
        l1:
        for (int i = 0; i < overs; i++) {
            for (int j = 0; j < 6; j++) {
                Player strikePlayer = inning.getBattingTeam().getPlayers().get(strikePlayerIndex);
                int runsAtBowl = playerService.getRunsAtBowl(strikePlayer);
                strikePlayer.setBallsPlayed(strikePlayer.getBallsPlayed() + 1);
                // add bowlsBowled to bowler
                if (runsAtBowl == 7) {
                    wicketFallen();
                    if (teamAllOut()) {
                        break l1;
                    }
                } else {
                    runsScored(runsAtBowl, strikePlayer);
                    if (targetRuns != -1 && inning.getTotalRuns() > targetRuns) {
                        break l1;
                    }
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
}
