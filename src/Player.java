public class Player {

    private String name;
    private int runs;
    private int wickets;

    public static Player getDefaultPlayer(String name) {
        Player player = new Player();
        player.setName(name);
        return player;
    }

    public Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }
}
