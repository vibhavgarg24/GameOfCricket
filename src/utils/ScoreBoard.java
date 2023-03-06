package utils;

import model.Inning;
import model.Player;

import java.util.List;

public class ScoreBoard {

    public static void displayScoreBoard(Inning inning) {
        System.out.println("\n" + inning.getBattingTeam().getName() + " batting, " +
                inning.getBowlingTeam().getName() + " bowling...");
        displayScoreLine(inning.getScoreLine());
        displayRunsByEachBatter(inning.getBattingTeam().getPlayers());
        displayInningTotals(inning);
    }

    private static void displayScoreLine(List<Character> scoreLine) {
        StringBuilder sb = new StringBuilder("ScoreLine: ");
        for (char bowlScore : scoreLine) {
            sb.append(bowlScore).append(',');
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }

    private static void displayRunsByEachBatter(List<Player> players) {
        StringBuilder sb = new StringBuilder("Runs Scored: ");
        for (Player player : players) {
            if (player.getBallsPlayed() != 0) {
                sb.append("\n\t").append(player.getName()).append(": ");
                sb.append(player.getRuns()).append(" (");
                sb.append(player.getBallsPlayed()).append(")");
            }
        }
        System.out.println(sb);
    }

    private static void displayInningTotals(Inning inning) {
        int oversBowled = inning.getScoreLine().size() / 6;
        int bowlsBowled = inning.getScoreLine().size() % 6;
        StringBuilder sb = new StringBuilder("Total: ");
        sb.append(inning.getTotalRuns()).append(" / ");
        sb.append(inning.getTotalWickets()).append(" ");
        sb.append("(").append(oversBowled).append(".");
        sb.append(bowlsBowled).append(")");
        System.out.println(sb);
    }
}
