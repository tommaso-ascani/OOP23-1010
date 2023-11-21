package oop23_1010.view.gameView;

import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import oop23_1010.utils.Block;
import oop23_1010.utils.BlockGenerator;
import oop23_1010.utils.BlockType;
import oop23_1010.view.ViewImpl;

public class GameView extends ViewImpl {

    private int gridSize;

    private int gridPaneWidthHeight;

    private int gridCellSize;

    private final int GAP_GRID_PANE = 5;

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
            this.gridCellSize = 45;
        }
        if (this.gridSize == 10) {
            this.gridCellSize = 35;
        }
        if (this.gridSize == 15) {
            this.gridCellSize = 30;
        }
        if (this.gridSize == 20) {
            this.gridCellSize = 25;
        }
        this.gridPane.setStyle(
                "-fx-vgap: " + GAP_GRID_PANE + "; -fx-hgap: " + GAP_GRID_PANE
                        + "; -fx-background-color: black; -fx-border-insets: 5; -fx-border-width: 5; -fx-border-color: black;");
        for (int ColumnIndex = 0; ColumnIndex < this.gridSize; ColumnIndex++) {
            for (int RowIndex = 0; RowIndex < this.gridSize; RowIndex++) {
                AnchorPane aPane = new AnchorPane();
                aPane.setPrefHeight(gridCellSize);
                aPane.setPrefWidth(gridCellSize);
                aPane.setStyle(
                        "-fx-background-color: white; -fx-border-width: 2; -fx-border-color: black; -fx-border-radius: 3; -fx-border-insets: -2");

                this.gridPane.add(aPane, ColumnIndex, RowIndex);
            }
        }

        this.gridPaneWidthHeight = (this.gridSize * gridCellSize) + (this.gridSize - 1) * GAP_GRID_PANE;
        this.upperPane.setPrefSize(1280, ((720 - this.gridPaneWidthHeight) / 2));
        // this.upperPane.setStyle("-fx-background-color: green");

        this.bottomPane.setPrefSize(1280, ((720 - this.gridPaneWidthHeight) / 2));
        // this.bottomPane.setStyle("-fx-background-color: blue");

        this.leftPane.setPrefSize(((1280 - this.gridPaneWidthHeight) / 2), 720);
        // this.leftPane.setStyle("-fx-background-color: yellow");

        this.upLeftSpawn.setPrefSize(260, 260);
        this.upLeftSpawn.relocate((((1280 - this.gridPaneWidthHeight) / 2) - 260) / 2, (720 - 260 - 260 - 50) / 2);
        this.upLeftSpawn.setStyle("-fx-border-width: 5; -fx-border-color: black; -fx-border-radius: 10");

        this.downLeftSpawn.setPrefSize(260, 260);
        this.downLeftSpawn.relocate((((1280 - this.gridPaneWidthHeight) / 2) - 260) / 2,
                720 - 260 - (720 - 260 - 260 - 50) / 2);
        this.downLeftSpawn.setStyle("-fx-border-width: 5; -fx-border-color: black; -fx-border-radius: 10");

        this.rightPane.setPrefSize(((1280 - this.gridPaneWidthHeight) / 2), 720);
        // this.rightPane.setStyle("-fx-background-color: red");

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

        Block block1 = BlockGenerator.generateBlock(BlockType.BLOCK_2x1, gridCellSize);
        Block block2 = BlockGenerator.generateBlock(BlockType.BLOCK_1x3, gridCellSize);
        Block block3 = BlockGenerator.generateBlock(BlockType.BLOCK_2x2, gridCellSize);
        Block block4 = BlockGenerator.generateBlock(BlockType.BLOCK_1x5, gridCellSize);

        this.upLeftSpawn.getChildren().addAll(block1);
        this.upRightSpawn.getChildren().addAll(block2);
        this.downRightSpawn.getChildren().addAll(block3);
        this.downLeftSpawn.getChildren().addAll(block4);

        Group gruppo = new Group(this.mainPane, this.upLeftSpawn, this.downLeftSpawn, this.upRightSpawn,
                this.downRightSpawn);

        this.getStage().setScene(new Scene(gruppo));
        this.getStage().show();

        block1.setOnMouseReleased(e -> {
            Node node = this.getNodeIfUpLeftCornerInGrid(block1);
            if (this.getNodeIfUpLeftCornerInGrid(block1) != null) {
                switch (block1.getBlockType()) {
                    case BLOCK_2x1:
                        node.setStyle("-fx-background-color: gold");
                        int row = GridPane.getRowIndex(node);
                        int column = GridPane.getColumnIndex(node);
                        System.out.println(this.gridPane.getCellBounds(column, row));
                        System.out.println("next " + this.gridPane.getCellBounds(column + 1, row));
                        // Node nextNode = this.getNodeInGrid(this.gridPane.getCellBounds(column + 1,
                        // row));
                        // if (this.getNodeInGrid(this.gridPane.getCellBounds(column + 1, row)) != null)
                        // {

                        // }
                        for (Node a : this.gridPane.getChildren()) {

                        }

                        break;

                    default:
                        break;
                }
            }
        });

    }

    public void paintCell(Node node, Path block) {

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

    // public Node getNodeInGrid(Bounds bounds) {
    // Bounds boundsInScene = bounds.localToScene(bounds.getBoundsInLocal());
    // for (Node node : this.gridPane.getChildren()) {
    // if ((node.localToScene(node.getBoundsInLocal()).getMaxX() >
    // boundsInScene.getMinX()
    // && boundsInScene.getMinX() >
    // node.localToScene(node.getBoundsInLocal()).getMinX()) &&
    // (node.localToScene(node.getBoundsInLocal()).getMaxY() >
    // boundsInScene.getMinY()
    // && boundsInScene.getMinY() >
    // node.localToScene(node.getBoundsInLocal()).getMinY())) {
    // return node;
    // }
    // }
    // return null;
    // }
}
