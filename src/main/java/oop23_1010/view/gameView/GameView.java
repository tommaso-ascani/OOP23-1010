package oop23_1010.view.gameView;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import oop23_1010.sound.GameSoundSystem;
import oop23_1010.sound.SoundType;
import oop23_1010.types.BlockType;
import oop23_1010.utils.BlocksAvailable;
import oop23_1010.utils.DataUtils;
import oop23_1010.utils.GameGrid;
import oop23_1010.utils.GridBlock;
import oop23_1010.utils.Randomizer;
import oop23_1010.utils.ShapeBlock;
import oop23_1010.utils.ThemeUtils;
import oop23_1010.view.View;
import oop23_1010.view.ViewSwitcher;
import oop23_1010.view.ViewType;

public class GameView extends View {

    private GameGrid<GridBlock> grid;
    private BlocksAvailable<ShapeBlock> blocksAvalaible = new BlocksAvailable<>();

    private Integer score;
    private Integer coins;

    private static final Integer GAP_GRID_PANE = 5;
    private static Double spawnPanelsWidth;
    private static final Integer GAP_BETWEEN_SPAWN_PANELS = 40;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Pane    upLeftSpawn,
                    upRightSpawn,
                    downLeftSpawn,
                    downRightSpawn,
                    dialogPaneRestart,
                    dialogPaneMenu,
                    pausePane,
                    gameOverPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label   labelCoin, 
                    labelScore, 
                    titleCoin, 
                    titleScore,
                    dialogRestartLabel1,
                    dialogRestartLabel2,
                    dialogMenuLabel1,
                    dialogMenuLabel2,
                    labelGameOver,
                    labelGameOverScore;

    @FXML
    private Button  buttonRiprendi,
                    buttonRicomincia,
                    buttonMenu,
                    dialogMenuYes,
                    dialogMenuNo,
                    dialogMenuBack,
                    dialogRestartYes,
                    dialogRestartNo,
                    buttonBackToMenu;

    @FXML
    private ImageView imagePause;

    @Override
    public void init() {

        this.mainPane.setPrefSize(View.WINDOW_WIDTH, View.WINDOW_HEIGHT);

        // -------------------------------- Sound Setup --------------------------------

        GameSoundSystem.getInstance().setMediaPlayer(SoundType.BACKGROUND_01);
        GameSoundSystem.getInstance().playMediaPlayer();

        // -------------------------------- Json Setup ---------------------------------

        this.coins = DataUtils.loadCoins();
        this.score = DataUtils.loadScore();
        this.grid = DataUtils.loadGrid();

        // -------------------------------- View Setup ---------------------------------

        GameView.spawnPanelsWidth = 6.0 * grid.getGridCellSize();

        this.labelCoin.setText(String.valueOf(this.coins));
        this.labelScore.setText(String.valueOf(this.score));

        this.createGridCells();
        this.setPanelsPrefSizes();
        this.setPanelsStyle();
        this.createNewPuzzles();
        this.createPausePane();
        this.setObjectLocation();
    }

    /*
     * Instantiate the pause pane and all the sub-object, like button and pane, and
     * set size, style, position and text of all
     */
    public void createPausePane() {
        
        this.pausePane.setStyle("-fx-background-color: " + ThemeUtils.getSelectedSkin().getColor_background() + "; -fx-border-width: 2; -fx-border-color: black");
        this.dialogPaneRestart.setStyle("-fx-background-color: " + ThemeUtils.getSelectedSkin().getColor_background() + "; -fx-border-width: 2; -fx-border-color: black");
        this.dialogPaneMenu.setStyle("-fx-background-color: " + ThemeUtils.getSelectedSkin().getColor_background() + "; -fx-border-width: 2; -fx-border-color: black");

        this.pausePane.relocate(            ((this.mainPane.getPrefWidth() / 2)  - (this.pausePane.getPrefWidth() / 2)),
                                            ((this.mainPane.getPrefHeight() / 2)  - (this.pausePane.getPrefHeight() / 2)));

        this.dialogPaneRestart.relocate(    ((this.pausePane.getPrefWidth() / 2)  - (this.dialogPaneRestart.getPrefWidth() / 2)),
                                            ((this.pausePane.getPrefHeight() / 2)  - (this.dialogPaneRestart.getPrefHeight() / 2)));

        this.dialogPaneMenu.relocate(       ((this.pausePane.getPrefWidth() / 2)  - (this.dialogPaneMenu.getPrefWidth() / 2)),
                                            ((this.pausePane.getPrefHeight() / 2)  - (this.dialogPaneMenu.getPrefHeight() / 2)));

        this.setListenersPausePane( buttonMenu, 
                                    buttonRiprendi, 
                                    buttonRicomincia, 
                                    dialogRestartYes, 
                                    dialogRestartNo, 
                                    dialogMenuBack, 
                                    dialogMenuYes, 
                                    dialogMenuNo, 
                                    dialogPaneMenu, 
                                    dialogPaneRestart);
    }

