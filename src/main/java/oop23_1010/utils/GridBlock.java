package oop23_1010.utils;

import javafx.geometry.Bounds;
import javafx.scene.layout.AnchorPane;

public class GridBlock extends AnchorPane {

    private Integer x;
    private Integer y;

    private String color;
    private String background_color;

    private Bounds bounds;

    // Constuctor

    public GridBlock(Integer x, Integer y, String color, String background_color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.background_color = background_color;
    }

    // Setter

    public void setFill(String color) {
        this.color = color;
    }

    // Getter

    public Integer getGridX() {
        return this.x;
    }

    public Integer getGridY() {
        return this.y;
    }

    public String getFill() {
        return this.color;
    }

    public String getBackground_color() {
        return background_color;
    }

    public Double getMinX() {
        bounds = this.localToScene(this.getBoundsInLocal());
        return bounds.getMinX();
    }

    public Double getMinY() {
        bounds = this.localToScene(this.getBoundsInLocal());
        return bounds.getMinY();
    }

    public Double getMaxX() {
        bounds = this.localToScene(this.getBoundsInLocal());
        return bounds.getMaxX();
    }

    public Double getMaxY() {
        bounds = this.localToScene(this.getBoundsInLocal());
        return bounds.getMaxY();
    }
}