package oop23_1010.controllers;

import oop23_1010.types.SkinType;
import oop23_1010.utils.JsonUtils;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import javafx.util.Pair;

public final class ThemeController {

    private static SkinType selectedSkin;

    public static void setSelectedSkin(SkinType newSkin) {
        selectedSkin = newSkin;
    }

    public static SkinType getSelectedSkin() {
        return selectedSkin;
    }

    public static void saveSkins() {

        JSONArray temp = new JSONArray();

        for (SkinType skin : SkinType.values()) {
            JSONObject skinObject = new JSONObject();
            skinObject.put("name", skin.name());
            skinObject.put("purchased", skin.getPurchased());
            System.out.println(skinObject.toString());
            temp.put(skinObject);
            System.out.println(temp.toString());
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
