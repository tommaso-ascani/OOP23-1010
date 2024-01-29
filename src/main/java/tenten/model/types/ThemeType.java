package tenten.model.types;

import java.util.HashMap;

/**
 * Class used to hold all the themes.
 */
public enum ThemeType {

    /**
     * Item that contains all colors of light theme.
     */
    LIGHT("white",
            "darkgrey",
            "peru",
            "chartreuse",
            "dodgerblue",
            "gold",
            "darkorange",
            "lightcoral",
            "firebrick",
            "green",
            "red",
            "Light",
            0,
            true),

    /**
     * Item that contains all colors of dark theme.
     */
    DARK("#303030",
            "darkgrey",
            "peru",
            "chartreuse",
            "dodgerblue",
            "gold",
            "darkorange",
            "lightcoral",
            "firebrick",
            "green",
            "red",
            "Dark",
            10,
            false),

    /**
     * Item that contains all colors of soft theme.
     */
    SOFT("blanchedalmond",
            "darkgrey",
            "darkslateblue",
            "forestgreen",
            "cadetblue",
            "mediumpurple",
            "sandybrown",
            "lightsteelblue",
            "palevioletred",
            "green",
            "red",
            "Soft",
            100,
            false);

    // Colors

    private String colorBackground;
    private String colorGrid;

    private HashMap<String, String> colors = new HashMap<>();

    private String name;
    private Integer cost;
    private Boolean purchased;

    ThemeType(final String colorBackground,
            final String colorGrid,
            final String color1x1,
            final String color2x2,
            final String color3x3,
            final String color2x1,
            final String color3x1,
            final String color4x1,
            final String color5x1,
            final String color_L2x2,
            final String color_L3x3,
            final String name,
            final Integer cost,
            final Boolean purchased) {

        this.colorBackground = colorBackground;
        this.colorGrid = colorGrid;
        this.colors.put("color1x1", color1x1);
        this.colors.put("color2x2", color2x2);
        this.colors.put("color3x3", color3x3);
        this.colors.put("color2x1", color2x1);
        this.colors.put("color3x1", color3x1);
        this.colors.put("color4x1", color4x1);
        this.colors.put("color5x1", color5x1);
        this.colors.put("color_L2x2", color_L2x2);
        this.colors.put("color_L3x3", color_L3x3);
        this.name = name;
        this.cost = cost;
        this.purchased = purchased;
    }

    /**
     * Getter of color background.
     * 
     * @return colorBackground
     */
    public String getColorBackground() {
        return colorBackground;
    }

    /**
     * Getter of color grid.
     * 
     * @return colorGrid
     */
    public String getColorGrid() {
        return colorGrid;
    }

    /**
     * Getter of the name of theme.
     * 
     * @return the name of theme
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter of the cost of theme.
     * 
     * @return the cost of theme
     */
    public Integer getCost() {
        return this.cost;
    }

    /**
     * Getter of puchased of theme.
     * 
     * @return purchased of theme
     */
    public Boolean getPurchased() {
        return purchased;
    }

    /**
     * Setter of purchased of theme.
     * 
     * @param purch
     */
    private void setPurchased(final Boolean purch) {
        this.purchased = purch;
    }

    /**
     * Setter of purchased of theme.
     * 
     * @param purch condition
     */
    public void recallSetPurchased(final Boolean purch) {
        this.setPurchased(purch);
    }

    public String getColor(final String color) {
        return this.colors.get(color);
    }
}