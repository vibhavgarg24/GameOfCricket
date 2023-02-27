package model;

import enums.PlayerType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Player {

    private String name;
    private int runs;
    private int wickets;
    private int ballsPlayed;
    private int ballsBowled;
    private PlayerType playerType;

    public Player(String name, int runs, int wickets, PlayerType playerType) {
        this.name = name;
        this.runs = runs;
        this.wickets = wickets;
        this.playerType = playerType;
    }

    public static int getRunsAtBowl(Player player) {
        // 0-6 for runs and 7 fow Wicket
        if (player.getPlayerType() == PlayerType.BATTER) {
            return (int) (Math.random() * 7) + (int) (Math.random() * 2);
        }
        return (int) (Math.random() * 8);
    }

}
