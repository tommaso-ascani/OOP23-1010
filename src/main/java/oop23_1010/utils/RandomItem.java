package oop23_1010.utils;

import javafx.scene.layout.Pane;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import oop23_1010.controllers.Movement;
import oop23_1010.view.gameView.GameView;

public class RandomItem extends Path {

    private BlockType type;
    private GameView view;
    private Pane pane;

    private Integer width;
    private Integer height;

    private String color;

    // Constuctor

    public RandomItem(BlockType type, Pane pane, GameView view){

        this.type = type;
        this.view = view;
        this.pane = pane;

        switch (type) {
            case BLOCK_1x1:
                this.width = 1;
                this.height = 1;
                this.color = "peru";
                break;
            case BLOCK_1x2:
                this.width = 1;
                this.height = 2;
                this.color = "gold";
                break;        
            case BLOCK_1x3:
                this.width = 1;
                this.height = 3;
                this.color = "darkorange";
                break;
            case BLOCK_1x4:
                this.width = 1;
                this.height = 4;
                this.color = "lightcoral";
                break;
            case BLOCK_1x5:
                this.width = 1;
                this.height = 5;
                this.color = "firebrick";
                break;
            case BLOCK_2x1:
                this.width = 2;
                this.height = 1;
                this.color = "gold";
                break;
            case BLOCK_2x2:
                this.width = 2;
                this.height = 2;
                this.color = "chartreuse";
                break;
            case BLOCK_3x1:
                this.width = 3;
                this.height = 1;
                this.color = "darkorange";
                break;
            case BLOCK_3x3:
                this.width = 3;
                this.height = 3;
                this.color = "dodgerblue";
                break;
            case BLOCK_4x1:
                this.width = 4;
                this.height = 1;
                this.color = "lightcoral";
                break;
            case BLOCK_5x1:
                this.width = 5;
                this.height = 1;
                this.color = "firebrick";
                break;
            case BLOCK_L_BOTTOM_LEFT_2x2:
                break;
            case BLOCK_L_BOTTOM_LEFT_3X3:
                break;
            case BLOCK_L_BOTTOM_RIGHT_2x2:
                break;
            case BLOCK_L_BOTTOM_RIGHT_3X3:
                break;
            case BLOCK_L_TOP_LEFT_2x2:
                break;
            case BLOCK_L_TOP_LEFT_3X3:
                break;
            case BLOCK_L_TOP_RIGHT_2x2:
                break;
            case BLOCK_L_TOP_RIGHT_3X3:
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

    // Methods

    public void generateBlock() {
        Integer x;
        Integer y;

        for (x = 0; x < this.width; x++) {
            for (y = 0; y < this.height; y++) {
                this.getElements().addAll(
                        new MoveTo((x * (this.view.gridCellSize + 3)) + this.view.gridCellSize, (y * (this.view.gridCellSize + 3)) + this.view.gridCellSize),

                        new LineTo((x * (this.view.gridCellSize + 3)) + this.view.gridCellSize,
                                (y * (this.view.gridCellSize + 3)) + this.view.gridCellSize * 2),
                        new LineTo((x * (this.view.gridCellSize + 3)) + this.view.gridCellSize * 2,
                                (y * (this.view.gridCellSize + 3)) + this.view.gridCellSize * 2),
                        new LineTo((x * (this.view.gridCellSize + 3)) + this.view.gridCellSize * 2,
                                (y * (this.view.gridCellSize + 3)) + this.view.gridCellSize),
                        new LineTo((x * (this.view.gridCellSize + 3)) + this.view.gridCellSize, (y * (this.view.gridCellSize + 3)) + this.view.gridCellSize),

                        new ClosePath());
            }
        }
        
        this.setStyle("-fx-fill: " + this.color);
        this.setAccessibleText(this.color);
        Movement.makeDraggable(this);
    }
    
}
