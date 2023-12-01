package oop23_1010.types;

public enum SkinType {

    LIGHT(  "white", 
            "lightgrey", 
            "black", 
            "black", 
            "peru", 
            "chartreuse", 
            "dodgerblue", 
            "gold", 
            "darkorange", 
            "lightcoral", 
            "firebrick", 
            "Light Mode", 
            0);

    // DARK("black", "Dark Mode", 15);

    // Colors

    private String color_background;
    private String color_grid;
    private String color_label;
    private String color_icon;
    private String color_1x1;
    private String color_2x2;
    private String color_3x3;
    private String color_2x1_1x2;
    private String color_3x1_1x3;
    private String color_4x1_1x4;
    private String color_5x1_1x5;

    private String description;
    private Integer cost;

    SkinType(   String color_background, 
                String color_grid, 
                String color_label,
                String color_icon,
                String color_1x1,
                String color_2x2,
                String color_3x3,
                String color_2x1_1x2,
                String color_3x1_1x3,
                String color_4x1_1x4,
                String color_5x1_1x5,
                String description,
                Integer cost) {

        this.color_background = color_background; 
        this.color_grid = color_grid; 
        this.color_label = color_label;
        this.color_icon = color_icon;
        this.color_1x1 = color_1x1;
        this.color_2x2 = color_2x2;
        this.color_3x3 = color_3x3;
        this.color_2x1_1x2 = color_2x1_1x2;
        this.color_3x1_1x3 = color_3x1_1x3;
        this.color_4x1_1x4 = color_4x1_1x4;
        this.color_5x1_1x5 = color_5x1_1x5;
        this.description = description;
        this.cost = cost;
    }

    // Getter

    public String getColor_background() {
        return color_background;
    }

    public String getColor_grid() {
        return color_grid;
    }

    public String getColor_label() {
        return color_label;
    }

    public String getColor_icon() {
        return color_icon;
    }

    public String getColor_1x1() {
        return color_1x1;
    }

    public String getColor_2x2() {
        return color_2x2;
    }

    public String getColor_3x3() {
        return color_3x3;
    }

    public String getColor_2x1_1x2() {
        return color_2x1_1x2;
    }

    public String getColor_3x1_1x3() {
        return color_3x1_1x3;
    }

    public String getColor_4x1_1x4() {
        return color_4x1_1x4;
    }

    public String getColor_5x1_1x5() {
        return color_5x1_1x5;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getCost() {
        return this.cost;
    }
}