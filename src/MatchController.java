public class MatchController {
    private int overs;
    private Inning firstInning;
    private Inning secondInning;

    public MatchController() {
    }

    public void config(String t1Name, String t2Name, int overs) {
        this.overs = overs;
        // create teams
        Team t1 = Team.getDefaultTeam(t1Name);
        Team t2 = Team.getDefaultTeam(t2Name);
        // toss
        // create Innings
        firstInning = new Inning(t1, t2);
        secondInning = new Inning(t2, t1);
        // start the match
        this.start();
    }
    public void start() {
        System.out.println(overs + "-over Match starts...\n");
        // simulateInnings
        simulateInning(firstInning);
        simulateInning(secondInning);
        // Result
        result();
    }

    public void simulateInning(Inning inning) {
        System.out.println(inning.getBattingteam().getName() + " batting, " +
                            inning.getBallingTeam().getName()+ " bowling...");
        simulateOvers(inning, overs);
        displayTotal(inning);
    }

    public void simulateOvers(Inning inning, int overs) {
        int runs = 0;
        int wickets = 0;

        System.out.print("Score: ");
        l1:for (int i=0; i<overs; i++) {
            for (int j=0; j<6; j++) {
                int ballResult = getBallResult();
                // check if wicket fallen
                if (ballResult == 7) {
                    wickets++;
                    System.out.print('W');
                    // check if team is all out
                    if (wickets == 10) {
                        break l1;
                    }
                } else {
                    runs += ballResult;
                    System.out.print(ballResult);
                }

                if (j<5) {
                    System.out.print(",");
                }
            }
            System.out.print(" | ");
        }

        inning.setTotalRuns(runs);
        inning.setTotalWickets(wickets);
    }

    public int getBallResult() {
        // 0-6 for runs and 7 fow Wicket
        return (int)(Math.random() * 8);
    }

    public void displayTotal(Inning inning) {
        System.out.println("\nTotal: " + inning.getTotalRuns() + "/" +
                            inning.getTotalWickets() + "\n");
    }

    public void result() {
        Team winner = null;
        if (firstInning.getTotalRuns() > secondInning.getTotalRuns()) {
            winner = firstInning.getBattingteam();
        } else if (firstInning.getTotalRuns() < secondInning.getTotalRuns()) {
            winner = secondInning.getBattingteam();
        }

        if (winner != null) {
            System.out.println(winner.getName() + " WON!");
        } else {
            System.out.println("MATCH TIED!");
        }
    }
}
