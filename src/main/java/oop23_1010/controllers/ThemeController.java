package oop23_1010.controllers;

import oop23_1010.types.SkinType;
import oop23_1010.utils.JsonUtils;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;

import com.google.gson.JsonArray;

import javafx.util.Pair;

public final class ThemeController {

    private static SkinType selectedSkin;

    public static void setSelectedSkin(SkinType newSkin) {
        selectedSkin = newSkin;
    }

    public static SkinType getSelectedSkin() {
        return selectedSkin;
    }

    public static void saveSkins(ArrayList<SkinType> list) {
        try {
            JsonUtils.addElement(new Pair<String, Object>(JsonUtils.SKINS, list), JsonUtils.GAME_DATA_FILE);
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
