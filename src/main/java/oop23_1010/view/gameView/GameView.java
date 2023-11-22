package oop23_1010.view.gameView;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import oop23_1010.utils.BlockType;
import oop23_1010.utils.GridBlock;
import oop23_1010.utils.ShapeBlock;
import oop23_1010.view.ViewImpl;
import oop23_1010.view.ViewSwitcher;

public class GameView extends ViewImpl {

    private int gridSize;
    public int gridCellSize;
    private ArrayList<GridBlock> grid = new ArrayList<>();

    private static final int GAP_GRID_PANE = 5;
    private static final int SPAWN_PANELS_WIDTH = 260;
    private static final int GAP_BETWEEN_SPAWN_PANELS = 40;
    private static final int PAUSE_PANEL_WIDTH = 400;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Pane leftPane, upLeftSpawn, downLeftSpawn;

    @FXML
    private GridPane gridPane;

    @FXML
    private Pane rightPane, upRightSpawn, downRightSpawn;

    @FXML
    private Pane bottomPane, upperPane, pausePane;

    @FXML
    private Label labelCoin, labelScore;

    @FXML
    private ImageView imagePause;

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

        this.createGridCells();

        this.setPanelsPrefSizes();

        this.setPanelsStyle();

        this.setObjectLocation();

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

        this.pausePane.setVisible(true);
        this.pausePane.setPrefSize(GameView.PAUSE_PANEL_WIDTH, GameView.PAUSE_PANEL_WIDTH);
        this.pausePane.setStyle(
                "-fx-background-color: white; -fx-border-width: 2; -fx-border-color: black; -fx-border-radius: 3;");
        this.pausePane.relocate((ViewSwitcher.getWindowWidth() - GameView.PAUSE_PANEL_WIDTH) / 2,
                (ViewSwitcher.getWindowHeight() - GameView.PAUSE_PANEL_WIDTH) / 2);
        this.pausePane.setOnMousePressed(e -> {
            System.out.println("pause panel");
            this.pausePane.setVisible(false);
            this.mainPane.setVisible(true);
            this.upLeftSpawn.setVisible(true);
        });

        Group gruppo = new Group(this.mainPane, this.upLeftSpawn, this.downLeftSpawn, this.upRightSpawn,
                this.downRightSpawn, this.pausePane);

