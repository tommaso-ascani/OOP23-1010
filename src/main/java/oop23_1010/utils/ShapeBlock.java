package oop23_1010.utils;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import oop23_1010.controllers.Movement;
import oop23_1010.controllers.ThemeController;
import oop23_1010.types.BlockType;

public class ShapeBlock extends Path {

    private BlockType type;
    private GameGrid<GridBlock> grid;
    private Pane pane;

    private Integer width;
    private Integer height;

    private Double widthPx;
    private Double heigthPx;

    private String color;

    private Double startX;
    private Double startY;

    private BlocksAvailable<ShapeBlock> blocksAvalaible;

    private Bounds bounds;

    // Constuctor

    public ShapeBlock(BlockType type, Pane pane, GameGrid<GridBlock> grid,
            BlocksAvailable<ShapeBlock> blocksAvalaible) {

        this.bounds = this.localToScene(this.getBoundsInLocal());
        this.type = type;
        this.grid = grid;
        this.pane = pane;
        this.blocksAvalaible = blocksAvalaible;

        switch (type) {
            case BLOCK_1x1:
                this.width = 1;
                this.height = 1;
                this.color = ThemeController.getSelectedSkin().getColor_1x1();
                break;
            case BLOCK_1x2:
                this.width = 1;
                this.height = 2;
                this.color = ThemeController.getSelectedSkin().getColor_2x1_1x2();
                break;
            case BLOCK_1x3:
                this.width = 1;
                this.height = 3;
                this.color = ThemeController.getSelectedSkin().getColor_3x1_1x3();
                break;
            case BLOCK_1x4:
                this.width = 1;
                this.height = 4;
                this.color = ThemeController.getSelectedSkin().getColor_4x1_1x4();
                break;
            case BLOCK_1x5:
                this.width = 1;
                this.height = 5;
                this.color = ThemeController.getSelectedSkin().getColor_5x1_1x5();
                break;
            case BLOCK_2x1:
                this.width = 2;
                this.height = 1;
                this.color = ThemeController.getSelectedSkin().getColor_2x1_1x2();
                break;
            case BLOCK_2x2:
                this.width = 2;
                this.height = 2;
                this.color = ThemeController.getSelectedSkin().getColor_2x2();
                break;
            case BLOCK_3x1:
                this.width = 3;
                this.height = 1;
                this.color = ThemeController.getSelectedSkin().getColor_3x1_1x3();
                break;
            case BLOCK_3x3:
                this.width = 3;
                this.height = 3;
                this.color = ThemeController.getSelectedSkin().getColor_3x3();
                break;
            case BLOCK_4x1:
                this.width = 4;
                this.height = 1;
                this.color = ThemeController.getSelectedSkin().getColor_4x1_1x4();
                break;
            case BLOCK_5x1:
                this.width = 5;
                this.height = 1;
                this.color = ThemeController.getSelectedSkin().getColor_5x1_1x5();
                break;
            default:
                break;
        }

        generateBlock();
        pane.getChildren().addAll(this);
    }

    // Getter

    public BlockType getType() {
        return this.type;
    }

    public Pane getPane() {
        return this.pane;
    }

    public Integer getWidth() {
        return this.width;
    }

    public Integer getHeight() {
        return this.height;
    }

    public String getColor() {
        return this.color;
    }

    public Integer getTriggerX() {
        this.bounds = this.localToScene(this.getBoundsInLocal());
        Double tempX = this.bounds.getMinX();
        return tempX.intValue() + (grid.getGridCellSize() / 2);
    }

    public Integer getTriggerY() {
        this.bounds = this.localToScene(this.getBoundsInLocal());
        Double tempY = this.bounds.getMinY();
        return tempY.intValue() + (grid.getGridCellSize() / 2);
    }

    // -------- Methods --------

    // Generate new shape that can be placed on the grid

    public void generateBlock() {
        Integer x;
        Integer y;

        for (x = 0; x < this.width; x++) {
            for (y = 0; y < this.height; y++) {
                this.getElements().addAll(
                        new MoveTo((x * (this.grid.getGridCellSize() + 3)) + this.grid.getGridCellSize(),
                                (y * (this.grid.getGridCellSize() + 3)) + this.grid.getGridCellSize()),

                        new LineTo((x * (this.grid.getGridCellSize() + 3)) + this.grid.getGridCellSize(),
                                (y * (this.grid.getGridCellSize() + 3)) + this.grid.getGridCellSize() * 2),
                        new LineTo((x * (this.grid.getGridCellSize() + 3)) + this.grid.getGridCellSize() * 2,
                                (y * (this.grid.getGridCellSize() + 3)) + this.grid.getGridCellSize() * 2),
                        new LineTo((x * (this.grid.getGridCellSize() + 3)) + this.grid.getGridCellSize() * 2,
                                (y * (this.grid.getGridCellSize() + 3)) + this.grid.getGridCellSize()),
                        new LineTo((x * (this.grid.getGridCellSize() + 3)) + this.grid.getGridCellSize(),
                                (y * (this.grid.getGridCellSize() + 3)) + this.grid.getGridCellSize()),

                        new ClosePath());
            }
        }

        this.widthPx = this.getBoundsInParent().getMaxX() - this.getBoundsInParent().getMinX();
        this.heigthPx = this.getBoundsInParent().getMaxY() - this.getBoundsInParent().getMinY();

        this.startX = (this.pane.getPrefWidth() - this.widthPx) / 2;
        this.startY = (this.pane.getPrefHeight() - this.heigthPx) / 2;

        this.relocate(this.startX, this.startY);

        this.setStyle("-fx-fill: " + this.color + "; -fx-stroke-width: 0");
        this.setAccessibleText(this.color);
        this.blocksAvalaible.add(this);
        Movement.makeDraggable(this);
    }

    // Reposition the item to start when it's not possibile to place it

    public void returnToStart() {
        this.setTranslateX(0);
        this.setTranslateY(0);
    }
}
