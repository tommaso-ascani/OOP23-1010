package oop23_1010.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;

public class JsonUtils {

    private static final String DATA_PATH = "src/main/resources/config/";

    public static final String MATCH_SCORE = "matchScore";
    public static final String MATCH_ON_GOING = "matchOnGoing";

    public static Object loadData(String data) throws IOException {
        String file = Files.readString(Paths.get(DATA_PATH + "match.json"));
        JSONObject json = new JSONObject(file);
        return json.get(data);
    }

    public static void saveMatchData(final boolean matchOnGoing, final int matchScore) throws IOException {
        // Create directory if doesn't exist
        Files.createDirectories(Paths.get(DATA_PATH));

        // Write match data as a json
        JSONObject matchData = new JSONObject();
        
        matchData.put("matchOnGoing", matchOnGoing);
        matchData.put("matchScore", matchScore);

        Files.writeString(Paths.get(DATA_PATH + "match.json"), matchData.toString(1));
    }
}
