package oop23_1010.view.gameView;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import oop23_1010.utils.BlockType;
import oop23_1010.utils.GridBlock;
import oop23_1010.utils.ShapeBlock;
import oop23_1010.view.ViewImpl;

public class GameView extends ViewImpl {

    public int gridSize;
    public int gridPaneWidthHeight;
    public int gridCellSize;
    public ArrayList<GridBlock> grid = new ArrayList<>();

    public final int GAP_GRID_PANE = 5;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Pane leftPane;

    @FXML
    private Pane upLeftSpawn;

    @FXML
    private Pane downLeftSpawn;

    @FXML
    private GridPane gridPane;

    @FXML
    private Pane rightPane;

    @FXML
    private Pane upRightSpawn;

    @FXML
    private Pane downRightSpawn;

    @FXML
    private Pane bottomPane;

    @FXML
    private Pane upperPane;

    @FXML
    private Label labelCoin;

    @FXML
    private Label labelScore;

    @Override
    public void init() {
        
        this.gridSize = HomeView.getGridSize();

        if (this.gridSize == 5) {
            gridCellSize = 45;
        }
        if (this.gridSize == 10) {
            gridCellSize = 35;
        }
        if (this.gridSize == 15) {
            gridCellSize = 30;
        }
        if (this.gridSize == 20) {
            gridCellSize = 25;
        }

        this.gridPane.setStyle(
                "-fx-vgap: " + GAP_GRID_PANE + "; -fx-hgap: " + GAP_GRID_PANE
                        + "; -fx-background-color: black; -fx-border-insets: 5; -fx-border-width: 5; -fx-border-color: black;");
        
        for (int ColumnIndex = 0; ColumnIndex < this.gridSize; ColumnIndex++) {
            for (int RowIndex = 0; RowIndex < this.gridSize; RowIndex++) {
                GridBlock aPane = new GridBlock(ColumnIndex, RowIndex, false);

                aPane.setPrefHeight(gridCellSize);
                aPane.setPrefWidth(gridCellSize);
                aPane.setStyle("-fx-background-color: white; -fx-border-width: 2; -fx-border-color: black; -fx-border-radius: 3; -fx-border-insets: -2");

                this.grid.add(aPane);
                this.gridPane.add(aPane, ColumnIndex, RowIndex);
            }
        }

        this.gridPaneWidthHeight = (this.gridSize * gridCellSize) + (this.gridSize - 1) * GAP_GRID_PANE;
        this.upperPane.setPrefSize(1280, ((720 - this.gridPaneWidthHeight) / 2));

        this.bottomPane.setPrefSize(1280, ((720 - this.gridPaneWidthHeight) / 2));

        this.leftPane.setPrefSize(((1280 - this.gridPaneWidthHeight) / 2), 720);

        this.upLeftSpawn.setPrefSize(260, 260);
        this.upLeftSpawn.relocate((((1280 - this.gridPaneWidthHeight) / 2) - 260) / 2, (720 - 260 - 260 - 50) / 2);
        this.upLeftSpawn.setStyle("-fx-border-width: 5; -fx-border-color: black; -fx-border-radius: 10");

        this.downLeftSpawn.setPrefSize(260, 260);
        this.downLeftSpawn.relocate((((1280 - this.gridPaneWidthHeight) / 2) - 260) / 2,
                720 - 260 - (720 - 260 - 260 - 50) / 2);
        this.downLeftSpawn.setStyle("-fx-border-width: 5; -fx-border-color: black; -fx-border-radius: 10");

        this.rightPane.setPrefSize(((1280 - this.gridPaneWidthHeight) / 2), 720);

        this.upRightSpawn.setPrefSize(260, 260);
        this.upRightSpawn.relocate((1280 - 260) - (((1280 - this.gridPaneWidthHeight) / 2) - 260) / 2,
                (720 - 260 - 260 - 50) / 2);
        this.upRightSpawn.setStyle("-fx-border-width: 5; -fx-border-color: black; -fx-border-radius: 10");

        this.downRightSpawn.setPrefSize(260, 260);
        this.downRightSpawn.relocate((1280 - 260) - (((1280 - this.gridPaneWidthHeight) / 2) - 260) / 2,
                720 - 260 - (720 - 260 - 260 - 50) / 2);
        this.downRightSpawn.setStyle("-fx-border-width: 5; -fx-border-color: black; -fx-border-radius: 10");

        this.labelCoin.relocate((((960 - this.gridPaneWidthHeight) / 2)),
                ((720 - 260 - 260 - 50) / 2) - 40);
        this.labelCoin.setText("Coin: 200");
        System.out.println("label coin lenght in px: " + this.labelCoin.getWidth());

        this.labelScore.relocate(700, ((720 - 260 - 260 - 50) / 2) - 40);

        ShapeBlock block1 = new ShapeBlock(BlockType.BLOCK_5x1, upLeftSpawn, this);
        ShapeBlock block2 = new ShapeBlock(BlockType.BLOCK_5x1, upRightSpawn, this);
        ShapeBlock block3 = new ShapeBlock(BlockType.BLOCK_1x5, downRightSpawn, this);
        ShapeBlock block4 = new ShapeBlock(BlockType.BLOCK_1x5, downLeftSpawn, this);

        this.setBlockReadyToBePlaced(block1);
        this.setBlockReadyToBePlaced(block2);
        this.setBlockReadyToBePlaced(block3);
        this.setBlockReadyToBePlaced(block4);

        Group gruppo = new Group(this.mainPane, this.upLeftSpawn, this.downLeftSpawn, this.upRightSpawn,
                this.downRightSpawn);

        this.getStage().setScene(new Scene(gruppo));
        this.getStage().show();
    }

