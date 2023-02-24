package service;

import enums.PlayerType;
import model.Player;
import model.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamService {

    public Team getDefaultTeam(String name) {
        Team team = new Team(name);
        // add 6 BATTER to the default team
        List<Player> players = new ArrayList<>(11);
        for (int i = 0; i < 6; i++) {
            players.add(new Player("player" + (i + 1), 0, 0, PlayerType.BATTER));
        }
        // add remaining 5 BOWLER to the default team
        for (int i = 6; i < 11; i++) {
            players.add(new Player("player" + (i + 1), 0, 0, PlayerType.BOWLER));
        }
        team.setPlayers(players);
        return team;
    }
}
