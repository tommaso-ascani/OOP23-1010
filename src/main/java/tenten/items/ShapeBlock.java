package tenten.items;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import tenten.Movement;
import tenten.types.BlockType;
import tenten.utils.ThemeUtils;

/**
 * Class that extends Path, represent a draggable object that has to be place in
 * the grid.
 */
public final class ShapeBlock extends Path {

    private static final Integer SIZE_CHECKSTYLE_ERROR = 5;

    private final BlockType type;
    private final GameGrid<GridBlock> grid;
    private final Pane pane;

    private final Integer width;
    private final Integer height;

    private final String color;

    private final BlocksAvailable<ShapeBlock> blocksAvalaible;

    private Bounds bounds;

    /**
     * Initialize new ShapeBlock object.
     * 
     * @param type
     * @param pane
     * @param grid
     * @param blocksAvalaible
     */
    public ShapeBlock(final BlockType type,
            final Pane pane,
            final GameGrid<GridBlock> grid,
            final BlocksAvailable<ShapeBlock> blocksAvalaible) {

        this.bounds = this.localToScene(this.getBoundsInLocal());
        this.type = type;
        this.grid = grid;
        this.pane = pane;
        this.blocksAvalaible = blocksAvalaible;

        switch (type) {
            case BLOCK_1_1:
                this.width = 1;
                this.height = 1;
                this.color = ThemeUtils.getSelectedTheme().getColor1x1();
                break;
            case BLOCK_1_2:
                this.width = 1;
                this.height = 2;
                this.color = ThemeUtils.getSelectedTheme().getColor2x1or1x2();
                break;
            case BLOCK_1_3:
                this.width = 1;
                this.height = 3;
                this.color = ThemeUtils.getSelectedTheme().getColor3x1or1x3();
                break;
            case BLOCK_1_4:
                this.width = 1;
                this.height = 4;
                this.color = ThemeUtils.getSelectedTheme().getColor4x1or1x4();
                break;
            case BLOCK_1_5:
                this.width = 1;
                this.height = ShapeBlock.SIZE_CHECKSTYLE_ERROR;
                this.color = ThemeUtils.getSelectedTheme().getColor5x1or1x5();
                break;
            case BLOCK_2_1:
                this.width = 2;
                this.height = 1;
                this.color = ThemeUtils.getSelectedTheme().getColor2x1or1x2();
                break;
            case BLOCK_2_2:
                this.width = 2;
                this.height = 2;
                this.color = ThemeUtils.getSelectedTheme().getColor2x2();
                break;
            case BLOCK_3_1:
                this.width = 3;
                this.height = 1;
                this.color = ThemeUtils.getSelectedTheme().getColor3x1or1x3();
                break;
            case BLOCK_3_3:
                this.width = 3;
                this.height = 3;
                this.color = ThemeUtils.getSelectedTheme().getColor3x3();
                break;
            case BLOCK_4_1:
                this.width = 4;
                this.height = 1;
                this.color = ThemeUtils.getSelectedTheme().getColor4x1or1x4();
                break;
            case BLOCK_5_1:
                this.width = ShapeBlock.SIZE_CHECKSTYLE_ERROR;
                this.height = 1;
                this.color = ThemeUtils.getSelectedTheme().getColor5x1or1x5();
                break;
            default:
                this.height = 0;
                this.width = 0;
                this.color = "white";
                break;
        }

        this.generateBlock();
        pane.getChildren().addAll(this);
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
     * Method to get the pane on which is located.
     * 
     * @return Pane.
     */
    public Pane getPane() {
        return this.pane;
    }

    /**
     * Method to get the width.
     * 
     * @return Width.
     */
    public Integer getWidth() {
        return this.width;
    }

    /**
     * Method to get the height.
     * 
     * @return Height.
     */
    public Integer getHeight() {
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
    public Integer getTriggerX() {
        this.bounds = this.localToScene(this.getBoundsInLocal());
        final Double tempX = this.bounds.getMinX();
        return tempX.intValue() + (grid.getGridCellSize() / 2);
    }

    /**
     * Method to get center Y coordinate.
     * 
     * @return Center Y coordinate.
     */
    public Integer getTriggerY() {
        this.bounds = this.localToScene(this.getBoundsInLocal());
        final Double tempY = this.bounds.getMinY();
        return tempY.intValue() + (grid.getGridCellSize() / 2);
    }

    /**
     * Method to generate the new block.
     */
    private void generateBlock() {
        Integer x;
        Integer y;

        for (x = 0; x < this.width; x++) {
            for (y = 0; y < this.height; y++) {
                this.getElements().addAll(
                        new MoveTo(x * (this.grid.getGridCellSize() + 3) + this.grid.getGridCellSize(),
                                y * (this.grid.getGridCellSize() + 3) + this.grid.getGridCellSize()),

                        new LineTo(x * (this.grid.getGridCellSize() + 3) + this.grid.getGridCellSize(),
                                y * (this.grid.getGridCellSize() + 3) + this.grid.getGridCellSize() * 2),
                        new LineTo(x * (this.grid.getGridCellSize() + 3) + this.grid.getGridCellSize() * 2,
                                y * (this.grid.getGridCellSize() + 3) + this.grid.getGridCellSize() * 2),
                        new LineTo(x * (this.grid.getGridCellSize() + 3) + this.grid.getGridCellSize() * 2,
                                y * (this.grid.getGridCellSize() + 3) + this.grid.getGridCellSize()),
                        new LineTo(x * (this.grid.getGridCellSize() + 3) + this.grid.getGridCellSize(),
                                y * (this.grid.getGridCellSize() + 3) + this.grid.getGridCellSize()),

                        new ClosePath());
            }
        }

        this.relocate((this.pane.getPrefWidth()
                - (this.getBoundsInParent().getMaxX() - this.getBoundsInParent().getMinX())) / 2,
                (this.pane.getPrefHeight()
                        - (this.getBoundsInParent().getMaxY() - this.getBoundsInParent().getMinY())) / 2);

        this.setStyle("-fx-fill: " + this.color + "; -fx-stroke-width: 0");
        this.setAccessibleText(this.color);
        this.blocksAvalaible.add(this);
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