    /**
     * This method is used to set the listeners to the various button in the pause
     * menu
     * 
     * @param btnMenu    the menu button
     * @param btnResume  the resunme button
     * @param btnRestart the restart button
     * @param btnDialogY the dialog yes button
     * @param btnDialogN the dialog no button
     * @param dialogPane the dialog pane
     */
    private void setListenersPausePane( Button btnMenu, 
                                        Button btnResume, 
                                        Button btnRestart, 
                                        Button btnDialogY,
                                        Button btnDialogN,
                                        Button btnMenuBack, 
                                        Button btnMenuY, 
                                        Button btnMenuN,
                                        Pane dialogPaneMenu,
                                        Pane dialogPane) {
        
        btnResume.setOnMouseClicked(e -> {

            GameSoundSystem.getInstance().resumeMedia();

            this.pausePane.setVisible(false);

            this.upLeftSpawn.setDisable(false);
            this.upLeftSpawn.setOpacity(1);

            this.downLeftSpawn.setDisable(false);
            this.downLeftSpawn.setOpacity(1);

            this.upRightSpawn.setDisable(false);
            this.upRightSpawn.setOpacity(1);

            this.downRightSpawn.setDisable(false);
            this.downRightSpawn.setOpacity(1);

            this.labelCoin.setDisable(false);
            this.labelCoin.setOpacity(1);

            this.labelScore.setDisable(false);
            this.labelScore.setOpacity(1);

        });

        btnRestart.setOnMouseClicked(e -> {
            dialogPane.setVisible(true);
            dialogPane.toFront();

            btnMenu.setDisable(true);
            btnMenu.setOpacity(0.5);

            btnRestart.setDisable(true);
            btnRestart.setOpacity(0.5);

            btnResume.setDisable(true);
            btnResume.setOpacity(0.5);
        });

        btnDialogY.setOnMouseClicked(e -> {
            ViewSwitcher.getInstance().switchView(getStage(), ViewType.GAME);
        });

        btnDialogN.setOnMouseClicked(e -> {
            dialogPane.setVisible(false);

            btnMenu.setDisable(false);
            btnMenu.setOpacity(1);

            btnRestart.setDisable(false);
            btnRestart.setOpacity(1);

            btnResume.setDisable(false);
            btnResume.setOpacity(1);
        });

        btnMenu.setOnMouseClicked(e -> {
            dialogPaneMenu.setVisible(true);
            dialogPaneMenu.toFront();

            btnMenu.setDisable(true);
            btnMenu.setOpacity(0.5);

            btnRestart.setDisable(true);
            btnRestart.setOpacity(0.5);

            btnResume.setDisable(true);
            btnResume.setOpacity(0.5);
        });

        btnMenuBack.setOnMouseClicked(e -> {
            dialogPaneMenu.setVisible(false);

            btnMenu.setDisable(false);
            btnMenu.setOpacity(1);

            btnRestart.setDisable(false);
            btnRestart.setOpacity(1);

            btnResume.setDisable(false);
            btnResume.setOpacity(1);
        });

        btnMenuN.setOnMouseClicked(e -> {
            DataUtils.saveCoins(this.coins);
            ViewSwitcher.getInstance().switchView(getStage(), ViewType.HOME);
        });

        btnMenuY.setOnMouseClicked(e -> {
            DataUtils.saveMatchData(this.score, this.grid);
            DataUtils.saveCoins(this.coins);
            ViewSwitcher.getInstance().switchView(getStage(), ViewType.HOME);
        });
    }

