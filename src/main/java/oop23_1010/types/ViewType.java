package oop23_1010.types;

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

    public String getPath() {
        return PREFIX + this.file + EXTENSION;
    }
}
