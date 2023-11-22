package oop23_1010.utils;

import java.util.ArrayList;

public class GameGrid<E> extends ArrayList<GridBlock> {

    private Integer gridSize;

    public GameGrid(Integer gridSize) {
        this.gridSize = gridSize;
    }

    // Get up block

    public GridBlock getUpBlock(GridBlock block, Integer offset) {
        for (GridBlock x : this) {
            if (block.getGridX() == x.getGridX() && block.getGridY()-offset == x.getGridY()) {
                return x;
            }
        }
        return null;
    }

    // Get down block

    public GridBlock getDownBlock(GridBlock block, Integer offset) {
        for (GridBlock x : this) {
            if (block.getGridX() == x.getGridX() && block.getGridY()+offset == x.getGridY()) {
                return x;
            }
        }
        return null;
    }
    
    // Get right block

    public GridBlock getRightBlock(GridBlock block, Integer offset) {
        for (GridBlock x : this) {
            if (block.getGridX()+offset == x.getGridX() && block.getGridY() == x.getGridY()) {
                return x;
            }
        }
        return null;
    }
    
    // Get left block

    public GridBlock getLeftBlock(GridBlock block, Integer offset) {
        for (GridBlock x : this) {
            if (block.getGridX()-offset == x.getGridX() && block.getGridY() == x.getGridY()) {
                return x;
            }
        }
        return null;
    }

    public GridBlock getElement(Integer x, Integer y) {
        for (GridBlock elem : this) {
            if(elem.getGridX() == x && elem.getGridY() == y){
                return elem;
            }
        }
        return null;
    }
}
