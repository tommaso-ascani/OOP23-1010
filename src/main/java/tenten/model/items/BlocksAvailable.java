package tenten.model.items;

import java.util.ArrayList;

import tenten.common.utils.ThemeUtils;

/**
 * Class that extends ArrayList of type ShapeBlock, it contains the four
 * placeble blocks.
 * 
 * @param <E> Shapeblock
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
     * @param grid GameGrid
     * @return Boolean
     */
    public Boolean checkIfBlocksCanBePlaced(final GameGrid<GridBlock> grid) {

        ArrayList<GridBlock> toBeFilled = new ArrayList<>();

        for (final ShapeBlock shapeBlock : this) {            
            for (final GridBlock elem : grid) {

                int targetX = elem.getGridX();
                int targetY = elem.getGridY();

                for (GridBlock gridBlock : shapeBlock.getBlocks()) {
                    int controlX = targetX + gridBlock.getGridX();
                    int controlY = targetY + gridBlock.getGridY();

                    if(controlX >= grid.getGridSize() || controlY >= grid.getGridSize() || controlX < 0 || controlY < 0){
                        toBeFilled.clear();
                        break;
                    }

                    if(grid.getElement(controlX, controlY).getBackgroundColor() == ThemeUtils.getSelectedTheme().getColorGrid()){
                        toBeFilled.add(grid.getElement(controlX, controlY));
                    }else{
                        toBeFilled.clear();
                        break;
                    }
                }
                if(!toBeFilled.isEmpty()){
                    return true;
                }
            }
        }
        return false;
    }
}
