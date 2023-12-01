package oop23_1010.types;

public enum SkinType {

    LIGHT("white", "Light Mode", 0),

    DARK("black", "Dark Mode", 15);

    private String color;

    private String description;

    public Integer cost;

    SkinType(final String color, final String descr, final Integer cost) {
        this.color = color;
        this.description = descr;
        this.cost = cost;
    }

    public String getColor() {
        return this.color;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getCost() {
        return this.cost;
    }
}