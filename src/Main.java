import model.Casino;
import model.Match;
import model.Player;
import model.Transaction;
import utils.MatchDataReader;
import utils.PlayersDataReader;
import utils.ResultWriter;

import java.io.*;
import java.util.List;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        try {
            List<Player> players = PlayersDataReader.readPlayersData("player_data.txt");
            List<Match> matches = MatchDataReader.readMatchesData("match_data.txt");
            Casino casino = new Casino();
            analyzeData(players, matches, casino);
            ResultWriter.writeResultToFile(players, casino);
        } catch (IOException e){
            System.out.println("Error caused: " + e.getMessage());
        }
    }


    public static void analyzeData(List<Player> players, List<Match> matches, Casino casino) {
        ListIterator<Player> playerListIterator = players.listIterator();
        while (playerListIterator.hasNext()) {
            Player player = playerListIterator.next();
            for (Transaction transaction : player.getTransactions()) {
                String currentTransaction = transaction.getType();
                if (currentTransaction.equals("DEPOSIT")) {
                    player.setBalance(player.getBalance() + transaction.getValue());
                }
                if (currentTransaction.equals("WITHDRAW")) {
                    if (player.getBalance() >= transaction.getValue()) {
                        player.setBalance(player.getBalance() - transaction.getValue());
                        player.setWithdraw(player.getWithdraw() + transaction.getValue());
                    } else {
                        startRollbackProcess(player, transaction, playerListIterator, casino);
                        break;
                    }
                }
                if (currentTransaction.equals("BET")) {
                    Match match = matches.stream()
                            .filter(m -> transaction.getMatchId().equals(m.getId()))
                            .findAny().orElse(null);
                    if (player.getBalance() >= transaction.getValue()) {
                        player.setBalance(player.getBalance() - transaction.getValue());
                        casino.setBalance(casino.getBalance() + transaction.getValue());
                        player.setNumberOfPlacedBets(player.getNumberOfPlacedBets() + 1);
                        assert match != null;
                        if (match.getResult().equals("DRAW")) {
                            player.setBalance(player.getBalance() + transaction.getValue());
                            casino.setBalance(casino.getBalance() - transaction.getValue());
                        } else if (transaction.getSideOfTheMatch().equals(match.getResult())) {
                            int gain = (int) Math.floor(transaction.getValue() * (match.getRate() + 1));
                            player.setBalance(player.getBalance() + gain);
                            player.setNumberOfWonGame(player.getNumberOfWonGame() + 1);
                            casino.setBalance(casino.getBalance() - gain);
                            casino.addPlayerTransaction(player, (int) Math.floor(transaction.getValue() * (match.getRate())));
                        } else {
                            casino.addPlayerTransaction(player, (transaction.getValue() * -1));
                        }
                    } else {
                        startRollbackProcess(player, transaction, playerListIterator, casino);
                        break;
                    }
                }
            }
        }
    }

    public static void startRollbackProcess(Player player, Transaction transaction, ListIterator<Player> listIterator,
                                            Casino casino) {
        casino.rollbackPlayerTransaction(player);
        casino.addToThePlayersWithIllegalTransactionsList(player, transaction);
        listIterator.remove();
    }
}