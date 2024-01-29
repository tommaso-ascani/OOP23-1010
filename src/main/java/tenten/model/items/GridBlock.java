package tenten.model.items;

import javafx.geometry.Bounds;
import javafx.scene.layout.AnchorPane;

/**
 * Class that extends AnchorPane, used to represent each grid square.
 */
public class GridBlock extends AnchorPane {

    private Integer x;
    private Integer y;

    private String backgroundColor;

    private Bounds bounds;

    /**
     * Method to initialize the fields x, y, color and background_color.
     * 
     * @param x coordinate
     * @param y coordinate
     * @param backgroundColor of gridBlock
     */
    public GridBlock(final Integer x, final Integer y, final String backgroundColor) {
        this.x = x;
        this.y = y;
        this.backgroundColor = backgroundColor;
    }

    public void setGridX(Integer x) {
        this.x = x;
    }

    public void setGridY(Integer y) {
        this.y = y;
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
     * Method to get the field backgroundColor.
     * 
     * @return String.
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Method to get the field backgroundColor.
     * 
     * @return String.
     */
    public void setBackgroundColor(final String backgroundColor) {
        this.backgroundColor = backgroundColor;
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

    /**
     * Method to get center X coordinate.
     * 
     * @return Center X coordinate.
     */
    public int getTriggerX(final int gridCellSize) {
        this.bounds = this.localToScene(this.getBoundsInLocal());
        final Double tempX = this.bounds.getMinX();
        return tempX.intValue() + (gridCellSize / 2);
    }

    /**
     * Method to get center Y coordinate.
     * 
     * @return Center Y coordinate.
     */
    public int getTriggerY(final int gridCellSize) {
        this.bounds = this.localToScene(this.getBoundsInLocal());
        final Double tempY = this.bounds.getMinY();
        return tempY.intValue() + (gridCellSize / 2);
    }
}
