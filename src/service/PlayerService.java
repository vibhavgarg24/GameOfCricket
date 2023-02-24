package service;

import enums.PlayerType;
import model.Player;

public class PlayerService {

    public int getRunsAtBowl(Player player) {
        // 0-6 for runs and 7 fow Wicket
        if (player.getPlayerType() == PlayerType.BATTER) {
            return (int) (Math.random() * 7) + (int) (Math.random() * 2);
        }
        return (int) (Math.random() * 8);
    }
}
