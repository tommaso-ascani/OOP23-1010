package oop23_1010.utils;

import oop23_1010.types.ThemeType;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import javafx.util.Pair;

public final class ThemeUtils {

    private static ThemeType selectedSkin;

    public static void setSelectedSkin(ThemeType newSkin) {
        selectedSkin = newSkin;
    }

    public static ThemeType getSelectedSkin() {
        return selectedSkin;
    }

    public static void saveSelectedSkin() {
        try {
            JsonUtils.addElement(new Pair<String, Object>(JsonUtils.SELECTED_SKIN, selectedSkin),
                    JsonUtils.GAME_DATA_FILE);
        } catch (IOException exc) {
            System.err.println("Theme Utils - Error on selected skin saving!");
        }
    }

    public static void loadSelectedSkin() {
        try {
            String selecSkin = (String) JsonUtils.loadData(JsonUtils.SELECTED_SKIN, JsonUtils.GAME_DATA_FILE);
            for (ThemeType skinType : ThemeType.values()) {
                if (skinType.name().equals(selecSkin)) {
                    selectedSkin = skinType;
                }
            }
        } catch (IOException exc) {
            System.err.println("Theme Utils - Error on selected skin loading!");
        }
    }

    public static void saveThemes() {

        JSONArray temp = new JSONArray();

        for (ThemeType skin : ThemeType.values()) {
            JSONObject skinObject = new JSONObject();
            skinObject.put("name", skin.name());
            skinObject.put("purchased", skin.getPurchased());
            temp.put(skinObject);
        }

        try {
            JsonUtils.addElement(new Pair<String, Object>(JsonUtils.SKINS, temp), JsonUtils.GAME_DATA_FILE);
        } catch (IOException exc) {
            System.err.println("Theme Utils - Error on themes saving!");
        }
    }

    public static JSONArray getSkins() {

        try {
            return JsonUtils.loadDataArray(JsonUtils.SKINS, JsonUtils.GAME_DATA_FILE);
        } catch (IOException exc) {
            System.err.println("Theme Utils - Error on skin getting!");
        }
        return null;
    }
}
