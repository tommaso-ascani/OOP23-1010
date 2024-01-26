package tenten.model.types;

import java.util.HashMap;

import javafx.util.Pair;

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

    private HashMap<Pair<Integer, Integer>, String> colors = new HashMap<>();

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
            final String name,
            final Integer cost,
            final Boolean purchased) {

        this.colorBackground = colorBackground;
        this.colorGrid = colorGrid;
        this.colors.put(new Pair<Integer, Integer>(BlockType.BLOCK_1_1.getWidth(), BlockType.BLOCK_1_1.getHeight()),
                color1x1);
        this.colors.put(new Pair<Integer, Integer>(BlockType.BLOCK_2_2.getWidth(), BlockType.BLOCK_2_2.getHeight()),
                color2x2);
        this.colors.put(new Pair<Integer, Integer>(BlockType.BLOCK_3_3.getWidth(), BlockType.BLOCK_3_3.getHeight()),
                color3x3);
        this.colors.put(new Pair<Integer, Integer>(BlockType.BLOCK_2_1.getWidth(), BlockType.BLOCK_2_1.getHeight()),
                color2x1);
        this.colors.put(new Pair<Integer, Integer>(BlockType.BLOCK_3_1.getWidth(), BlockType.BLOCK_3_1.getHeight()),
                color3x1);
        this.colors.put(new Pair<Integer, Integer>(BlockType.BLOCK_4_1.getWidth(), BlockType.BLOCK_4_1.getHeight()),
                color4x1);
        this.colors.put(new Pair<Integer, Integer>(BlockType.BLOCK_5_1.getWidth(), BlockType.BLOCK_5_1.getHeight()),
                color5x1);
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

    public String getColor(int width, int height) {
        if (width > height) {
            return this.colors.get(new Pair<Integer, Integer>(width, height));
        } else {
            return this.colors.get(new Pair<Integer, Integer>(height, width));
        }
    }
}