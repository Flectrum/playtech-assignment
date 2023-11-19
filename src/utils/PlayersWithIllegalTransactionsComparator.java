package utils;

import java.util.Comparator;

public class PlayersWithIllegalTransactionsComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}
