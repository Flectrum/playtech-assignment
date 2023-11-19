package utils;

import model.Casino;
import model.Player;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ResultWriter {
    public static void writeResultToFile(List<Player> players, Casino casino) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/result.txt"));
        players.sort(new PlayersComparator());
        for (Player player : players) {
            writer.write(player.getId() + " " + player.getBalance() + " " + player.getWinRate());
            writer.newLine();
        }
        if (players.isEmpty()) {
            writer.newLine();
        }
        writer.newLine();
        for (String st : casino.getPlayersWithIllegalTransactions()) {
            writer.write(st);
            writer.newLine();
        }
        if (casino.getPlayersWithIllegalTransactions().isEmpty()) {
            writer.newLine();
        }
        writer.newLine();
        writer.write(String.valueOf(casino.getBalance()));
        writer.close();
    }
}
