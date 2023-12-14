package tenten.model.types;

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
            "Soft",
            100,
            false);

    // Colors

    private String colorBackground;
    private String colorGrid;
    private String color1x1;
    private String color2x2;
    private String color3x3;
    private String color2x1or1x2;
    private String color3x1or1x3;
    private String color4x1or1x4;
    private String color5x1or1x5;

    private String name;
    private Integer cost;
    private Boolean purchased;

    ThemeType(final String colorBackground,
            final String colorGrid,
            final String color1x1,
            final String color2x2,
            final String color3x3,
            final String color2x1or1x2,
            final String color3x1or1x3,
            final String color4x1or1x4,
            final String color5x1or1x5,
            final String name,
            final Integer cost,
            final Boolean purchased) {

        this.colorBackground = colorBackground;
        this.colorGrid = colorGrid;
        this.color1x1 = color1x1;
        this.color2x2 = color2x2;
        this.color3x3 = color3x3;
        this.color2x1or1x2 = color2x1or1x2;
        this.color3x1or1x3 = color3x1or1x3;
        this.color4x1or1x4 = color4x1or1x4;
        this.color5x1or1x5 = color5x1or1x5;
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
     * Getter of 1x1 block color.
     * 
     * @return colorof 1x1 block
     */
    public String getColor1x1() {
        return color1x1;
    }

    /**
     * Getter of 2x2 block color.
     * 
     * @return colorof 2x2 block
     */
    public String getColor2x2() {
        return color2x2;
    }

    /**
     * Getter of 3x3 block color.
     * 
     * @return colorof 3x3 block
     */
    public String getColor3x3() {
        return color3x3;
    }

    /**
     * Getter of 2x1 block color.
     * 
     * @return colorof 2x1 block
     */
    public String getColor2x1or1x2() {
        return color2x1or1x2;
    }

    /**
     * Getter of 3x1 block color.
     * 
     * @return colorof 1x3 block
     */
    public String getColor3x1or1x3() {
        return color3x1or1x3;
    }

    /**
     * Getter of 4x1 block color.
     * 
     * @return colorof 4x1 block
     */
    public String getColor4x1or1x4() {
        return color4x1or1x4;
    }

    /**
     * Getter of 5x1 block color.
     * 
     * @return colorof 5x1 block
     */
    public String getColor5x1or1x5() {
        return color5x1or1x5;
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
}
