package oop23_1010.view;

public enum ViewType {

    HOME("HomeView"),

    SETTINGS("SettingsView"),

    SHOP("ShopView"),

    GAME("GameView");

    private static final String PREFIX = "layouts/";
    private static final String EXTENSION = ".fxml";
    private String file;

    ViewType(final String s) {
        this.file = s;
    }

    public String getPath() {
        return PREFIX + this.file + EXTENSION;
    }
}
