package tenten.items;

import java.util.ArrayList;

/**
 * Class that extends ArrayList of type ShapeBlock, it contains the four
 * placeble blocks.
 * 
 * @param <E>
 */
public class BlocksAvailable<E> extends ArrayList<ShapeBlock> {

    /**
     * Define serialization id to avoid serialization related bugs.
     */
    public static final long serialVersionUID = 4328743;

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
        ArrayList<GridBlock> toBeFilled = new ArrayList<>();

        for (final ShapeBlock block : this) {
            for (final GridBlock elem : grid) {

                targetX = elem.getGridX();
                targetY = elem.getGridY();

                toBeFilled = new ArrayList<>();

                toBeFilled.clear();
                for (int y = targetY; y < targetY + block.getHeight(); y++) {
                    if (y >= grid.getGridSize()) {
                        break;
                    }
                    for (int x = targetX; x < targetX + block.getWidth(); x++) {
                        if (x >= grid.getGridSize()) {
                            break;
                        }
                        if (grid.getElement(x, y).getFill() == null) {
                            toBeFilled.add(grid.getElement(x, y));
                        } else {
                            toBeFilled.clear();
                            break;
                        }
                    }
                }
                if (toBeFilled.size() == block.getWidth() * block.getHeight()) {
                    break;
                }
            }

            if (toBeFilled.size() == block.getWidth() * block.getHeight()) {
                return true;
            }
        }
        return false;
    }
}
