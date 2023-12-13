package tenten.language;

import java.io.IOException;

import javafx.util.Pair;
import tenten.types.LanguageType;
import tenten.utils.JsonUtils;
import java.util.logging.Logger;

/**
 * Class that represent the game language system.
 */
public final class GameLanguageSystem {

    private static GameLanguageSystem instance;

    private static String language;

    private static LanguageType languageType;

    private static final String DEFAULT_LANGUAGE = "ENG";

    /**
     * This method is used to get the instace of the class. It used the Singleton
     * pattern.
     * 
     * @return GameLanguageSystem.
     */
    public static GameLanguageSystem getInstance() {
        if (GameLanguageSystem.instance == null) {
            GameLanguageSystem.instance = new GameLanguageSystem();
        }
        try {
            if (JsonUtils.ifDataExist(JsonUtils.LANGUAGE, JsonUtils.GAME_DATA_FILE)) {
                GameLanguageSystem.language = (String) JsonUtils.loadData(JsonUtils.LANGUAGE, JsonUtils.GAME_DATA_FILE);
                for (final LanguageType languageType : LanguageType.values()) {
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
            final Logger log = Logger.getLogger(GameLanguageSystem.class.getName());
            log.fine("Game Language System - Error on language loading!");
        }
        return instance;
    }

    /**
     * This method is used to get the selected language of the game.
     * 
     * @return String of the selected language.
     */
    public String getLanguage() {
        return GameLanguageSystem.language;
    }

    /**
     * This method is used to get the language type with all the string to set.
     * 
     * @return LanguageType of the selected language.
     */
    public LanguageType getLanguageType() {
        return GameLanguageSystem.languageType;
    }
}
