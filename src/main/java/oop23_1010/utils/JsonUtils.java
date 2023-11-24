package oop23_1010.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.util.Pair;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;

public class JsonUtils {

    private static final String DATA_PATH = "src/main/resources/config/";

    public static final String MATCH_SCORE = "matchScore";
    public static final String MATCH_ON_GOING = "matchOnGoing";
    public static final String GRID_SIZE = "gridSize";

    public static Object loadData(String data) throws IOException {
        // Read file
        String file = Files.readString(Paths.get(DATA_PATH + "match.json"));
        // Create new JSONObject with file data
        JSONObject json = new JSONObject(file);
        // Return json given data
        return json.get(data);
    }

    public static JSONArray loadGriglia(String data) throws IOException {
        // Read file
        String file = Files.readString(Paths.get(DATA_PATH + "match.json"));
        // Create new JSONObject with file data
        JSONObject json = new JSONObject(file);
        // Return jsonArray of the grid's cells occupied
        return (JSONArray) json.get(data);
    }

    public static JSONObject loadDatas() throws IOException {
        // Read file
        String file = Files.readString(Paths.get(DATA_PATH + "match.json"));
        // Create new JSONObject with file data
        JSONObject json = new JSONObject(file);
        // Return all json datas
        return json;
    }

    public static void saveMatchData(JSONObject json) throws IOException {
        // Create directory if doesn't exist
        Files.createDirectories(Paths.get(DATA_PATH));
        // Write json on file
        Files.writeString(Paths.get(DATA_PATH + "match.json"), json.toString(1));
    }

    public static void addElement(Pair<String, Object> temp) throws IOException {
        // Create local variable
        JSONObject json;

        if (Files.exists(Paths.get(DATA_PATH + "match.json"))
                && Files.readAllBytes(Paths.get(DATA_PATH + "match.json")).length > 0) {
            // Get json element if exists
            json = loadDatas();
        } else {
            // Create new JSONObject if doesn't exist
            json = new JSONObject();
        }

        // Add new element
        json.put(temp.getKey(), temp.getValue());
        // Save data
        saveMatchData(json);
    }

    public static void removeElement(String data) throws IOException {
        // Get json element if exists
        JSONObject json = loadDatas();
        // Add new element
        json.remove(data);

        if (json.isEmpty()) {
            // Delete json file if is empty
            flushJson();
        } else {
            // Save data
            saveMatchData(json);
        }
    }

    public static void flushJson() throws IOException {
        // Delete json file
        Files.delete(Paths.get(DATA_PATH + "match.json"));
    }
}
