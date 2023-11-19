package model;

public class Transaction {
    private String type;
    private int value;
    private String sideOfTheMatch;
    private String matchId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getSideOfTheMatch() {
        return sideOfTheMatch;
    }

    public void setSideOfTheMatch(String sideOfTheMatch) {
        this.sideOfTheMatch = sideOfTheMatch;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }
}

