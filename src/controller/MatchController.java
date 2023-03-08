package controller;

import dao.InningDao;
import dao.MatchDao;
import dao.TeamDao;
import model.Inning;
import model.Match;
import model.Team;
import service.InningService;

public class MatchController {

    private Match match;
    private MatchDao matchDao;
    private InningService inningService;
    private InningDao inningDao;
    private TeamDao teamDao;

    public MatchController(String t1Name, String t2Name, int overs) {
        this.matchDao = new MatchDao();
        this.inningService = new InningService();
        this.inningDao = new InningDao();
        this.teamDao = new TeamDao();
        this.match = config(t1Name, t2Name, overs);
    }

    private Match config(String t1Name, String t2Name, int overs) {
        String t1Id = teamDao.findByName(t1Name).get_id();
        String t2Id = teamDao.findByName(t2Name).get_id();
        // toss
        int tossValue = toss(t1Name, t2Name);
        String firstInningId;
        String secondInningId;
        if (tossValue < 2) {
            firstInningId = inningDao.addInning(t1Id, t2Id);
            secondInningId = inningDao.addInning(t2Id, t1Id);
        } else {
            firstInningId = inningDao.addInning(t2Id, t1Id);
            secondInningId = inningDao.addInning(t1Id, t2Id);
        }
        return new Match(overs, firstInningId, secondInningId);
    }

    private int toss(String t1Name, String t2Name) {
        int tossValue = (int) (Math.random() * 4);
        String tossString = "";
        switch (tossValue) {
            case 0:
                tossString = t1Name + " won and chose Batting.";
                break;
            case 1:
                tossString = t2Name + " won and chose Bowling.";
                break;
            case 2:
                tossString = t1Name + " won and chose Bowling.";
                break;
            case 3:
                tossString = t2Name + " won and chose Batting.";
        }
        System.out.println("\nTOSS: " + tossString);
        return tossValue;
    }

    public void start() {
        System.out.println(match.getOvers() + "-over Match starts...");
        Inning firstInning = inningService.simulateInning(match.getFirstInningId(), -1, match.getOvers());
        Inning secondInning = inningService.simulateInning(match.getSecondInningId(), firstInning.getTotalRuns(), match.getOvers());
        findWinner(firstInning, secondInning);
        printResult();
        saveMatch();
    }

    private void findWinner(Inning firstInning, Inning secondInning) {
        String winnerId = null;
        if (firstInning.getTotalRuns() > secondInning.getTotalRuns()) {
            winnerId = firstInning.getBattingTeamId();
        } else if (firstInning.getTotalRuns() < secondInning.getTotalRuns()) {
            winnerId = secondInning.getBattingTeamId();
        }
        match.setWinnerId(winnerId);
    }

    private void printResult() {
        Team winner = teamDao.findById(match.getWinnerId());
        String winnerString = "";
        if (winner != null) {
            winnerString = winner.getName() + " WON!";
        } else {
            winnerString = "MATCH TIED!";
        }
        System.out.println("\nRESULT: " + winnerString);
    }

    private void saveMatch() {
        String matchId = matchDao.addMatch(match.getOvers(), match.getFirstInningId(),
                match.getSecondInningId(), match.getWinnerId());
        System.out.println("\nMatchId: " + matchId);
    }
}
