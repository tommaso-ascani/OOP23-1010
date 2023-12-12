package tenten.utils;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import javafx.util.Pair;
import tenten.types.ThemeType;

/**
 * Class that implements methods usefull to manage themes.
 */
public final class ThemeUtils {

    /**
     * Selected and active theme.
     */
    private static ThemeType selectedTheme;

    /**
     * Method to set a theme.
     * 
     * @param newTheme type of theme.
     */
    public static void setSelectedTheme(ThemeType newTheme) {
        selectedTheme = newTheme;
    }

    /**
     * Method to get the selected theme.
     * 
     * @return The selected theme.
     */
    public static ThemeType getSelectedTheme() {
        return selectedTheme;
    }

    /**
     * Method to save the selected theme.
     */
    public static void saveSelectedTheme() {
        try {
            JsonUtils.addElement(new Pair<String, Object>(JsonUtils.SELECTED_THEME, selectedTheme),
                    JsonUtils.GAME_DATA_FILE);
        } catch (IOException exc) {
            System.err.println("Theme Utils - Error on selected theme saving!");
        }
    }

    /**
     * Method to load the selected theme.
     */
    public static void loadSelectedTheme() {
        try {
            String selecTheme = (String) JsonUtils.loadData(JsonUtils.SELECTED_THEME, JsonUtils.GAME_DATA_FILE);
            for (ThemeType themeType : ThemeType.values()) {
                if (themeType.name().equals(selecTheme)) {
                    selectedTheme = themeType;
                }
            }
        } catch (IOException exc) {
            System.err.println("Theme Utils - Error on selected theme loading!");
        }
    }

    /**
     * Method to save themes state.
     */
    public static void saveThemes() {

        JSONArray temp = new JSONArray();

        for (ThemeType theme : ThemeType.values()) {
            JSONObject themeObject = new JSONObject();
            themeObject.put("name", theme.name());
            themeObject.put("purchased", theme.getPurchased());
            temp.put(themeObject);
        }

        try {
            JsonUtils.addElement(new Pair<String, Object>(JsonUtils.THEMES, temp), JsonUtils.GAME_DATA_FILE);
        } catch (IOException exc) {
            System.err.println("Theme Utils - Error on themes saving!");
        }
    }

    /**
     * Method to get all avalaible themes.
     * 
     * @return Array of themes.
     */
    public static JSONArray getThemes() {

        try {
            return JsonUtils.loadDataArray(JsonUtils.THEMES, JsonUtils.GAME_DATA_FILE);
        } catch (IOException exc) {
            System.err.println("Theme Utils - Error on theme getting!");
        }
        return null;
    }
}
