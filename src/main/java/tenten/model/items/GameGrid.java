package tenten.model.items;

import java.util.ArrayList;
import java.util.List;

import tenten.common.sound.GameSoundSystem;
import tenten.common.utils.ThemeUtils;
import tenten.model.types.SoundType;

/**
 * Class that extends ArrayList of type GridBlock, used to mantain reference of
 * the various grid blocks in the cell.
 * 
 * @param <E> Gridblock
 */
public class GameGrid<E> extends ArrayList<GridBlock> {

    /**
     * Size of the game grid.
     */
    private final Integer gridSize;

    /**
     * Size of the game grid cell.
     */
    private Integer gridCellSize;

    /**
     * Define serialization id to avoid serialization related bugs.
     */
    public static final long serialVersionUID = 4328742;

    /**
     * Gamegrid constructor.
     * 
     * @param gridSize gridSize
     */
    public GameGrid(final Integer gridSize) {
        this.gridSize = gridSize;
    }

    /**
     * Method to get the specified block at (x,y).
     * 
     * @param x coordinate
     * @param y coordinate
     * @return GridBlock.
     */
    public GridBlock getElement(final Integer x, final Integer y) {
        for (final GridBlock elem : this) {
            if (elem.getGridX().equals(x) && elem.getGridY().equals(y)) {
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
     * @param cellSize cellSize
     */
    public void setGridCellSize(final Integer cellSize) {
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
     * @return Integer.
     */
    public Integer controlIfLinesCompleted() {
        Integer lines = 0;

        for (final List<GridBlock> line : getNumFullLines()) {
            for (final GridBlock block : line) {
                block.setBackgroundColor(ThemeUtils.getSelectedTheme().getColorGrid());
                block.setStyle("-fx-background-color: " + block.getBackgroundColor());
            }
            GameSoundSystem.getInstance().setAudioClip(SoundType.LINE_COMPLETED);
            GameSoundSystem.getInstance().playAudioClip();
            lines++;
        }

        return lines;
    }

    /**
     * Return the group of lines (rows or columns) that are full.
     * 
     * @return ArrayList
     */
    public List<List<GridBlock>> getNumFullLines() {

        final List<List<GridBlock>> lines = new ArrayList<>();

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
     * Return a boolean that indicates if the line (row or column) is full.
     * 
     * @param list of blocks
     * @return Boolean
     */
    public Boolean isFull(final List<GridBlock> list) {

        for (final GridBlock block : list) {
            if (block.getBackgroundColor() == ThemeUtils.getSelectedTheme().getColorGrid()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return the row at the passed index.
     * 
     * 
     * @param index of row to get
     * @return ArrayList
     */
    public List<GridBlock> getRow(final Integer index) {

        final List<GridBlock> row = new ArrayList<>();

        for (final GridBlock block : this) {
            if (block.getGridY().equals(index)) {
                row.add(block);
            }
        }
        return row;
    }

    /**
     * Return the column at the passed index.
     * 
     * 
     * @param index of column to get
     * @return ArrayList
     */
    public List<GridBlock> getColumn(final Integer index) {

        final List<GridBlock> column = new ArrayList<>();

        for (final GridBlock block : this) {
            if (block.getGridX().equals(index)) {
                column.add(block);
            }
        }
        return column;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object object) {
        final boolean ris = super.equals(object);
        final Boolean risObject = (Boolean) ris;
        return risObject.booleanValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int ris = super.hashCode();
        final Integer risObject = (Integer) ris;
        return risObject.intValue();
    }
}
