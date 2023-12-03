package oop23_1010.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.util.Pair;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtils {

    // Paths

    private static final String DATA_PATH = "src/main/resources/config/";

    // Files

    public static final String MATCH_FILE = "match";
    public static final String BEST_SCORE_FILE = "best_score";
    public static final String GAME_DATA_FILE = "game_data";

    // Match values

    public static final String MATCH_SCORE = "matchScore";
    public static final String MATCH_ON_GOING = "matchOnGoing";
    public static final String GRID_SIZE = "gridSize";
    public static final String GRID_COMPOSITION = "grid";

    // Game data values

    public static final String VOLUME = "volume";
    public static final String SELECTED_SKIN = "selectedSkin";
    public static final String SKINS = "skins";
    public static final String COINS = "coins";

    public static Object loadData(String data, String fileName) throws IOException {
        // Read file
        String file = Files.readString(Paths.get(DATA_PATH + fileName + ".json"));
        // Create new JSONObject with file data
        JSONObject json = new JSONObject(file);
        // Return json given data
        return json.get(data);
    }

    public static JSONArray loadDataArray(String data, String fileName) throws IOException {
        // Read file
        String file = Files.readString(Paths.get(DATA_PATH + fileName + ".json"));
        // Create new JSONObject with file data
        JSONObject json = new JSONObject(file);
        // Return jsonArray of the grid's cells occupied
        return (JSONArray) json.get(data);
    }

    public static JSONObject loadDatas(String fileName) throws IOException {
        // Read file
        String file = Files.readString(Paths.get(DATA_PATH + fileName + ".json"));
        // Create new JSONObject with file data
        JSONObject json = new JSONObject(file);
        // Return all json datas
        return json;
    }

    public static void saveMatchData(JSONObject json, String fileName) throws IOException {
        // Create directory if doesn't exist
        Files.createDirectories(Paths.get(DATA_PATH));
        // Write json on file
        Files.writeString(Paths.get(DATA_PATH + fileName + ".json"), json.toString(1));
    }

    public static void addElement(Pair<String, Object> temp, String fileName) throws IOException {
        // Create local variable
        JSONObject json;

        if (Files.exists(Paths.get(DATA_PATH + fileName + ".json"))
                && Files.readAllBytes(Paths.get(DATA_PATH + fileName + ".json")).length > 0) {
            // Get json element if exists
            json = loadDatas(fileName);
        } else {
            // Create new JSONObject if doesn't exist
            json = new JSONObject();
        }

        // Add new element
        json.put(temp.getKey(), temp.getValue());
        // Save data
        saveMatchData(json, fileName);
    }

    public static void removeElement(String data, String fileName) throws IOException {
        // Get json element if exists
        JSONObject json = loadDatas(fileName);
        // Add new element
        json.remove(data);

        if (json.isEmpty()) {
            // Delete json file if is empty
            flushJson(fileName);
        } else {
            // Save data
            saveMatchData(json, fileName);
        }
    }

    public static void flushJson(String fileName) throws IOException {
        // Delete json file
        Files.delete(Paths.get(DATA_PATH + fileName + ".json"));
    }

    public static Boolean jsonExist(String fileName) throws IOException {
        // Check if exist some data to load
        if (Files.exists(Paths.get(DATA_PATH + fileName + ".json"))
                && Files.readAllBytes(Paths.get(DATA_PATH + fileName + ".json")).length > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean ifDataExist(String data, String fileName) throws IOException {
        if(jsonExist(fileName)) {
            // Read file
            String file = Files.readString(Paths.get(DATA_PATH + fileName + ".json"));
            // Create new JSONObject with file data
            JSONObject json = new JSONObject(file);
            // Return true if json given data exist
            return json.has(data);
        } else {
            return false;
        }
    }
}
