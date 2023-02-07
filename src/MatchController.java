public class MatchController {

    private Team t1, t2;
    private int overs;

    public MatchController(Team t1, Team t2, int overs) {
        this.t1 = t1;
        this.t2 = t2;
        this.overs = overs;
    }

    public void start() {

//        t1.displayTeam();
//        t2.displayTeam();

        System.out.println(overs + "-over Match starts...\n");
        // toss
        // firstInnings
        Inning firstInning = innings(t1, t2);
        // secondInnings
        Inning secondInning = innings(t2, t1);
        // Result
        result(firstInning, secondInning);
    }

    public Inning innings(Team t1, Team t2) {

        System.out.println(t1.getName() + " batting, " + t2.getName() + " bowling...");
        Inning inning = new Inning(t1, t2);
        simulateOvers(inning, overs);
        displayTotal(inning);
        return inning;
    }

    public void simulateOvers(Inning inning, int overs) {
        int runs = 0;
        int wickets = 0;

        System.out.print("Score: ");
        l1:for (int i=0; i<overs; i++) {
            for (int j=0; j<6; j++) {
                int ballResult = getBallResult();

                // check if wicket
                if (ballResult == 7) {
                    wickets++;
                    System.out.print('W');

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
        System.out.println("\nTotal: " + inning.getTotalRuns() + "/" + inning.getTotalWickets() + "\n");
    }

    public void result(Inning firstInning, Inning secondInning) {

        Team winner = null;
        if (firstInning.getTotalRuns() > secondInning.getTotalRuns()) {
            winner = t1;
        } else if (firstInning.getTotalRuns() < secondInning.getTotalRuns()) {
            winner = t2;
        }

        if (winner != null) {
            System.out.println(winner.getName() + " WON!");
        } else {
            System.out.println("MATCH TIED!");
        }
    }
}
