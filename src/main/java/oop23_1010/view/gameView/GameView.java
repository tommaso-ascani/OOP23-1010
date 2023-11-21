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
import oop23_1010.utils.RandomItem;
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
                GridBlock aPane = new GridBlock(ColumnIndex, RowIndex);

                aPane.setPrefHeight(gridCellSize);
                aPane.setPrefWidth(gridCellSize);
                aPane.setStyle("-fx-background-color: white; -fx-border-width: 2; -fx-border-color: black; -fx-border-radius: 3; -fx-border-insets: -2");

                this.grid.add(aPane);
                this.gridPane.add(aPane, ColumnIndex, RowIndex);
            }
        }

        this.gridPaneWidthHeight = (this.gridSize * gridCellSize) + (this.gridSize - 1) * GAP_GRID_PANE;
        this.upperPane.setPrefSize(1280, ((720 - this.gridPaneWidthHeight) / 2));
        this.upperPane.setStyle("-fx-background-color: green");

        this.bottomPane.setPrefSize(1280, ((720 - this.gridPaneWidthHeight) / 2));
        this.bottomPane.setStyle("-fx-background-color: blue");

        this.leftPane.setPrefSize(((1280 - this.gridPaneWidthHeight) / 2), 720);
        this.leftPane.setStyle("-fx-background-color: yellow");

        this.upLeftSpawn.setPrefSize(260, 260);
        this.upLeftSpawn.relocate((((1280 - this.gridPaneWidthHeight) / 2) - 260) / 2, (720 - 260 - 260 - 50) / 2);
        this.upLeftSpawn.setStyle("-fx-border-width: 5; -fx-border-color: black; -fx-border-radius: 10");

        this.downLeftSpawn.setPrefSize(260, 260);
        this.downLeftSpawn.relocate((((1280 - this.gridPaneWidthHeight) / 2) - 260) / 2,
                720 - 260 - (720 - 260 - 260 - 50) / 2);
        this.downLeftSpawn.setStyle("-fx-border-width: 5; -fx-border-color: black; -fx-border-radius: 10");

        this.rightPane.setPrefSize(((1280 - this.gridPaneWidthHeight) / 2), 720);
        this.rightPane.setStyle("-fx-background-color: red");

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

        RandomItem block1 = new RandomItem(BlockType.BLOCK_1x3, upLeftSpawn, this);
        RandomItem block2 = new RandomItem(BlockType.BLOCK_2x1, upRightSpawn, this);
        RandomItem block3 = new RandomItem(BlockType.BLOCK_3x3, downRightSpawn, this);
        RandomItem block4 = new RandomItem(BlockType.BLOCK_5x1, downLeftSpawn, this);

        this.setBlockReadyToBePlaced(block1);
        this.setBlockReadyToBePlaced(block2);
        this.setBlockReadyToBePlaced(block3);
        this.setBlockReadyToBePlaced(block4);

        Group gruppo = new Group(this.mainPane, this.upLeftSpawn, this.downLeftSpawn, this.upRightSpawn,
                this.downRightSpawn);

        this.getStage().setScene(new Scene(gruppo));
        this.getStage().show();
    }

    public void setBlockReadyToBePlaced(RandomItem block) {

        block.setOnMouseReleased(e -> {
            GridBlock node = (GridBlock) this.getNodeIfUpLeftCornerInGrid(block);
            if (this.getNodeIfUpLeftCornerInGrid(block) != null) {

                Integer targetX = node.getGridX();
                Integer targetY = node.getGridY();

                Integer remainsX = block.getWidth();
                Integer remainsY = block.getHeight();

                for (GridBlock a : this.grid) {

                    if(a.getGridX() == targetX && a.getGridY() == targetY) {
                        a.setStyle("-fx-background-color: " + block.getColor());
                        if(remainsY>1) {
                            targetY++;
                            remainsY--;
                        }else if(remainsX>1){
                            targetY = node.getGridY();
                            remainsY = block.getHeight();

                            targetX++;
                            remainsX--;
                        }
                    }
                }
                Pane temp = block.getPane();
                temp.getChildren().remove(block);
            }
        });        
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
