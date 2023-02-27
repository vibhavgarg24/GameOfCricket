import controller.MatchController;

public class Main {
    public static void main(String[] args) {
        MatchController matchController = new MatchController("IND", "PAK", 10);
        matchController.start();
    }
}