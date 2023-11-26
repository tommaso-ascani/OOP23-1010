package oop23_1010.types;

public enum ColorType {

    PERU("peru"),

    GOLD("gold"),

    DARKORANGE("darkorange"),

    LIGHTCORAL("lightcoral"),

    FIREBRICK("firebrick"),

    CHARTREUSE("chartreuse"),

    DODGERBLUE("dodgerblue"),

    GREEN("green");

    private String color;

    ColorType(final String str) {
        this.color = str;
    }

    public String getColor() {
        return this.color;
    }

    public static ColorType get(String str) {
        for (ColorType colortype : ColorType.values()) {
            if (colortype.getColor().equals(str)) {
                return colortype;
            }
        }
        return null;
    }
}
