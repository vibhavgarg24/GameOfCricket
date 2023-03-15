package service;

import dao.InningDao;
import dao.PlayerDao;
import dao.TeamDao;
import model.Inning;
import model.Player;
import model.Team;
import utils.ScoreBoard;

import java.util.ArrayList;
import java.util.List;

public class InningService {

    private InningDao inningDao;
    private TeamDao teamDao;
    private PlayerDao playerDao;
    private Inning inning;
    private int targetRuns;
    private List<Player> battingTeamPlayers;
    private List<Player> bowlingTeamPlayers;

    private int strikePlayerIndex;
    private int nonStrikePlayerIndex;
    private int nextBatterIndex;
    private int currentBowlerIndex;

    public InningService() {
        this.inningDao = new InningDao();
        this.teamDao = new TeamDao();
        this.playerDao = new PlayerDao();
    }

    public Inning simulateInning(String inningId, int targetRuns, int overs) {
        inning = inningDao.findById(inningId);
        this.targetRuns = targetRuns;
        config();
        simulateOvers(overs);
        saveData();
        ScoreBoard.displayScoreBoard(inningId, battingTeamPlayers, bowlingTeamPlayers);
        return inning;
    }

    private void config() {
        initPlayers();
        strikePlayerIndex = 0;
        nonStrikePlayerIndex = 1;
        nextBatterIndex = 2;
        currentBowlerIndex = 6;
    }

    private void initPlayers() {
        Team battingTeam = teamDao.findById(inning.getBattingTeamId());
        List<String> battingTeamPlayerIds = battingTeam.getPlayerIds();
        battingTeamPlayers = new ArrayList<>(11);
        for (String playerId : battingTeamPlayerIds) {
            battingTeamPlayers.add(playerDao.getDefaultPlayer(playerId));
        }
        Team bowlingTeam = teamDao.findById(inning.getBowlingTeamId());
        List<String> bowlingTeamPlayerIds = bowlingTeam.getPlayerIds();
        bowlingTeamPlayers = new ArrayList<>(11);
        for (String playerId : bowlingTeamPlayerIds) {
            bowlingTeamPlayers.add(playerDao.getDefaultPlayer(playerId));
        }
    }

    private void simulateOvers(int overs) {
        for (int i = 0; i < overs; i++) {
            for (int j = 0; j < 6; j++) {
                Player strikePlayer = battingTeamPlayers.get(strikePlayerIndex);
                int runsAtBall = Player.getRunsAtBall(strikePlayer);
                strikePlayer.setBallsPlayed(strikePlayer.getBallsPlayed() + 1);
                Player currentBowler = bowlingTeamPlayers.get(currentBowlerIndex);
                currentBowler.setBallsBowled(currentBowler.getBallsBowled() + 1);
                if (runsAtBall == 7) {
                    wicketFallen(currentBowler);
                } else {
                    runsScored(runsAtBall, strikePlayer, currentBowler);
                }
                if (teamAllOut() || targetAchieved()) {
                    return;
                }
            }
            swapPlayers();
            changeBowler();
        }
    }

    private void wicketFallen(Player currentBowler) {
        inning.getScoreLine().add('W');
        inning.setTotalWickets(inning.getTotalWickets() + 1);
        currentBowler.setWickets(currentBowler.getWickets() + 1);
        if (!teamAllOut()) {
            strikePlayerIndex = nextBatterIndex;
            nextBatterIndex++;
        }
    }

    private void runsScored(int runsAtBall, Player strikePlayer, Player currentBowler) {
        char runsAtBallChar = (char) ('0' + runsAtBall);
        inning.getScoreLine().add(runsAtBallChar);
        inning.setTotalRuns(inning.getTotalRuns() + runsAtBall);
        strikePlayer.setRunsScored(strikePlayer.getRunsScored() + runsAtBall);
        currentBowler.setRunsGiven(currentBowler.getRunsGiven() + runsAtBall);
        if (runsAtBall % 2 == 1) {
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

    private void saveData() {
        updatePlayers(battingTeamPlayers);
        updatePlayers(bowlingTeamPlayers);
        inningDao.updateInning(inning.get_id(), inning);
    }

    private void updatePlayers(List<Player> players) {
        for (Player playerInMatch : players) {
            Player playerOverall = playerDao.findById(playerInMatch.get_id());
            playerOverall.setRunsScored(playerOverall.getRunsScored() + playerInMatch.getRunsScored());
            playerOverall.setRunsGiven(playerOverall.getRunsGiven() + playerInMatch.getRunsGiven());
            playerOverall.setWickets(playerOverall.getWickets() + playerInMatch.getWickets());
            playerOverall.setBallsPlayed(playerOverall.getBallsPlayed() + playerInMatch.getBallsPlayed());
            playerOverall.setBallsBowled(playerOverall.getBallsBowled() + playerInMatch.getBallsBowled());
            playerDao.updatePlayer(playerInMatch.get_id(), playerOverall);
        }
    }
}
