package oop23_1010.utils;

import java.util.ArrayList;

public class GameGrid<E> extends ArrayList<GridBlock> {

    private Integer gridSize;
    private Integer gridCellSize;

    public GameGrid(Integer gridSize) {
        this.gridSize = gridSize;
    }

    // Get up block

    public GridBlock getUpBlock(GridBlock block, Integer offset) {
        for (GridBlock x : this) {
            if (block.getGridX() == x.getGridX() && block.getGridY() - offset == x.getGridY()) {
                return x;
            }
        }
        return null;
    }

    // Get down block

    public GridBlock getDownBlock(GridBlock block, Integer offset) {
        for (GridBlock x : this) {
            if (block.getGridX() == x.getGridX() && block.getGridY() + offset == x.getGridY()) {
                return x;
            }
        }
        return null;
    }

    // Get right block

    public GridBlock getRightBlock(GridBlock block, Integer offset) {
        for (GridBlock x : this) {
            if (block.getGridX() + offset == x.getGridX() && block.getGridY() == x.getGridY()) {
                return x;
            }
        }
        return null;
    }

    // Get left block

    public GridBlock getLeftBlock(GridBlock block, Integer offset) {
        for (GridBlock x : this) {
            if (block.getGridX() - offset == x.getGridX() && block.getGridY() == x.getGridY()) {
                return x;
            }
        }
        return null;
    }

    public GridBlock getElement(Integer x, Integer y) {
        for (GridBlock elem : this) {
            if (elem.getGridX() == x && elem.getGridY() == y) {
                return elem;
            }
        }
        return null;
    }

    public Integer getGridSize() {
        return this.gridSize;
    }

    public void setGridCellSize(Integer cellSize) {
        this.gridCellSize = cellSize;
    }

    public Integer getGridCellSize() {
        return this.gridCellSize;
    }

    /**
     * Control if there are some lines (rows or columns) that are full,
     * and then remove them
     * 
     * @param score
     * @return
     */
    public Integer controlIfLinesCompleted(Integer score) {

        Integer scoreMultiplier = 1;

        for (ArrayList<GridBlock> line : getNumFullLines()) {
            for (GridBlock block : line) {
                block.setStyle("-fx-background-color: " + block.getBackground_color().getColor());
                block.setFill(null);
            }
            score = score + (line.size() * scoreMultiplier);
            scoreMultiplier++;
        }
        
        return score;
    }

    /**
     * Return the group of lines (rows or columns) that are full
     * 
     * @return
     */
    public ArrayList<ArrayList<GridBlock>> getNumFullLines() {

        ArrayList<ArrayList<GridBlock>> lines = new ArrayList<>();

        for (int y = 0; y < this.getGridSize(); y++) {
            if(this.isFull(getRow(y))) {
                lines.add(getRow(y));
            }
        }

        for (int x = 0; x < this.getGridSize(); x++) {
            if(this.isFull(getColumn(x))) {
                lines.add(getColumn(x));
            }
        }

        return lines;
    }

    /**
     * Return a boolean that indicates if the line (row or column) is full
     * 
     * @param list
     * @return
     */
    public Boolean isFull(ArrayList<GridBlock> list) {

        for (GridBlock block : list) {
            if(block.getFill() == null){
                return false;
            }
        }
        return true;
    }

    /**
     * Return the row at the passed index
     * 
     * @param index
     * @return
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
     * @return
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
