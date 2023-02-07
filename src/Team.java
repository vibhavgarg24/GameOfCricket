import java.util.ArrayList;

public class Team {

    private String name;
    private ArrayList<Player> players;

    public Team() {
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Team getDefaultTeam(String name) {
        Team team = new Team();
        team.setName(name);

        // add 11 default players to the default team
        ArrayList<Player> players = new ArrayList<>(11);
        for (int i=0; i<11; i++) {
            players.add(Player.getDefaultPlayer("player" + (i+1)));
        }
        team.setPlayers(players);

        return team;
    }

    public void displayTeam() {

        System.out.println("Team Name: " + this.name);

        // display players in the team
        for (int i=0; i<11; i++) {
            System.out.println("\tPlayer: " + this.players.get(i).getName());
        }
        System.out.println();
    }
}