    public void setBlockReadyToBePlaced(ShapeBlock block) {

        block.setOnMouseReleased(e -> {
            GridBlock node = (GridBlock) this.getNodeIfUpLeftCornerInGrid(block);
            if (this.getNodeIfUpLeftCornerInGrid(block) != null) {

                Integer targetX = node.getGridX();
                Integer targetY = node.getGridY();

                Integer remainsX = block.getWidth();
                Integer remainsY = block.getHeight();

                ArrayList<GridBlock> toFill = new ArrayList<>();

                for (GridBlock a : this.grid) {

                    if (a.getGridX() == targetX && a.getGridY() == targetY && a.getFill() == false) {
                        toFill.add(a);
                        if (remainsX>1) {

                            targetX++;
                            remainsX--;

                        } else if (remainsY>1){

                            targetX = node.getGridX();
                            remainsX = block.getWidth();

                            targetY++;
                            remainsY--;
                        }
                    }
                }

                if (toFill.size() == block.getWidth()*block.getHeight()) {
                    for (GridBlock x : toFill) {
                        x.setFill(true);
                        x.setStyle("-fx-background-color: " + block.getColor());
                        Pane pane = block.getPane();
                        pane.getChildren().remove(block);
                    }
                } else {
                    block.returnToStart();
                }

            }
            controlIfLinesCompleted();
        });        
    }

    private void controlIfLinesCompleted() {

        ArrayList<GridBlock> line;

        // Control if there are full rows
        
        for(int y=0; y < gridSize; y++){
            for(int x=0; x < gridSize; x++){
                line = new ArrayList<>();
                for (GridBlock a : this.grid) {
                    if(a.getGridY() == x && a.getFill()) {
                        line.add(a);
                    }
                }
                if (line.size() == gridSize){
                    for (GridBlock a : line) {
                        a.setFill(false);
                        a.setStyle("-fx-background-color: white");
                    } 
                }
            }
        }

        // Control if there are full columns

        for(int y=0; y < gridSize; y++){
            for(int x=0; x < gridSize; x++){
                line = new ArrayList<>();
                for (GridBlock a : this.grid) {
                    if(a.getGridX() == x && a.getFill()) {
                        line.add(a);
                    }
                }
                if (line.size() == gridSize){
                    for (GridBlock a : line) {
                        a.setFill(false);
                        a.setStyle("-fx-background-color: white");
                    } 
                }
            }
        }
    }

    public Node getNodeIfUpLeftCornerInGrid(Path block) {

        Bounds boundsInScene = block.localToScene(block.getBoundsInLocal());
        for (Node node : this.gridPane.getChildren()) {
            if ((node.localToScene(node.getBoundsInLocal()).getMaxX() > boundsInScene.getMinX()
                    && boundsInScene.getMinX() > node.localToScene(node.getBoundsInLocal()).getMinX()) &&
                    (node.localToScene(node.getBoundsInLocal()).getMaxY() > boundsInScene.getMinY()
                            && boundsInScene.getMinY() > node.localToScene(node.getBoundsInLocal()).getMinY())) {
                return node;
            }
        }
        return null;
    }
}