    /**
     * This method is used to set the listener when the mouse is released on the
     * blocks
     * 
     * @param block
     */
    public void setBlockReadyToBePlaced(ShapeBlock block) {
        block.setOnMouseReleased(e -> {

            GridBlock node = (GridBlock) this.getNodeIfTriggered(block);

            if (this.getNodeIfTriggered(block) != null) {

                Integer targetX = node.getGridX();
                Integer targetY = node.getGridY();

                ArrayList<GridBlock> toFill = new ArrayList<>();

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
                    for (GridBlock x : toFill) {
                        x.setFill(block.getColor());
                        x.setStyle("-fx-background-color: " + block.getColor());
                        Pane pane = block.getPane();
                        pane.getChildren().remove(block);
                        this.score++;
                    }

                    for (int index = 1; index <= grid.getNumFullLines().size(); index++) {
                        this.score = this.score + (this.grid.getGridSize() * index);
                        this.coins++;
                    }

                    grid.controlIfLinesCompleted();

                    /*
                     * TO-DO
                     */
                    GameSoundSystem.getInstance().setAudioClip(SoundType.RIGHT_BLOCK_POSITION);
                    GameSoundSystem.getInstance().playAudioClip();
                    blocksAvalaible.remove(block);

                    this.labelScore.setText(String.valueOf(this.score));
                    this.labelCoin.setText(String.valueOf(this.coins));

                } else {
                    GameSoundSystem.getInstance().setAudioClip(SoundType.WRONG_BLOCK_POSITION);
                    GameSoundSystem.getInstance().playAudioClip();
                    block.returnToStart();
                }

            } else {
                GameSoundSystem.getInstance().setAudioClip(SoundType.WRONG_BLOCK_POSITION);
                GameSoundSystem.getInstance().playAudioClip();
                block.returnToStart();
            }
            
            if (blocksAvalaible.size() == 0) {
                createNewPuzzles();
            }

            if (!blocksAvalaible.checkIfBlocksCanBePlaced(grid, grid.getGridSize())) {
                GameSoundSystem.getInstance().setAudioClip(SoundType.GAME_OVER);
                GameSoundSystem.getInstance().playAudioClip();
                DataUtils.saveCoins(this.coins);
                DataUtils.saveBestScore(this.score, String.valueOf(this.grid.getGridSize()));
                this.createGameOverPane();
                this.gameOverPane.setVisible(true);
            }
        });
    }

    /*
     * This method is used to create and instantiate the game over pane and all its
     * sub-nodes
     */
    private void createGameOverPane() {

        this.gameOverPane.setStyle(
                "-fx-background-color: " + ThemeUtils.getSelectedSkin().getColor_background() + "; -fx-border-width: 2; -fx-border-color: black");

        this.gameOverPane.relocate( (this.mainPane.getPrefWidth() - gameOverPane.getPrefWidth()) / 2,
                                    (this.mainPane.getPrefHeight() - gameOverPane.getPrefHeight()) / 2);

        labelGameOverScore.setText("Score: " + String.valueOf(this.score));

        this.setListenersGameOverPane(buttonBackToMenu);

        this.imagePause.setDisable(true);
        this.imagePause.setOpacity(0.5);

        this.upLeftSpawn.setDisable(true);
        this.upLeftSpawn.setOpacity(0.5);

        this.downLeftSpawn.setDisable(true);
        this.downLeftSpawn.setOpacity(0.5);

        this.upRightSpawn.setDisable(true);
        this.upRightSpawn.setOpacity(0.5);

        this.downRightSpawn.setDisable(true);
        this.downRightSpawn.setOpacity(0.5);

        this.labelCoin.setDisable(true);
        this.labelCoin.setOpacity(0.5);

        this.labelScore.setDisable(true);
        this.labelScore.setOpacity(0.5);
    }

    private void setListenersGameOverPane(Button buttonBackToMenu) {
        buttonBackToMenu.setOnMouseClicked(e -> {
            ViewSwitcher.getInstance().switchView(getStage(), ViewType.HOME);
        });
    }

    /**
     * This method is used to get the node where the up-left corner of the block is,
     * if
     * its corner is in the GridPane, else return null
     * 
     * @param block the block to check the up-left corner
     * @return the node where the corner is
     */
    public Node getNodeIfTriggered(ShapeBlock block) {
        for (GridBlock node : this.grid) {
            if ((node.getMaxX() > block.getTriggerX() && block.getTriggerX() > node.getMinX()) &&
                    (node.getMaxY() > block.getTriggerY() && block.getTriggerY() > node.getMinY())) {
                return node;
            }
        }
        return null;
    }

    /**
     * This methos is used to check if the grid is empty, then copy it in the
     * griPane to resume a game, or not, then create the panes and add them empty in
     * the gridPane
     */
    public void createGridCells() {
        // Check if grid is full
        if (!this.grid.isEmpty()) {
            for (GridBlock gridBlock : this.grid) {
                gridBlock.setPrefHeight(grid.getGridCellSize());
                gridBlock.setPrefWidth(grid.getGridCellSize());
                if (gridBlock.getFill() == null) {
                    gridBlock.setStyle(
                            "-fx-background-color: " + ThemeUtils.getSelectedSkin().getColor_grid()
                                    + "; -fx-border-width: 2; -fx-border-radius: 3; -fx-border-insets: -2");
                } else {
                    gridBlock.setStyle(
                            "-fx-background-color: " + gridBlock.getFill()
                                    + "; -fx-border-width: 2; -fx-border-radius: 3; -fx-border-insets: -2");
                }

                this.gridPane.add(gridBlock, gridBlock.getGridX(), gridBlock.getGridY());
            }
            // then the grid is empty so create panes
        } else {
            for (int RowIndex = 0; RowIndex < grid.getGridSize(); RowIndex++) {
                for (int ColumnIndex = 0; ColumnIndex < grid.getGridSize(); ColumnIndex++) {
                    GridBlock aPane = new GridBlock(ColumnIndex, RowIndex, null, ThemeUtils.getSelectedSkin().getColor_grid());

                    aPane.setPrefHeight(grid.getGridCellSize());
                    aPane.setPrefWidth(grid.getGridCellSize());
                    aPane.setStyle(
                            "-fx-background-color: " + ThemeUtils.getSelectedSkin().getColor_grid()
                                    + "; -fx-border-width: 2; -fx-border-radius: 3; -fx-border-insets: -2");

                    this.grid.add(aPane);
                    this.gridPane.add(aPane, ColumnIndex, RowIndex);
                }
            }
        }

    }

    /**
     * This method is used to set the various final preference sizes of all the main
     * panels in the view
     */
    public void setPanelsPrefSizes() {
        this.upLeftSpawn.setPrefSize(GameView.spawnPanelsWidth, GameView.spawnPanelsWidth);
        this.downLeftSpawn.setPrefSize(GameView.spawnPanelsWidth, GameView.spawnPanelsWidth);
        this.upRightSpawn.setPrefSize(GameView.spawnPanelsWidth, GameView.spawnPanelsWidth);
        this.downRightSpawn.setPrefSize(GameView.spawnPanelsWidth, GameView.spawnPanelsWidth);
    }

    /**
     * This method is used to set the style of the main panels
     */
    public void setPanelsStyle() {

        String SpawnPanlesStyle = "-fx-border-width: 5; -fx-border-radius: 10";

        this.upLeftSpawn.setStyle(SpawnPanlesStyle);
        this.downLeftSpawn.setStyle(SpawnPanlesStyle);
        this.upRightSpawn.setStyle(SpawnPanlesStyle);
        this.downRightSpawn.setStyle(SpawnPanlesStyle);

        this.mainPane.setStyle("-fx-background: " + ThemeUtils.getSelectedSkin().getColor_background());

        this.gridPane.setStyle(
                "-fx-vgap: " + GAP_GRID_PANE + "; -fx-hgap: " + GAP_GRID_PANE
                        + "; -fx-border-insets: 5; -fx-border-width: 5;");
    }

    /**
     * This methos is used to set the position on the view of the various panels,
     * labels and buttons
     */
    public void setObjectLocation() {
        Group gruppo = new Group(this.mainPane);
        this.getStage().setScene(new Scene(gruppo));
        this.getStage().show();

        this.upLeftSpawn.relocate(
                (((this.mainPane.getPrefWidth() - this.getGridWidth()) / 2) - GameView.spawnPanelsWidth) / 2,
                (this.mainPane.getPrefHeight() - GameView.spawnPanelsWidth - GameView.spawnPanelsWidth
                        - GameView.GAP_BETWEEN_SPAWN_PANELS) / 2);
        this.downLeftSpawn.relocate(
                (((this.mainPane.getPrefWidth() - this.getGridWidth()) / 2) - GameView.spawnPanelsWidth) / 2,
                this.mainPane.getPrefHeight() - GameView.spawnPanelsWidth - (this.mainPane.getPrefHeight()
                        - GameView.spawnPanelsWidth - GameView.spawnPanelsWidth - GameView.GAP_BETWEEN_SPAWN_PANELS)
                        / 2);
        this.upRightSpawn.relocate(
                (this.mainPane.getPrefWidth() - GameView.spawnPanelsWidth)
                        - (((this.mainPane.getPrefWidth() - this.getGridWidth()) / 2) - GameView.spawnPanelsWidth)
                                / 2,
                (this.mainPane.getPrefHeight() - GameView.spawnPanelsWidth - GameView.spawnPanelsWidth
                        - GameView.GAP_BETWEEN_SPAWN_PANELS) / 2);
        this.downRightSpawn.relocate(
                (this.mainPane.getPrefWidth() - GameView.spawnPanelsWidth)
                        - (((this.mainPane.getPrefWidth() - this.getGridWidth()) / 2) - GameView.spawnPanelsWidth)
                                / 2,
                this.mainPane.getPrefHeight() - GameView.spawnPanelsWidth - (this.mainPane.getPrefHeight()
                        - GameView.spawnPanelsWidth - GameView.spawnPanelsWidth - GameView.GAP_BETWEEN_SPAWN_PANELS)
                        / 2);

        this.titleCoin.relocate(this.mainPane.getPrefWidth() / 3 - (this.titleCoin.getWidth() / 2), 20);

        this.labelCoin.relocate(this.mainPane.getPrefWidth() / 3 - (this.labelCoin.getWidth() / 2), 60);

        this.titleScore.relocate(((this.mainPane.getPrefWidth() / 3) * 2) - (this.titleScore.getWidth() / 2), 20);

        this.labelScore.relocate(((this.mainPane.getPrefWidth() / 3) * 2) - (this.labelScore.getWidth() / 2), 60);

        this.gridPane.relocate(this.mainPane.getPrefWidth() / 2 - (this.gridPane.getWidth() / 2),
                this.mainPane.getPrefHeight() / 2 - (this.gridPane.getHeight() / 2));

        this.imagePause.relocate(this.mainPane.getPrefWidth() - this.imagePause.getFitHeight() - 15, 15);
    }

    /**
     * This method is used to get the size of the GridPane
     * 
     * @return the width of the GridPane
     */
    public int getGridWidth() {
        int gridWidth = (grid.getGridSize() * grid.getGridCellSize()) + (grid.getGridSize() - 1) * GAP_GRID_PANE;
        return gridWidth;
    }

    /*
     * This methos is the listeners attached to the pause button and display the
     * pause menu and disable the game view
     */
    public void switchToPauseView() {

        GameSoundSystem.getInstance().pauseMedia();

        this.pausePane.setVisible(true);
        this.pausePane.toFront();

        this.upLeftSpawn.setDisable(true);
        this.upLeftSpawn.setOpacity(0.5);

        this.downLeftSpawn.setDisable(true);
        this.downLeftSpawn.setOpacity(0.5);

        this.upRightSpawn.setDisable(true);
        this.upRightSpawn.setOpacity(0.5);

        this.downRightSpawn.setDisable(true);
        this.downRightSpawn.setOpacity(0.5);

        this.labelCoin.setDisable(true);
        this.labelCoin.setOpacity(0.5);

        this.labelScore.setDisable(true);
        this.labelScore.setOpacity(0.5);
    }

    /*
     * This method is used to create random instances of blocks to play with
     */
    public void createNewPuzzles() {

        ShapeBlock block;
        BlockType type;

        for (int x = 1; x <= 4; x++) {

            type = Randomizer.getRandomPuzzle();

            switch (x) {
                case 1:
                    block = new ShapeBlock(type, upLeftSpawn, this.grid, blocksAvalaible);
                    break;
                case 2:
                    block = new ShapeBlock(type, upRightSpawn, this.grid, blocksAvalaible);
                    break;
                case 3:
                    block = new ShapeBlock(type, downLeftSpawn, this.grid, blocksAvalaible);
                    break;
                case 4:
                    block = new ShapeBlock(type, downRightSpawn, this.grid, blocksAvalaible);
                    break;
                default:
                    block = new ShapeBlock(type, upLeftSpawn, this.grid, blocksAvalaible);
                    break;
            }
            this.setBlockReadyToBePlaced(block);
        }
    }
}