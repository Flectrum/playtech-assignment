package model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String id;
    private double numberOfWonGame;
    private double numberOfPlacedBets;
    private int withdraw;
    private int balance;
    private List<Transaction> transactions;

    public Player(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getNumberOfWonGame() {
        return numberOfWonGame;
    }

    public void setNumberOfWonGame(double numberOfWonGame) {
        this.numberOfWonGame = numberOfWonGame;
    }

    public double getNumberOfPlacedBets() {
        return numberOfPlacedBets;
    }

    public void setNumberOfPlacedBets(double numberOfPlacedBets) {
        this.numberOfPlacedBets = numberOfPlacedBets;
    }

    public String getWinRate() {
        DecimalFormat toTheFormat = new DecimalFormat("0.00");
        if(numberOfPlacedBets > 0) {
            return toTheFormat.format(numberOfWonGame / numberOfPlacedBets);
        }
        return "0.00";
    }

    public int getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(int withdraw) {
        this.withdraw = withdraw;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        if (transactions == null){
            transactions = new ArrayList<>();
        }
        this.transactions = transactions;
    }

}
