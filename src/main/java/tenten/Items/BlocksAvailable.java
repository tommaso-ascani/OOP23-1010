package tenten.Items;

import java.util.ArrayList;

/**
 * Class that extends ArrayList of type ShapeBlock, it contains the four
 * placeble blocks.
 * 
 * @param <E>
 */
public class BlocksAvailable<E> extends ArrayList<ShapeBlock> {

    /**
     * Method to check whether at least one remaining placeable block can be placed
     * in the grid. If at least one can be placed so it return true. False
     * otherwise.
     * 
     * @param grid
     * @return Boolean
     */
    public Boolean checkIfBlocksCanBePlaced(final GameGrid<GridBlock> grid) {

        Integer targetX;
        Integer targetY;
        ArrayList<GridBlock> toFill = new ArrayList<>();

        for (ShapeBlock block : this) {
            for (GridBlock elem : grid) {

                targetX = elem.getGridX();
                targetY = elem.getGridY();

                toFill = new ArrayList<>();

                toFill.clear();
                for (int y = targetY; y < targetY + block.getHeight(); y++) {
                    if (y >= grid.getGridSize()) {
                        break;
                    }
                    for (int x = targetX; x < targetX + block.getWidth(); x++) {
                        if (x >= grid.getGridSize()) {
                            break;
                        }
                        if (grid.getElement(x, y).getFill() == null) {
                            toFill.add(grid.getElement(x, y));
                        } else {
                            toFill.clear();
                            break;
                        }
                    }
                }
                if (toFill.size() == block.getWidth() * block.getHeight()) {
                    break;
                }
            }

            if (toFill.size() == block.getWidth() * block.getHeight()) {
                return true;
            }
        }
        return false;
    }
}
