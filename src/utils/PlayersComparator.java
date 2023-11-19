package utils;

import model.Player;

import java.util.Comparator;

public class PlayersComparator implements Comparator<Player> {

    public int compare(Player player1, Player player2) {
        return player1.getId().compareTo(player2.getId());
    }
}
