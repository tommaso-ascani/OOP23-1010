package oop23_1010.view.gameView;

import java.io.IOException;
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
import javafx.util.Pair;
import oop23_1010.utils.BlockType;
import oop23_1010.utils.BlocksAvailable;
import oop23_1010.utils.GameGrid;
import oop23_1010.utils.GridBlock;
import oop23_1010.utils.JsonUtils;
import oop23_1010.utils.ShapeBlock;
import oop23_1010.view.ViewImpl;
import oop23_1010.view.ViewSwitcher;
import oop23_1010.view.ViewType;

public class GameView extends ViewImpl {

    private int gridSize;
    public int gridCellSize;
    public GameGrid<GridBlock> grid = new GameGrid<>(gridSize);
    public BlocksAvailable<ShapeBlock> blocksAvalaible = new BlocksAvailable<>();

    private static final int GAP_GRID_PANE = 5;
    private static final int SPAWN_PANELS_WIDTH = 260;
    private static final int GAP_BETWEEN_SPAWN_PANELS = 40;

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

        this.labelCoin.setText("Coin:200");
        this.labelScore.setText("Score:200");

        this.labelScore.relocate(700, ((720 - 260 - 260 - 50) / 2) - 40);

        ShapeBlock block1 = new ShapeBlock(BlockType.BLOCK_1x5, upLeftSpawn, this, blocksAvalaible);
        ShapeBlock block2 = new ShapeBlock(BlockType.BLOCK_2x2, upRightSpawn, this, blocksAvalaible);
        ShapeBlock block3 = new ShapeBlock(BlockType.BLOCK_4x1, downLeftSpawn, this, blocksAvalaible);
        ShapeBlock block4 = new ShapeBlock(BlockType.BLOCK_1x3, downRightSpawn, this, blocksAvalaible);

        this.setBlockReadyToBePlaced(block1);
        this.setBlockReadyToBePlaced(block2);
        this.setBlockReadyToBePlaced(block3);
        this.setBlockReadyToBePlaced(block4);

        this.pausePane.setVisible(false);
        this.pausePane.setPrefSize(ViewSwitcher.getWindowWidth() / 1.5, ViewSwitcher.getWindowHeight() / 1.5);
        this.pausePane.setStyle(
                "-fx-background-color: white; -fx-border-width: 2; -fx-border-color: black");
        this.pausePane.relocate(((ViewSwitcher.getWindowWidth() -
                this.pausePane.getPrefWidth()) / 2),
                ((ViewSwitcher.getWindowHeight() - this.pausePane.getPrefHeight()) / 2));

        Button buttonRiprendi = new Button("Riprendi");
        Button buttonRicomincia = new Button("Ricomincia");
        Button buttonMenu = new Button("Menu");
        Pane dialogPane = new Pane();
        dialogPane.setVisible(false);
        Button dialogYes = new Button("Si, ricomincia");
        Button dialogNo = new Button("No, indietro");

        dialogPane.getChildren().addAll(dialogYes, dialogNo);

        this.pausePane.getChildren().addAll(buttonRiprendi, buttonRicomincia, buttonMenu, dialogPane);

        buttonRiprendi.setPrefSize(80, 40);
        buttonRicomincia.setPrefSize(80, 40);
        buttonMenu.setPrefSize(80, 40);
        dialogPane.setPrefSize(300, 200);
        dialogYes.setPrefSize(80, 30);
        dialogNo.setPrefSize(80, 30);

        buttonRiprendi.relocate((((this.pausePane.getPrefWidth() - 80) / 2) - 80) / 2,
                (this.pausePane.getPrefHeight() - 40) / 2);
        buttonRicomincia.relocate(((this.pausePane.getPrefWidth() - 80) / 2),
                (this.pausePane.getPrefHeight() - 40) / 2);
        buttonMenu.relocate(
                this.pausePane.getPrefWidth() - 80 - ((((this.pausePane.getPrefWidth() - 80) / 2) - 80) / 2),
                (this.pausePane.getPrefHeight() - 40) / 2);

        System.out.println(dialogYes.getWidth());
        System.out.println(dialogYes.getHeight());
        dialogPane.relocate((this.pausePane.getPrefWidth() - 300) / 2, (this.pausePane.getPrefHeight() - 200) / 2);

        dialogYes.relocate((dialogPane.getPrefWidth() - dialogYes.getWidth() - dialogNo.getWidth()) / 2,
                (dialogPane.getPrefHeight() - dialogYes.getHeight()) / 2);
        dialogNo.relocate(
                (dialogPane.getPrefWidth() - dialogNo.getWidth()
                        - ((dialogPane.getPrefWidth() - dialogYes.getWidth() - dialogNo.getWidth() - 40)) / 2),
                (dialogPane.getPrefHeight() - dialogYes.getHeight()) / 2);

        dialogPane.setStyle("-fx-background-color: white; -fx-border-width: 2; -fx-border-color: black");

        buttonRiprendi.setOnMouseClicked(e -> {
            this.pausePane.setVisible(false);
            this.upLeftSpawn.setVisible(true);
            this.downLeftSpawn.setVisible(true);
            this.upRightSpawn.setVisible(true);
            this.downRightSpawn.setVisible(true);
            this.labelCoin.setVisible(true);
            this.labelScore.setVisible(true);
        });

        buttonRicomincia.setOnMouseClicked(e -> {
            dialogPane.setVisible(true);
        });

        dialogYes.setOnMouseClicked(e -> {
            ViewSwitcher.getInstance().switchView(getStage(), ViewType.GAME);
        });

        dialogNo.setOnMouseClicked(e -> {
            dialogPane.setVisible(false);
        });

        buttonMenu.setOnMouseClicked(e -> {
            try {
                JsonUtils.addElement(new Pair<String, Object>(JsonUtils.MATCH_SCORE, this.labelScore.getText()));
                JsonUtils.addElement(new Pair<String, Object>(JsonUtils.MATCH_ON_GOING, true));
                JsonUtils.addElement(new Pair<String, Object>(JsonUtils.GRID_SIZE, this.gridSize));
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
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

                ArrayList<GridBlock> toFill = new ArrayList<>();

                toFill.clear();
                for (int y = targetY; y < targetY + block.getHeight(); y++) {
                    if (y >= gridSize) {
                        break;
                    }
                    for (int x = targetX; x < targetX + block.getWidth(); x++) {
                        if (x >= gridSize) {
                            break;
                        }
                        if (!grid.getElement(x, y).getFill()) {
                            toFill.add(grid.getElement(x, y));
                        } else {
                            toFill.clear();
                            break;
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

            } else {
                block.returnToStart();
            }
            controlIfLinesCompleted();
            blocksAvalaible.remove(block);
            // grid.getRightBlock(node, 2).setStyle("-fx-background-color: black");
            try {
                blocksAvalaible.checkIfBlocksCanBePlaced(grid, this.gridSize);
            } catch (Exception error) {
                System.out.println(error);
            }
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
        for (int RowIndex = 0; RowIndex < this.gridSize; RowIndex++) {
            for (int ColumnIndex = 0; ColumnIndex < this.gridSize; ColumnIndex++) {
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
        this.pausePane.setVisible(true);
        this.upLeftSpawn.setVisible(false);
        this.downLeftSpawn.setVisible(false);
        this.upRightSpawn.setVisible(false);
        this.downRightSpawn.setVisible(false);
        this.labelCoin.setVisible(false);
        this.labelScore.setVisible(false);
    }
}
