package tenten.model.items;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import tenten.common.Movement;
import tenten.common.utils.RandomUtils;
import tenten.model.types.BlockType;
import java.util.ArrayList;

/**
 * Class that extends Path, represent a draggable object that has to be place in
 * the grid.
 */
public final class ShapeBlock extends GridPane {

    private final BlockType type;

    private final int gridCellSize;

    private double gridWidth;

    private double gridHeight;

    /**
     * Initialize new ShapeBlock object.
     * 
     * @param type         BlockType
     * @param gridCellSize gridCellSize
     */
    public ShapeBlock(final BlockType type, final int gridCellSize) {
        this.type = type;
        this.gridCellSize = gridCellSize;

        this.gridWidth = 0;
        this.gridHeight = 0;

        this.generateBlock();
    }

    /**
     * Method to get block type.
     * 
     * @return Block type.
     */
    public BlockType getType() {
        return this.type;
    }

    /**
     * Method to generate the new block.
     */
    private void generateBlock() {
        double rotation = RandomUtils.getRandomRotate();

        for (int xx = 0; xx < type.getWidth(); xx++) {
            for (int yy = 0; yy < type.getHeight(); yy++) {
                if(!type.getL_type() || (type.getL_type() && (xx == 0 || yy == type.getHeight()-1))){
                    GridBlock gridBlock = new GridBlock(xx, yy, type.getColor());

                    if(rotation == 90){
                        gridBlock.setGridX(-yy);
                        gridBlock.setGridY(xx);
                    }else if(rotation == 180){
                        gridBlock.setGridX(-xx);
                        gridBlock.setGridY(-yy);
                    }else if(rotation == 270){
                        gridBlock.setGridX(yy);
                        gridBlock.setGridY(-xx);
                    }

                    gridBlock.setPrefHeight(this.gridCellSize);
                    gridBlock.setPrefWidth(this.gridCellSize);
                    gridBlock.setStyle(
                            "-fx-background-color: " + type.getColor()
                                + "; -fx-border-width: 2; -fx-border-radius: 3; -fx-border-insets: -2");
                    this.add(gridBlock, xx, yy);
                }
                if (xx == 0) {
                    this.gridHeight = this.gridHeight + this.gridCellSize;
                }
            }
            this.gridWidth = this.gridWidth + this.gridCellSize;
        }

        this.setStyle("-fx-vgap: 5; -fx-hgap: 5; ");
        Movement.makeDraggable(this);
        this.gridWidth = this.gridWidth + 5 * (this.type.getWidth() - 1);
        this.gridHeight = this.gridHeight + 5 * (this.type.getHeight() - 1);

        this.setRotate(rotation);
    }

    /**
     * Relocate ShapeBlock to start position.
     */
    public void returnToStart() {
        this.setTranslateX(0);
        this.setTranslateY(0);
    }

    public void center(Pane pane) {
        this.relocate((pane.getPrefWidth() - this.gridWidth) / 2, (pane.getPrefHeight() - this.gridHeight) / 2);
    }

    public ArrayList<GridBlock> getBlocks() {
        ArrayList<GridBlock> list = new ArrayList<GridBlock>();

        for (Node node : this.getChildren()) {
            list.add((GridBlock) node);
        }

        return list;
    }
}
