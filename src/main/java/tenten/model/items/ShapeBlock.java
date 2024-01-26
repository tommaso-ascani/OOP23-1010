package tenten.model.items;

import javafx.geometry.Bounds;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import tenten.common.Movement;
import tenten.common.utils.ThemeUtils;
import tenten.model.types.BlockType;

/**
 * Class that extends Path, represent a draggable object that has to be place in
 * the grid.
 */
public final class ShapeBlock extends Path {

    private final BlockType type;

    private final int width;
    private final int height;

    private final int gridCellSize;

    private final String color;

    private Bounds bounds;

    /**
     * Initialize new ShapeBlock object.
     * 
     * @param type BlockType
     * @param gridCellSize gridCellSize
     */
    public ShapeBlock(final BlockType type, final int gridCellSize) {

        this.bounds = this.localToScene(this.getBoundsInLocal());
        this.type = type;
        this.gridCellSize = gridCellSize;

        this.width = type.getWidth();
        this.height = type.getHeight();
        this.color = ThemeUtils.getSelectedTheme().getColor(this.width, this.height);

        this.generateBlock();
    }

    /**
     * Method to get block type.
     * 
     * @return Block type.
     */
    public BlockType getType() {
        return this.type;
    }

    /**
     * Method to get the width.
     * 
     * @return Width.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Method to get the height.
     * 
     * @return Height.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Method to get the ShapeBlock color.
     * 
     * @return ShapeBlock color.
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Method to get center X coordinate.
     * 
     * @return Center X coordinate.
     */
    public int getTriggerX() {
        this.bounds = this.localToScene(this.getBoundsInLocal());
        final Double tempX = this.bounds.getMinX();
        return tempX.intValue() + (this.gridCellSize / 2);
    }

    /**
     * Method to get center Y coordinate.
     * 
     * @return Center Y coordinate.
     */
    public int getTriggerY() {
        this.bounds = this.localToScene(this.getBoundsInLocal());
        final Double tempY = this.bounds.getMinY();
        return tempY.intValue() + (this.gridCellSize / 2);
    }

    /**
     * Method to generate the new block.
     */
    private void generateBlock() {
        int x;
        int y;

        for (x = 0; x < this.width; x++) {
            for (y = 0; y < this.height; y++) {
                this.getElements().addAll(
                        new MoveTo(x * (this.gridCellSize + 3) + this.gridCellSize,
                                y * (this.gridCellSize + 3) + this.gridCellSize),

                        new LineTo(x * (this.gridCellSize + 3) + this.gridCellSize,
                                y * (this.gridCellSize + 3) + this.gridCellSize * 2),
                        new LineTo(x * (this.gridCellSize + 3) + this.gridCellSize * 2,
                                y * (this.gridCellSize + 3) + this.gridCellSize * 2),
                        new LineTo(x * (this.gridCellSize + 3) + this.gridCellSize * 2,
                                y * (this.gridCellSize + 3) + this.gridCellSize),
                        new LineTo(x * (this.gridCellSize + 3) + this.gridCellSize,
                                y * (this.gridCellSize + 3) + this.gridCellSize),

                        new ClosePath());
            }
        }

        this.setStyle("-fx-fill: " + this.color + "; -fx-stroke-width: 0");
        this.setAccessibleText(this.color);
        Movement.makeDraggable(this);
    }

    /**
     * Relocate ShapeBlock to start position.
     */
    public void returnToStart() {
        this.setTranslateX(0);
        this.setTranslateY(0);
    }
}
