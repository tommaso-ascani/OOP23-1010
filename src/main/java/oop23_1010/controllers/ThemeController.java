package oop23_1010.controllers;

import oop23_1010.types.ThemeType;
import oop23_1010.utils.JsonUtils;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import javafx.util.Pair;

public final class ThemeController {

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
        } catch (IOException e) {
            e.printStackTrace();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveSkins() {

        JSONArray temp = new JSONArray();

        for (ThemeType skin : ThemeType.values()) {
            JSONObject skinObject = new JSONObject();
            skinObject.put("name", skin.name());
            skinObject.put("purchased", skin.getPurchased());
            temp.put(skinObject);
        }

        try {
            JsonUtils.addElement(new Pair<String, Object>(JsonUtils.SKINS, temp), JsonUtils.GAME_DATA_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONArray getSkins() {

        try {
            return JsonUtils.loadDataArray(JsonUtils.SKINS, JsonUtils.GAME_DATA_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
