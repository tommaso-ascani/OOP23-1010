package oop23_1010.language;

import java.io.IOException;

import javafx.util.Pair;
import oop23_1010.types.LanguageType;
import oop23_1010.utils.JsonUtils;

public final class GameLanguageSystem {

    private static GameLanguageSystem instance;

    private static String language;

    private static LanguageType languageType;

    private static final String DEFAULT_LANGUAGE = "ENG";

    public static GameLanguageSystem getInstance() {
        if (GameLanguageSystem.instance == null) {
            GameLanguageSystem.instance = new GameLanguageSystem();
        }
        try {
            if (JsonUtils.ifDataExist(JsonUtils.LANGUAGE, JsonUtils.GAME_DATA_FILE)) {
                GameLanguageSystem.language = (String) JsonUtils.loadData(JsonUtils.LANGUAGE, JsonUtils.GAME_DATA_FILE);
                for (LanguageType languageType : LanguageType.values()) {
                    if (languageType.name().equals(GameLanguageSystem.language)) {
                        GameLanguageSystem.languageType = languageType;
                    }
                }
            } else {
                JsonUtils.addElement(new Pair<String, Object>(JsonUtils.LANGUAGE, GameLanguageSystem.DEFAULT_LANGUAGE),
                        JsonUtils.GAME_DATA_FILE);
                GameLanguageSystem.language = GameLanguageSystem.DEFAULT_LANGUAGE;
                GameLanguageSystem.languageType = LanguageType.ENG;
            }
        } catch (IOException exc) {
            System.err.println("Game Language System - Error on language loading!");
        }
        return instance;
    }

    public String getLanguage() {
        return GameLanguageSystem.language;
    }

    public LanguageType getLanguageType() {
        return GameLanguageSystem.languageType;
    }

}