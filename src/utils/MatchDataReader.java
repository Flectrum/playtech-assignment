package utils;

import model.Match;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MatchDataReader {
    public static List<Match> readMatchesData(String file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        List<Match> matches = new ArrayList<>();
        String st;
        while ((st = bufferedReader.readLine()) != null) {
            String[] line = st.split(",");
            Match match = new Match(line[0], Double.parseDouble(line[1]), Double.parseDouble(line[2]), line[3]);
            matches.add(match);
        }
        return matches;
    }
}
