import model.Inning;
import model.Team;
import service.InningService;
import service.TeamService;

public class MatchController {
    private int overs;
    private Inning firstInning;
    private Inning secondInning;

    private InningService inningService;
    private TeamService teamService;

    public MatchController(String t1Name, String t2Name, int overs) {
        this.inningService = new InningService();
        this.teamService = new TeamService();
        this.overs = overs;
        config(t1Name, t2Name);
    }

    private void config(String t1Name, String t2Name) {
        Team t1 = teamService.getDefaultTeam(t1Name);
        Team t2 = teamService.getDefaultTeam(t2Name);
        // toss
        firstInning = new Inning(t1, t2);
        secondInning = new Inning(t2, t1);
    }

    public void start() {
        System.out.println(overs + "-over Match starts...");
        firstInning = inningService.simulateInning(firstInning, -1, overs);
        secondInning = inningService.simulateInning(secondInning, firstInning.getTotalRuns(), overs);
        result();
    }

    public void result() {
        Team winner = null;
        if (firstInning.getTotalRuns() > secondInning.getTotalRuns()) {
            winner = firstInning.getBattingTeam();
        } else if (firstInning.getTotalRuns() < secondInning.getTotalRuns()) {
            winner = secondInning.getBattingTeam();
        }
        if (winner != null) {
            System.out.println("\n" + winner.getName() + " WON!");
        } else {
            System.out.println("\nMATCH TIED!");
        }
    }
}
