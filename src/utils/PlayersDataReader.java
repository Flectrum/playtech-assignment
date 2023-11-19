package utils;

import model.Player;
import model.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayersDataReader {
    public static List<Player> readPlayersData(String file) throws IOException {
        BufferedReader br
                = new BufferedReader(new FileReader(file));
        List<Player> players = new ArrayList<>();
        Player player = null;
        List<Transaction> transactions = new ArrayList<>();
        String st;
        while ((st = br.readLine()) != null) {
            String[] string = st.split(",");
            if (player == null || !player.getId().equals(string[0])) {
                if (player != null) {
                    player.setTransactions(transactions);
                    transactions = new ArrayList<>();
                    players.add(player);
                }
                player = new Player(string[0]);
            }
            Transaction transaction = new Transaction();
            if (string[1].equals("BET")) {
                transaction.setType(string[1]);
                transaction.setMatchId(string[2]);
                transaction.setValue(Integer.parseInt(string[3]));
                transaction.setSideOfTheMatch(string[4]);
            } else {
                transaction.setType(string[1]);
                transaction.setValue(Integer.parseInt(string[3]));
            }
            transactions.add(transaction);
        }
        assert player != null;
        player.setTransactions(transactions);
        players.add(player);
        return players;
    }
}
