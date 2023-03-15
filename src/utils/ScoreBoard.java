package utils;

import dao.InningDao;
import dao.TeamDao;
import model.Inning;
import model.Player;
import model.Team;

import java.util.List;

public class ScoreBoard {

    public static void displayScoreBoard(String inningId, List<Player> battingTeamPlayers, List<Player> bowlingTeamPlayers) {
        InningDao inningDao = new InningDao();
        Inning inning = inningDao.findById(inningId);
        TeamDao teamDao = new TeamDao();
        Team battingTeam = teamDao.findById(inning.getBattingTeamId());
        Team bowlingTeam = teamDao.findById(inning.getBowlingTeamId());
        System.out.println("\n" + battingTeam.getName() + " batting, " +
                bowlingTeam.getName() + " bowling...");
        displayScoreLine(inning.getScoreLine());
        displayBattingData(battingTeamPlayers);
        displayBowlingData(bowlingTeamPlayers);
        displayInningTotals(inning);
    }

    private static void displayScoreLine(List<Character> scoreLine) {
        StringBuilder sb = new StringBuilder("ScoreLine: ");
        for (int i = 0; i < scoreLine.size(); i++) {
            char bowlScore = scoreLine.get(i);
            sb.append(bowlScore);
            if ((i + 1) % 6 == 0) {
                sb.append(" | ");
            } else {
                sb.append(',');
            }
        }
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.deleteCharAt(sb.length() - 1);
        }
        System.out.println(sb);
    }

    private static void displayBattingData(List<Player> battingTeamPlayers) {
        StringBuilder sb = new StringBuilder("Batting: ");
        for (Player player : battingTeamPlayers) {
            if (player.getBallsPlayed() != 0) {
                sb.append("\n\t").append(player.getName()).append(": ");
                sb.append(player.getRunsScored()).append(" (");
                sb.append(player.getBallsPlayed()).append(")");
            }
        }
        System.out.println(sb);
    }

    private static void displayBowlingData(List<Player> bowlingTeamPlayers) {
        StringBuilder sb = new StringBuilder("Bowling: ");
        for (Player player : bowlingTeamPlayers) {
            if (player.getBallsBowled() != 0) {
                int overs = player.getBallsBowled() / 6;
                int balls = player.getBallsBowled() % 6;
                sb.append("\n\t").append(player.getName()).append(": ");
                sb.append(player.getWickets()).append("-");
                sb.append(player.getRunsGiven()).append(" (");
                sb.append(overs).append(".");
                sb.append(balls).append(")");
            }
        }
        System.out.println(sb);
    }

    private static void displayInningTotals(Inning inning) {
        int overs = inning.getScoreLine().size() / 6;
        int balls = inning.getScoreLine().size() % 6;
        StringBuilder sb = new StringBuilder("Total: ");
        sb.append(inning.getTotalRuns()).append(" / ");
        sb.append(inning.getTotalWickets()).append(" ");
        sb.append("(").append(overs).append(".");
        sb.append(balls).append(")");
        System.out.println(sb);
    }
}
