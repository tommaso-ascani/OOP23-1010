package oop23_1010.utils;

import java.util.ArrayList;

import oop23_1010.sound.GameSoundSystem;
import oop23_1010.types.SoundType;

public class GameGrid<E> extends ArrayList<GridBlock> {

    private Integer gridSize;
    private Integer gridCellSize;

    public GameGrid(Integer gridSize) {
        this.gridSize = gridSize;
    }

    /**
     * Method to get the block above the gave one.
     * 
     * @param block
     * @param offset
     * @return GridBlock.
     */
    public GridBlock getUpBlock(GridBlock block, Integer offset) {
        for (GridBlock x : this) {
            if (block.getGridX() == x.getGridX() && block.getGridY() - offset == x.getGridY()) {
                return x;
            }
        }
        return null;
    }

    /**
     * Method to get the block under the gave one.
     * 
     * @param block
     * @param offset
     * @return GridBlock.
     */
    public GridBlock getDownBlock(GridBlock block, Integer offset) {
        for (GridBlock x : this) {
            if (block.getGridX() == x.getGridX() && block.getGridY() + offset == x.getGridY()) {
                return x;
            }
        }
        return null;
    }

    /**
     * Method to get the block on the right of the gave one.
     * 
     * @param block
     * @param offset
     * @return GridBlock.
     */
    public GridBlock getRightBlock(GridBlock block, Integer offset) {
        for (GridBlock x : this) {
            if (block.getGridX() + offset == x.getGridX() && block.getGridY() == x.getGridY()) {
                return x;
            }
        }
        return null;
    }

    /**
     * Method to get the block on the left of the gave one.
     * 
     * @param block
     * @param offset
     * @return GridBlock.
     */
    public GridBlock getLeftBlock(GridBlock block, Integer offset) {
        for (GridBlock x : this) {
            if (block.getGridX() - offset == x.getGridX() && block.getGridY() == x.getGridY()) {
                return x;
            }
        }
        return null;
    }

    /**
     * Method to get the specified block at (x,y).
     * 
     * @param x
     * @param y
     * @return GridBlock.
     */
    public GridBlock getElement(Integer x, Integer y) {
        for (GridBlock elem : this) {
            if (elem.getGridX() == x && elem.getGridY() == y) {
                return elem;
            }
        }
        return null;
    }

    /**
     * Method to get the size of the grid.
     * 
     * @return Integer.
     */
    public Integer getGridSize() {
        return this.gridSize;
    }

    /**
     * Method to set the cell's size.
     * 
     * @param cellSize
     */
    public void setGridCellSize(Integer cellSize) {
        this.gridCellSize = cellSize;
    }

    /**
     * Method to get the cell's size.
     * 
     * @return Integer.
     */
    public Integer getGridCellSize() {
        return this.gridCellSize;
    }

    /**
     * Control if there are some lines (rows or columns) that are full,
     * and then remove them.
     * 
     * @param score
     * @return Integer.
     */
    public Integer controlIfLinesCompleted() {
        Integer lines = 0;

        for (ArrayList<GridBlock> line : getNumFullLines()) {
            for (GridBlock block : line) {
                block.setStyle("-fx-background-color: " + block.getBackground_color());
                block.setFill(null);
            }
            GameSoundSystem.getInstance().setAudioClip(SoundType.LINE_COMPLETED);
            GameSoundSystem.getInstance().playAudioClip();
            lines++;
        }

        return lines;
    }

    /**
     * Return the group of lines (rows or columns) that are full
     * 
     * @return ArrayList
     */
    public ArrayList<ArrayList<GridBlock>> getNumFullLines() {

        ArrayList<ArrayList<GridBlock>> lines = new ArrayList<>();

        for (int y = 0; y < this.getGridSize(); y++) {
            if (this.isFull(getRow(y))) {
                lines.add(getRow(y));
            }
        }

        for (int x = 0; x < this.getGridSize(); x++) {
            if (this.isFull(getColumn(x))) {
                lines.add(getColumn(x));
            }
        }

        return lines;
    }

    /**
     * Return a boolean that indicates if the line (row or column) is full
     * 
     * @param list
     * @return Boolean
     */
    public Boolean isFull(ArrayList<GridBlock> list) {

        for (GridBlock block : list) {
            if (block.getFill() == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return the row at the passed index
     * 
     * @param index
     * @return ArrayList
     */
    public ArrayList<GridBlock> getRow(Integer index) {

        ArrayList<GridBlock> row = new ArrayList<>();

        for (GridBlock block : this) {
            if (block.getGridY() == index) {
                row.add(block);
            }
        }
        return row;
    }

    /**
     * Return the column at the passed index
     * 
     * @param index
     * @return ArrayList
     */
    public ArrayList<GridBlock> getColumn(Integer index) {

        ArrayList<GridBlock> column = new ArrayList<>();

        for (GridBlock block : this) {
            if (block.getGridX() == index) {
                column.add(block);
            }
        }
        return column;
    }
}
