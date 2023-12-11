package oop23_1010.types;

/**
 * Class used to hold all view's path.
 */
public enum ViewType {

    HOME("HomeView"),

    SETTINGS("SettingsView"),

    SHOP("ShopView"),

    GAME("GameView");

    private static final String PREFIX = "layouts/";
    private static final String EXTENSION = ".fxml";
    private String file;

    ViewType(final String string) {
        this.file = string;
    }

    /**
     * Method to return the views's path
     * 
     * @return String of the view's path
     */
    public String getPath() {
        return PREFIX + this.file + EXTENSION;
    }
}
