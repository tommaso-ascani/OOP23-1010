package tenten.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.util.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Class that manage saving/loading saved match data, best scores data and
 * option data from/to json file.
 */
public final class JsonUtils {

    private static final String JSON_EXTENSION_FILE_STRING = ".json";

    /**
     * Static field used to assign separator.
     */
    private static final String PROP_FILE_SEPARATOR = "file.separator";

    /**
     * Static field to get operation system separtor.
     */
    private static String separator = System.getProperty(PROP_FILE_SEPARATOR);

    /**
     * Static field used to create data path base.
     */
    public static final String DATA_PATH = "src" + separator + "main" + separator + "resources" + separator + "config"
            + separator;

    /**
     * Static field used to get the saved match's string file used as key in json
     * operation.
     */
    public static final String MATCH_FILE = "match";
    /**
     * Static field used to get the best score's string file used as key in
     * json operation.
     */
    public static final String BEST_SCORE_FILE = "best_score";
    /**
     * Static field used to get the game data's string file used as key in json
     * operation.
     */
    public static final String GAME_DATA_FILE = "game_data";

    // Match values

    /**
     * Static field used to save the match score's string as key in key/value pair
     * saved in json file.
     */
    public static final String MATCH_SCORE = "matchScore";
    /**
     * Static field used to save the match on going's string as key in key/value
     * pair
     * saved in json file.
     */
    public static final String MATCH_ON_GOING = "matchOnGoing";
    /**
     * Static field used to save the match grid size's string as key in key/value
     * pair saved in json file.
     */
    public static final String GRID_SIZE = "gridSize";
    /**
     * Static field used to save the grid's string as key in key/value pair
     * saved in json file.
     */
    public static final String GRID_COMPOSITION = "grid";

    // Game data values

    /**
     * Static field used to save the volume's string as key in key/value pair
     * saved in json file.
     */
    public static final String VOLUME = "volume";
    /**
     * Static field used to save the selected theme's string as key in key/value
     * pair saved in json file.
     */
    public static final String SELECTED_THEME = "selectedTheme";
    /**
     * Static field used to save the themes's string as key in key/value pair
     * saved in json file.
     */
    public static final String THEMES = "themes";
    /**
     * Static field used to save the coins score's string as key in key/value pair
     * saved in json file.
     */
    public static final String COINS = "coins";
    /**
     * Static field used to save the language's string as key in key/value pair
     * saved in json file.
     */
    public static final String LANGUAGE = "language";

    /**
     * Deafult constructor.
     */
    private JsonUtils() {
    }

    /**
     * Method to load a specific data from a json file.
     * 
     * @param data     to load.
     * @param fileName to search in.
     * @return The data founded.
     * @throws IOException exception
     */
    public static Object loadData(final String data, final String fileName) throws IOException {
        // Read file
        final String file = Files.readString(Paths.get(DATA_PATH + fileName + JsonUtils.JSON_EXTENSION_FILE_STRING));
        // Create new JSONObject with file data
        final JSONObject json = new JSONObject(file);
        // Return json given data
        return json.get(data);
    }

    /**
     * Method to load an array of data.
     * 
     * @param data     Array name.
     * @param fileName to search on.
     * @return The array founded.
     * @throws IOException exception
     */
    public static JSONArray loadDataArray(final String data, final String fileName) throws IOException {
        // Read file
        final String file = Files.readString(Paths.get(DATA_PATH + fileName + JsonUtils.JSON_EXTENSION_FILE_STRING));
        // Create new JSONObject with file data
        final JSONObject json = new JSONObject(file);
        // Return jsonArray of the grid's cells occupied
        return (JSONArray) json.get(data);
    }

    /**
     * Method to lo all data from a json file.
     * 
     * @param fileName to search on.
     * @return All data.
     * @throws IOException exception
     */
    public static JSONObject loadDatas(final String fileName) throws IOException {
        // Read file
        final String file = Files.readString(Paths.get(DATA_PATH + fileName + JsonUtils.JSON_EXTENSION_FILE_STRING));
        // Return all json datas
        return new JSONObject(file);
    }

    /**
     * Method to save match data.
     * 
     * @param json     to save.
     * @param fileName to save on.
     * @throws IOException exception
     */
    private static void saveMatchData(final JSONObject json, final String fileName) throws IOException {
        // Create directory if doesn't exist
        Files.createDirectories(Paths.get(DATA_PATH));
        // Write json on file
        Files.writeString(Paths.get(DATA_PATH + fileName + JsonUtils.JSON_EXTENSION_FILE_STRING), json.toString(1));
    }

    /**
     * Method to add or overwrite an element on a json file.
     * 
     * @param element  to add.
     * @param fileName to save on.
     * @throws IOException exception
     */
    public static void addElement(final Pair<String, Object> element, final String fileName) throws IOException {
        // Create local variable
        JSONObject json;

        if (Files.exists(Paths.get(DATA_PATH + fileName + JsonUtils.JSON_EXTENSION_FILE_STRING))
                && Files.readAllBytes(
                        Paths.get(DATA_PATH + fileName + JsonUtils.JSON_EXTENSION_FILE_STRING)).length > 0) {
            // Get json element if exists
            json = loadDatas(fileName);
        } else {
            // Create new JSONObject if doesn't exist
            json = new JSONObject();
        }

        // Add new element
        json.put(element.getKey(), element.getValue());
        // Save data
        saveMatchData(json, fileName);
    }

    /**
     * Method to remove an element from a json file.
     * 
     * @param data     to remove.
     * @param fileName to remove on.
     * @throws IOException exception
     */
    public static void removeElement(final String data, final String fileName) throws IOException {
        // Get json element if exists
        final JSONObject json = loadDatas(fileName);
        // Remove element
        json.remove(data);

        if (json.isEmpty()) {
            // Delete json file if is empty
            flushJson(fileName);
        } else {
            // Save data
            saveMatchData(json, fileName);
        }
    }

    /**
     * Method to flush all json file content.
     * 
     * @param fileName to flush.
     * @throws IOException exception
     */
    public static void flushJson(final String fileName) throws IOException {
        if (jsonExist(fileName)) {
            // Delete json file
            Files.delete(Paths.get(DATA_PATH + fileName + JsonUtils.JSON_EXTENSION_FILE_STRING));
        }
    }

    /**
     * Method to check if a json exist.
     * 
     * @param fileName to check.
     * @return If exist.
     * @throws IOException exception
     */
    public static Boolean jsonExist(final String fileName) throws IOException {
        // Check if exist some data to load
        if (Files.exists(Paths.get(DATA_PATH + fileName + JsonUtils.JSON_EXTENSION_FILE_STRING))
                && Files.readAllBytes(
                        Paths.get(DATA_PATH + fileName + JsonUtils.JSON_EXTENSION_FILE_STRING)).length > 0) {
            return true;
        }
        return false;
    }

    /**
     * Method to check if a specific data on a json file exist.
     * 
     * @param data     to check.
     * @param fileName to check on.
     * @return If exist.
     * @throws IOException exception
     */
    public static Boolean ifDataExist(final String data, final String fileName) throws IOException {
        if (jsonExist(fileName)) {
            // Read file
            final String file = Files
                    .readString(Paths.get(DATA_PATH + fileName + JsonUtils.JSON_EXTENSION_FILE_STRING));
            // Create new JSONObject with file data
            final JSONObject json = new JSONObject(file);
            // Return true if json given data exist
            return json.has(data);
        } else {
            return false;
        }
    }
}
