package oop23_1010.utils;

import javafx.geometry.Bounds;
import javafx.scene.layout.AnchorPane;
import oop23_1010.types.ColorType;

public class GridBlock extends AnchorPane {

    private Integer x;
    private Integer y;

    private ColorType color;

    private Bounds bounds;

    // Constuctor

    public GridBlock(Integer x, Integer y, ColorType color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    // Setter

    public void setFill(ColorType state) {
        this.color = state;
    }

    // Getter

    public Integer getGridX() {
        return this.x;
    }

    public Integer getGridY() {
        return this.y;
    }

    public ColorType getFill() {
        return this.color;
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
