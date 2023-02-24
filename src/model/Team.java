package model;

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

}