        this.getStage().setScene(new Scene(gruppo));
        this.getStage().show();
    }

    /**
     * This method is used to set the listener when the mouse is released on the
     * block
     * 
     * @param block
     */
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
                        if (remainsX > 1) {

                            targetX++;
                            remainsX--;

                        } else if (remainsY > 1) {

                            targetX = node.getGridX();
                            remainsX = block.getWidth();

                            targetY++;
                            remainsY--;
                        }
                    }
                }

                if (toFill.size() == block.getWidth() * block.getHeight()) {
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

        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                line = new ArrayList<>();
                for (GridBlock a : this.grid) {
                    if (a.getGridY() == x && a.getFill()) {
                        line.add(a);
                    }
                }
                if (line.size() == gridSize) {
                    for (GridBlock a : line) {
                        a.setFill(false);
                        a.setStyle("-fx-background-color: white");
                    }
                }
            }
        }

        // Control if there are full columns

        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                line = new ArrayList<>();
                for (GridBlock a : this.grid) {
                    if (a.getGridX() == x && a.getFill()) {
                        line.add(a);
                    }
                }
                if (line.size() == gridSize) {
                    for (GridBlock a : line) {
                        a.setFill(false);
                        a.setStyle("-fx-background-color: white");
                    }
                }
            }
        }
    }

    /**
     * This method is used to get the node where the up-left corner of the block is,
     * if
     * its corner is in the GridPane, else return null
     * 
     * @param block the block to check the up-left corner
     * @return the node where the corner is
     */
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

    /**
     * Creation of GridBlocks, add them to GridPane and to the reference of
     * GridPane, the ArrayList<GridBlock> grid
     */
    public void createGridCells() {
        for (int ColumnIndex = 0; ColumnIndex < this.gridSize; ColumnIndex++) {
            for (int RowIndex = 0; RowIndex < this.gridSize; RowIndex++) {
                GridBlock aPane = new GridBlock(ColumnIndex, RowIndex, false);

                aPane.setPrefHeight(gridCellSize);
                aPane.setPrefWidth(gridCellSize);
                aPane.setStyle(
                        "-fx-background-color: white; -fx-border-width: 2; -fx-border-color: black; -fx-border-radius: 3; -fx-border-insets: -2");

                this.grid.add(aPane);
                this.gridPane.add(aPane, ColumnIndex, RowIndex);
            }
        }
    }

    /**
     * This method is used to set the various final preference sizes of all the
     * panels in the view
     */
    public void setPanelsPrefSizes() {
        this.upperPane.setPrefSize(ViewSwitcher.getWindowWidth(),
                ((ViewSwitcher.getWindowHeight() - this.getGridWidth()) / 2));
        this.bottomPane.setPrefSize(ViewSwitcher.getWindowWidth(),
                ((ViewSwitcher.getWindowHeight() - this.getGridWidth()) / 2));
        this.leftPane.setPrefSize(((ViewSwitcher.getWindowWidth() - this.getGridWidth()) / 2),
                ViewSwitcher.getWindowHeight());
        this.rightPane.setPrefSize(((ViewSwitcher.getWindowWidth() - this.getGridWidth()) / 2),
                ViewSwitcher.getWindowHeight());
        this.upLeftSpawn.setPrefSize(GameView.SPAWN_PANELS_WIDTH, GameView.SPAWN_PANELS_WIDTH);
        this.downLeftSpawn.setPrefSize(GameView.SPAWN_PANELS_WIDTH, GameView.SPAWN_PANELS_WIDTH);
        this.upRightSpawn.setPrefSize(GameView.SPAWN_PANELS_WIDTH, GameView.SPAWN_PANELS_WIDTH);
        this.downRightSpawn.setPrefSize(GameView.SPAWN_PANELS_WIDTH, GameView.SPAWN_PANELS_WIDTH);
    }

    /**
     * This method is used to set the style of the panels
     */
    public void setPanelsStyle() {
        String SpawnPanlesStyle = "-fx-border-width: 5; -fx-border-color: black; -fx-border-radius: 10";
        this.upLeftSpawn.setStyle(SpawnPanlesStyle);
        this.downLeftSpawn.setStyle(SpawnPanlesStyle);
        this.upRightSpawn.setStyle(SpawnPanlesStyle);
        this.downRightSpawn.setStyle(SpawnPanlesStyle);

        this.mainPane.setStyle("-fx-background-image: url('img/whiteTheme.jpg')");

        this.gridPane.setStyle(
                "-fx-vgap: " + GAP_GRID_PANE + "; -fx-hgap: " + GAP_GRID_PANE
                        + "; -fx-background-color: black; -fx-border-insets: 5; -fx-border-width: 5; -fx-border-color: black;");
    }

    /**
     * This methos is used to set the position on the view of the various panels,
     * labels and buttons
     */
    public void setObjectLocation() {
        this.upLeftSpawn.relocate(
                (((ViewSwitcher.getWindowWidth() - this.getGridWidth()) / 2) - GameView.SPAWN_PANELS_WIDTH) / 2,
                (ViewSwitcher.getWindowHeight() - GameView.SPAWN_PANELS_WIDTH - GameView.SPAWN_PANELS_WIDTH
                        - GameView.GAP_BETWEEN_SPAWN_PANELS) / 2);
        this.downLeftSpawn.relocate(
                (((ViewSwitcher.getWindowWidth() - this.getGridWidth()) / 2) - GameView.SPAWN_PANELS_WIDTH) / 2,
                ViewSwitcher.getWindowHeight() - GameView.SPAWN_PANELS_WIDTH - (ViewSwitcher.getWindowHeight()
                        - GameView.SPAWN_PANELS_WIDTH - GameView.SPAWN_PANELS_WIDTH - GameView.GAP_BETWEEN_SPAWN_PANELS)
                        / 2);
        this.upRightSpawn.relocate(
                (ViewSwitcher.getWindowWidth() - GameView.SPAWN_PANELS_WIDTH)
                        - (((ViewSwitcher.getWindowWidth() - this.getGridWidth()) / 2) - GameView.SPAWN_PANELS_WIDTH)
                                / 2,
                (ViewSwitcher.getWindowHeight() - GameView.SPAWN_PANELS_WIDTH - GameView.SPAWN_PANELS_WIDTH
                        - GameView.GAP_BETWEEN_SPAWN_PANELS) / 2);
        this.downRightSpawn.relocate(
                (ViewSwitcher.getWindowWidth() - GameView.SPAWN_PANELS_WIDTH)
                        - (((ViewSwitcher.getWindowWidth() - this.getGridWidth()) / 2) - GameView.SPAWN_PANELS_WIDTH)
                                / 2,
                ViewSwitcher.getWindowHeight() - GameView.SPAWN_PANELS_WIDTH - (ViewSwitcher.getWindowHeight()
                        - GameView.SPAWN_PANELS_WIDTH - GameView.SPAWN_PANELS_WIDTH - GameView.GAP_BETWEEN_SPAWN_PANELS)
                        / 2);

        this.labelCoin.relocate((((ViewSwitcher.getWindowWidth() - this.getGridWidth()) / 2)),
                ((ViewSwitcher.getWindowHeight() - GameView.SPAWN_PANELS_WIDTH - GameView.SPAWN_PANELS_WIDTH
                        - GameView.GAP_BETWEEN_SPAWN_PANELS) / 2)
                        - 40);
        this.labelScore.relocate(700,
                ((ViewSwitcher.getWindowHeight() - GameView.SPAWN_PANELS_WIDTH - GameView.SPAWN_PANELS_WIDTH
                        - GameView.GAP_BETWEEN_SPAWN_PANELS) / 2)
                        - 40);
    }

    /**
     * This method is used to get the size of the GridPane
     * 
     * @return the width of the GridPane
     */
    public int getGridWidth() {
        int gridWidth = (this.gridSize * gridCellSize) + (this.gridSize - 1) * GAP_GRID_PANE;
        return gridWidth;
    }

    public void switchToPauseView() {
        System.out.println("pausa");
        this.pausePane.setVisible(true);
        this.mainPane.setVisible(false);
        this.upLeftSpawn.setVisible(false);
    }
}
