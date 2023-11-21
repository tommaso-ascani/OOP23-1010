package oop23_1010.utils;

import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import oop23_1010.controllers.Movement;

public class BlockGenerator {
    
    public static Path generateBlock(BlockType type, int gridCellSize) {
        switch(type) {
            case BLOCK_1x1:
                return classicBlock(1, 1, gridCellSize);
            case BLOCK_1x2:
                return classicBlock(1, 2, gridCellSize);
            case BLOCK_1x3:
                return classicBlock(1, 3, gridCellSize);
            case BLOCK_1x4:
                return classicBlock(1, 4, gridCellSize);
            case BLOCK_1x5:
                return classicBlock(1, 5, gridCellSize);
            case BLOCK_2x1:
                return classicBlock(2, 1, gridCellSize);
            case BLOCK_2x2:
                return classicBlock(2, 2, gridCellSize);
            case BLOCK_3x1:
                return classicBlock(3, 1, gridCellSize);
            case BLOCK_3x3:
                return classicBlock(3, 3, gridCellSize);
            case BLOCK_4x1:
                return classicBlock(4, 1, gridCellSize);
            case BLOCK_5x1:
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
        return null;
    }

    private static Path classicBlock(int width, int height, int gridCellSize) {
        Path path = new Path();

        Integer x;
        Integer y;

        for (x=0; x<width; x++) {
            for (y=0; y<height; y++) {
                path.getElements().addAll(
                    new MoveTo(10+(x*30), 10+(y*30)),

                    new LineTo(10+(x*30), 30+(y*30)),
                    new LineTo(30+(x*30), 30+(y*30)),
                    new LineTo(30+(x*30), 10+(y*30)),
                    new LineTo(10+(x*30), 10+(y*30)),

                    new ClosePath()
                );
            }
        }

        path.setStyle("-fx-fill: aqua; -fx-stroke-width: 3");
        Movement.makeDraggable(path);
        return path;
    }
}
