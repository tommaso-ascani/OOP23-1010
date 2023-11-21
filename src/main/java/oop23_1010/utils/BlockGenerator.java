package oop23_1010.utils;

import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import oop23_1010.controllers.Movement;

public class BlockGenerator {

    public static Block generateBlock(BlockType type, int gridCellSize) {
        switch (type) {
            case BLOCK_1x1:
                return classicBlock(1, 1, gridCellSize, "peru", type);
            case BLOCK_1x2:
                return classicBlock(1, 2, gridCellSize, "gold", type);
            case BLOCK_1x3:
                return classicBlock(1, 3, gridCellSize, "darkorange", type);
            case BLOCK_1x4:
                return classicBlock(1, 4, gridCellSize, "lightcoral", type);
            case BLOCK_1x5:
                return classicBlock(1, 5, gridCellSize, "firebrick", type);
            case BLOCK_2x1:
                return classicBlock(2, 1, gridCellSize, "gold", type);
            case BLOCK_2x2:
                return classicBlock(2, 2, gridCellSize, "chartreuse", type);
            case BLOCK_3x1:
                return classicBlock(3, 1, gridCellSize, "darkorange", type);
            case BLOCK_3x3:
                return classicBlock(3, 3, gridCellSize, "dodgerblue", type);
            case BLOCK_4x1:
                return classicBlock(4, 1, gridCellSize, "lightcoral", type);
            case BLOCK_5x1:
                return classicBlock(5, 1, gridCellSize, "firebrick", type);
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
        return null;
    }

    private static Block classicBlock(int width, int height, int gridCellSize, String color, BlockType type) {
        Block path = new Block(type);

        Integer x;
        Integer y;

        for (x = 0; x < width; x++) {
            for (y = 0; y < height; y++) {
                path.getElements().addAll(
                        new MoveTo((x * (gridCellSize + 3)) + gridCellSize, (y * (gridCellSize + 3)) + gridCellSize),

                        new LineTo((x * (gridCellSize + 3)) + gridCellSize,
                                (y * (gridCellSize + 3)) + gridCellSize * 2),
                        new LineTo((x * (gridCellSize + 3)) + gridCellSize * 2,
                                (y * (gridCellSize + 3)) + gridCellSize * 2),
                        new LineTo((x * (gridCellSize + 3)) + gridCellSize * 2,
                                (y * (gridCellSize + 3)) + gridCellSize),
                        new LineTo((x * (gridCellSize + 3)) + gridCellSize, (y * (gridCellSize + 3)) + gridCellSize),

                        new ClosePath());
            }
        }
        path.setStyle("-fx-fill: " + color);
        path.setAccessibleText(color);
        Movement.makeDraggable(path);
        return path;
    }
}
