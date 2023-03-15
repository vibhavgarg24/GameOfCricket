package model;

import enums.PlayerType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Player {

    private String _id;
    private String name;
    private int runsScored;
    private int runsGiven;
    private int wickets;
    private int ballsPlayed;
    private int ballsBowled;
    private PlayerType playerType;

    public Player(String name, PlayerType playerType) {
        this.name = name;
        this.playerType = playerType;
    }

    public static int getRunsAtBall(Player player) {
        // 0-6 for runs and 7 for Wicket
        if (player.getPlayerType() == PlayerType.BATTER) {
            return (int) (Math.random() * 7) + (int) (Math.random() * 2);
        }
        return (int) (Math.random() * 8);
    }

}
