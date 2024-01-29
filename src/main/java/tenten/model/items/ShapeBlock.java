package tenten.model.items;

import javafx.geometry.Bounds;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import tenten.common.Movement;
import tenten.common.utils.ThemeUtils;
import tenten.model.types.BlockType;

/**
 * Class that extends Path, represent a draggable object that has to be place in
 * the grid.
 */
public final class ShapeBlock extends GridPane {

    private final BlockType type;

    private final int gridCellSize;

    private Bounds bounds;

    private double gridWidth;

    private double gridHeight;

    /**
     * Initialize new ShapeBlock object.
     * 
     * @param type         BlockType
     * @param gridCellSize gridCellSize
     */
    public ShapeBlock(final BlockType type, final int gridCellSize) {
        this.type = type;
        this.gridCellSize = gridCellSize;

        this.gridWidth = 0;
        this.gridHeight = 0;

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
        for (int xx = 0; xx < type.getWidth(); xx++) {
            for (int yy = 0; yy < type.getHeight(); yy++) {
                if(!type.getL_type() || (type.getL_type() && (xx == 0 || yy == type.getWidth()-1))){
                    GridBlock gridBlock = new GridBlock(xx, yy, null, ThemeUtils.getSelectedTheme().getColorGrid());
                    gridBlock.setPrefHeight(this.gridCellSize);
                    gridBlock.setPrefWidth(this.gridCellSize);
                    gridBlock.setStyle(
                            "-fx-background-color: " + type.getColor()
                                + "; -fx-border-width: 2; -fx-border-radius: 3; -fx-border-insets: -2");
                    this.add(gridBlock, xx, yy);
                }
                if (xx == 0) {
                    this.gridHeight = this.gridHeight + this.gridCellSize;
                }
            }

            this.gridWidth = this.gridWidth + this.gridCellSize;
        }

        this.setStyle("-fx-vgap: 5; -fx-hgap: 5; ");
        Movement.makeDraggable(this);
        this.gridWidth = this.gridWidth + 5 * (this.type.getWidth() - 1);
        this.gridHeight = this.gridHeight + 5 * (this.type.getHeight() - 1);
    }

    /**
     * Relocate ShapeBlock to start position.
     */
    public void returnToStart() {
        this.setTranslateX(0);
        this.setTranslateY(0);
    }

    public void center(Pane pane) {
        this.bounds = this.sceneToLocal(this.getBoundsInLocal());
        this.relocate((pane.getPrefWidth() - this.gridWidth) / 2, (pane.getPrefHeight() - this.gridHeight) / 2);
    }
}
