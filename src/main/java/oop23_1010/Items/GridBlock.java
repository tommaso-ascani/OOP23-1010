package oop23_1010.Items;

import javafx.geometry.Bounds;
import javafx.scene.layout.AnchorPane;

/**
 * Class that extends AnchorPane, used to represent each grid square.
 */
public class GridBlock extends AnchorPane {

    private Integer x;
    private Integer y;

    private String color;
    private String background_color;

    private Bounds bounds;

    /**
     * Method to initialize the fields x, y, color and background_color.
     * 
     * @param x
     * @param y
     * @param color
     * @param background_color
     */
    public GridBlock(Integer x, Integer y, String color, String background_color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.background_color = background_color;
    }

    /**
     * Method to set the field color.
     * 
     * @param color
     */
    public void setFill(String color) {
        this.color = color;
    }

    /**
     * Method to get the field x.
     * 
     * @return Integer.
     */
    public Integer getGridX() {
        return this.x;
    }

    /**
     * Method to get the field y.
     * 
     * @return Integer.
     */
    public Integer getGridY() {
        return this.y;
    }

    /**
     * Method to get the field color.
     * 
     * @return String.
     */
    public String getFill() {
        return this.color;
    }

    /**
     * Method to get the field background_color.
     * 
     * @return String.
     */
    public String getBackground_color() {
        return background_color;
    }

    /**
     * Method to get the min x of field bounds.
     * 
     * @return Double.
     */
    public Double getMinX() {
        bounds = this.localToScene(this.getBoundsInLocal());
        return bounds.getMinX();
    }

    /**
     * Method to get the min y of field bounds.
     * 
     * @return Double.
     */
    public Double getMinY() {
        bounds = this.localToScene(this.getBoundsInLocal());
        return bounds.getMinY();
    }

    /**
     * Method to get the max x of field bounds.
     * 
     * @return Double.
     */
    public Double getMaxX() {
        bounds = this.localToScene(this.getBoundsInLocal());
        return bounds.getMaxX();
    }

    /**
     * Method to get the max y of field bounds.
     * 
     * @return Double.
     */
    public Double getMaxY() {
        bounds = this.localToScene(this.getBoundsInLocal());
        return bounds.getMaxY();
    }
}