public class Main {
    public static void main(String[] args) {

        Team t1 = Team.getDefaultTeam("IND");
        Team t2 = Team.getDefaultTeam("PAK");

        MatchController matchController = new MatchController(t1, t2, 2);
        matchController.start();
    }
}