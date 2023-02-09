import java.util.ArrayList;
import java.util.List;

public class Team {

    private String name;
    private List<Player> players;

    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Team getDefaultTeam(String name) {
        Team team = new Team(name);
        // add 11 default players to the default team
        List<Player> players = new ArrayList<>(11);
        for (int i=0; i<11; i++) {
            players.add(new Player("player" + (i+1)));
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
