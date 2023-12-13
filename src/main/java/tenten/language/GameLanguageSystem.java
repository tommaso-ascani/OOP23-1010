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

    private String language;

    private LanguageType languageType;

    private static final String DEFAULT_LANGUAGE = "ENG";

    /**
     * Inner class used to maintain the instance of GameLanguageSystem.
     */
    static class InnerGameLanguageSystem {

        /**
         * GameLanguageSystem istance.
         */
        static final GameLanguageSystem INSTANCE = new GameLanguageSystem();
    }

    /**
     * This method is used to get the instace of the class. It used the Singleton
     * pattern.
     * 
     * @return GameLanguageSystem.
     */
    public static GameLanguageSystem getInstance() {
        return InnerGameLanguageSystem.INSTANCE;
    }

    /**
     * Method that control and set the field language and languagetype if there is a
     * saved data of them
     * or set them by default.
     */
    public void checkLanguageData() {
        try {
            if (JsonUtils.ifDataExist(JsonUtils.LANGUAGE, JsonUtils.GAME_DATA_FILE)) {
                this.language = (String) JsonUtils.loadData(JsonUtils.LANGUAGE, JsonUtils.GAME_DATA_FILE);
                for (final LanguageType languageType : LanguageType.values()) {
                    if (languageType.name().equals(this.language)) {
                        this.languageType = languageType;
                    }
                }
            } else {
                JsonUtils.addElement(new Pair<String, Object>(JsonUtils.LANGUAGE, GameLanguageSystem.DEFAULT_LANGUAGE),
                        JsonUtils.GAME_DATA_FILE);
                this.language = GameLanguageSystem.DEFAULT_LANGUAGE;
                this.languageType = LanguageType.ENG;
            }
        } catch (IOException exc) {
            final Logger log = Logger.getLogger(GameLanguageSystem.class.getName());
            log.fine("Game Language System - Error on language loading!");
        }
    }

    /**
     * This method is used to get the selected language of the game.
     * 
     * @return String of the selected language.
     */
    public String getLanguage() {
        return this.language;
    }

    /**
     * This method is used to get the language type with all the string to set.
     * 
     * @return LanguageType of the selected language.
     */
    public LanguageType getLanguageType() {
        return this.languageType;
    }
}
