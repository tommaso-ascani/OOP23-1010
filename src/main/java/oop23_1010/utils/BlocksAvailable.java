package oop23_1010.utils;

import java.util.ArrayList;

public class BlocksAvailable<E> extends ArrayList<ShapeBlock>{

    public Boolean checkIfBlocksCanBePlaced(GameGrid<GridBlock> grid, Integer gridsize) {

        Integer targetX;
        Integer targetY;
        ArrayList<GridBlock> toFill = new ArrayList<>();

        for (ShapeBlock block : this) {
            for (GridBlock elem : grid) {

                targetX = elem.getGridX();
                targetY = elem.getGridY();

                toFill = new ArrayList<>();

                toFill.clear();
                for(int y=targetY; y<targetY+block.getHeight(); y++){
                    if(y >= gridsize){break;}
                    for(int x=targetX; x<targetX+block.getWidth(); x++){
                        if(x >= gridsize){break;}
                        if(!grid.getElement(x, y).getFill()){
                            toFill.add(grid.getElement(x, y));
                        }else{
                            toFill.clear();
                            break;
                        }
                    }
                }
                if(toFill.size() == block.getWidth()*block.getHeight()){break;}
            }
            
            if (toFill.size() == block.getWidth() * block.getHeight()) {
                return true;
            }
        }
        return false;
    }    
}
