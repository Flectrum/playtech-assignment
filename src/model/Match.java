package model;

public class Match {
    private final String id;
    private final double rateA;
    private final double rateB;
    private final String result;

    public Match(String id, double rateA, double rateB, String result) {
        this.id = id;
        this.rateA = rateA;
        this.rateB = rateB;
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public String getResult() {
        return result;
    }

    public double getRate() {
        switch (result) {
            case "A" -> {
                return rateA;
            }
            case "B" -> {
                return rateB;
            }
        }
        return 1;
    }
}
