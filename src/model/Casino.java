package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Casino {
    private long balance;
    private final Map <String,Integer> playersTransactions = new HashMap<>();
    private final List<String> playersWithIllegalTransactions = new ArrayList<>();

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public List<String> getPlayersWithIllegalTransactions() {
        return playersWithIllegalTransactions;
    }

    public void addPlayerTransaction(Player player, int value){
        playersTransactions.merge(player.getId(), value, Integer::sum);
    }

    public void rollbackPlayerTransaction(Player player){
        if(!playersTransactions.isEmpty()) {
            balance += playersTransactions.get(player.getId());
        }
    }

    public void addToThePlayersWithIllegalTransactionsList(Player player, Transaction transaction){
        if(transaction.getType().equals("WITHDRAW")) {
            transaction.setMatchId(null);
            transaction.setSideOfTheMatch(null);
        }
        String currentPlayer = player.getId() + " " + transaction.getType() + " " + transaction.getMatchId() + " " +
                    transaction.getValue() + " " + transaction.getSideOfTheMatch();

        playersWithIllegalTransactions.add(currentPlayer);
    }
}
