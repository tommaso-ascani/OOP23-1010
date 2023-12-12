package tenten.view.gameView;

import java.io.IOException;
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
import tenten.Items.BlocksAvailable;
import tenten.Items.GameGrid;
import tenten.Items.GridBlock;
import tenten.Items.ShapeBlock;
import tenten.language.GameLanguageSystem;
import tenten.sound.GameSoundSystem;
import tenten.types.BlockType;
import tenten.types.SoundType;
import tenten.types.ViewType;
import tenten.utils.DataUtils;
import tenten.utils.JsonUtils;
import tenten.utils.RandomUtils;
import tenten.utils.ThemeUtils;
import tenten.view.View;
import tenten.view.ViewSwitcher;

/**
 * Class that implements all methods to use the game view.
 */
public final class GameView extends View {

    /**
     * Game grid object.
     */
    private GameGrid<GridBlock> grid;

    /**
     * Spawn pane width.
     */
    private static Double spawnPanelsWidth;

    /**
     * Avalaible spawn blocks.
     */
    private BlocksAvailable<ShapeBlock> blocksAvalaible = new BlocksAvailable<>();

    /**
     * Game score.
     */
    private Integer score;

    /**
     * Game coins.
     */
    private Integer coins;

    /**
     * Space between grid block.
     */
    private static final Integer GAP_GRID_PANE = 5;

    /**
     * Space between spawn pane.
     */
    private static final Integer GAP_BETWEEN_SPAWN_PANELS = 40;

    /**
     * Space between window top and title label.
     */
    private static final Integer TITLE_LABEL_LAYOUTY = 20;

    /**
     * Space between window top and lable value.
     */
    private static final Integer LABEL_VALUE_LAYOUTY = 60;

    /**
     * Space between window and pause button.
     */
    private static final Integer SPACE_PAUSE_BUTTON = 15;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Pane upLeftSpawn,
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
    private Label labelCoin,
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
    private Button buttonRiprendi,
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

        this.titleCoin.setText(GameLanguageSystem.getInstance().getLanguageType().getCoins());
        this.titleScore.setText(GameLanguageSystem.getInstance().getLanguageType().getScore());

        this.buttonMenu.setText(GameLanguageSystem.getInstance().getLanguageType().getMenu());
        this.buttonRicomincia.setText(GameLanguageSystem.getInstance().getLanguageType().getRestart());
        this.buttonRiprendi.setText(GameLanguageSystem.getInstance().getLanguageType().getResume());

        this.dialogRestartNo.setText(GameLanguageSystem.getInstance().getLanguageType().getNo());
        this.dialogRestartYes.setText(GameLanguageSystem.getInstance().getLanguageType().getYes());

        this.dialogMenuBack.setText(GameLanguageSystem.getInstance().getLanguageType().getBack());
        this.dialogMenuNo.setText(GameLanguageSystem.getInstance().getLanguageType().getQuit());
        this.dialogMenuYes.setText(GameLanguageSystem.getInstance().getLanguageType().getSaveAndQuit());

        this.dialogRestartLabel1.setText(GameLanguageSystem.getInstance().getLanguageType().getDialogRestartLabel1());
        this.dialogRestartLabel2.setText(GameLanguageSystem.getInstance().getLanguageType().getDialogRestartLabel2());
        this.dialogMenuLabel1.setText(GameLanguageSystem.getInstance().getLanguageType().getDialogMenuLabel1());
        this.dialogMenuLabel2.setText(GameLanguageSystem.getInstance().getLanguageType().getDialogMenuLabel2());

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

    /**
     * Method to create the pause pane and all the sub-object, like button and pane.
     * Set size, style, position and text.
     */
    public void createPausePane() {

        this.pausePane.setStyle("-fx-background-color: " + ThemeUtils.getSelectedTheme().getColorBackground()
                + "; -fx-border-width: 2; -fx-border-color: black");
        this.dialogPaneRestart.setStyle("-fx-background-color: " + ThemeUtils.getSelectedTheme().getColorBackground()
                + "; -fx-border-width: 2; -fx-border-color: black");
        this.dialogPaneMenu.setStyle("-fx-background-color: " + ThemeUtils.getSelectedTheme().getColorBackground()
                + "; -fx-border-width: 2; -fx-border-color: black");

        this.pausePane.relocate(((this.mainPane.getPrefWidth() / 2) - (this.pausePane.getPrefWidth() / 2)),
                ((this.mainPane.getPrefHeight() / 2) - (this.pausePane.getPrefHeight() / 2)));

        this.dialogPaneRestart.relocate(
                ((this.pausePane.getPrefWidth() / 2) - (this.dialogPaneRestart.getPrefWidth() / 2)),
                ((this.pausePane.getPrefHeight() / 2) - (this.dialogPaneRestart.getPrefHeight() / 2)));

        this.dialogPaneMenu.relocate(((this.pausePane.getPrefWidth() / 2) - (this.dialogPaneMenu.getPrefWidth() / 2)),
                ((this.pausePane.getPrefHeight() / 2) - (this.dialogPaneMenu.getPrefHeight() / 2)));

        this.setListenersPausePane(buttonMenu,
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
     * Method to set the listeners to the various buttons in the pause menu.
     * 
     * @param btnMenu        menu button
     * @param btnResume      resume button
     * @param btnRestart     restart button
     * @param btnDialogY     dialog yes button
     * @param btnDialogN     dialog no button
     * @param btnMenuBack    button back
     * @param btnMenuY       button yes
     * @param btnMenuN       button no
     * @param dialogPaneMenu dialog menu pane
     * @param dialogPane     dialog pane
     */
    private void setListenersPausePane(final Button btnMenu,
            final Button btnResume,
            final Button btnRestart,
            final Button btnDialogY,
            final Button btnDialogN,
            final Button btnMenuBack,
            final Button btnMenuY,
            final Button btnMenuN,
            final Pane dialogPaneMenu,
            final Pane dialogPane) {

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

            this.titleCoin.setDisable(false);
            this.titleCoin.setOpacity(1);

            this.titleScore.setDisable(false);
            this.titleScore.setOpacity(1);

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
     * Method to set the listener when the mouse is released on blocks.
     * 
     * @param block block on which set the listener.
     */
    public void setBlockReadyToBePlaced(final ShapeBlock block) {
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

                    if (grid.controlIfLinesCompleted() == 0) {
                        GameSoundSystem.getInstance().setAudioClip(SoundType.RIGHT_BLOCK_POSITION);
                        GameSoundSystem.getInstance().playAudioClip();
                    }
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

            if (!blocksAvalaible.checkIfBlocksCanBePlaced(grid)) {
                GameSoundSystem.getInstance().setAudioClip(SoundType.GAME_OVER);
                GameSoundSystem.getInstance().playAudioClip();
                DataUtils.saveCoins(this.coins);
                DataUtils.saveBestScore(this.score, String.valueOf(this.grid.getGridSize()));
                this.createGameOverPane();
                this.gameOverPane.setVisible(true);
                try {
                    JsonUtils.flushJson(JsonUtils.MATCH_FILE);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    /*
     * Method to create the game-over pane.
     */
    private void createGameOverPane() {
        GameSoundSystem.getInstance().stopMedia();

        this.gameOverPane.setStyle(
                "-fx-background-color: " + ThemeUtils.getSelectedTheme().getColorBackground()
                        + "; -fx-border-width: 2; -fx-border-color: black");

        this.gameOverPane.relocate((this.mainPane.getPrefWidth() - gameOverPane.getPrefWidth()) / 2,
                (this.mainPane.getPrefHeight() - gameOverPane.getPrefHeight()) / 2);

        this.labelGameOverScore.setText(
                GameLanguageSystem.getInstance().getLanguageType().getScore() + ": " + String.valueOf(this.score));

        this.buttonBackToMenu.setText(GameLanguageSystem.getInstance().getLanguageType().getBackToMenu());

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

        this.titleCoin.setDisable(true);
        this.titleCoin.setOpacity(0.5);

        this.titleScore.setDisable(true);
        this.titleScore.setOpacity(0.5);
    }

    private void setListenersGameOverPane(final Button buttonBackToMenu) {
        buttonBackToMenu.setOnMouseClicked(e -> {
            ViewSwitcher.getInstance().switchView(getStage(), ViewType.HOME);
        });
    }

    /**
     * Method to get the node on which the placeable item is released.
     * 
     * @param block to check.
     * @return node triggered.
     */
    public Node getNodeIfTriggered(final ShapeBlock block) {
        for (GridBlock node : this.grid) {
            if ((node.getMaxX() > block.getTriggerX() && block.getTriggerX() > node.getMinX())
                    && (node.getMaxY() > block.getTriggerY() && block.getTriggerY() > node.getMinY())) {
                return node;
            }
        }
        return null;
    }

    /**
     * Method to check if the grid is empty, then copy it in the
     * griPane to resume a game, or not, then create the panes and add them empty in
     * the gridPane.
     */
    public void createGridCells() {
        // Check if grid is full
        if (!this.grid.isEmpty()) {
            for (GridBlock gridBlock : this.grid) {
                gridBlock.setPrefHeight(grid.getGridCellSize());
                gridBlock.setPrefWidth(grid.getGridCellSize());
                if (gridBlock.getFill() == null) {
                    gridBlock.setStyle(
                            "-fx-background-color: " + ThemeUtils.getSelectedTheme().getColorGrid()
                                    + "; -fx-border-width: 2; -fx-border-radius: 3; -fx-border-insets: -2");
                } else {
                    gridBlock.setStyle(
                            "-fx-background-color: " + gridBlock.getFill()
                                    + "; -fx-border-width: 2; -fx-border-radius: 3; -fx-border-insets: -2");
                }

                this.gridPane.add(gridBlock, gridBlock.getGridX(), gridBlock.getGridY());
            }
        } else {
            for (int rowIndex = 0; rowIndex < grid.getGridSize(); rowIndex++) {
                for (int columnIndex = 0; columnIndex < grid.getGridSize(); columnIndex++) {
                    GridBlock aPane = new GridBlock(columnIndex, rowIndex, null,
                            ThemeUtils.getSelectedTheme().getColorGrid());

                    aPane.setPrefHeight(grid.getGridCellSize());
                    aPane.setPrefWidth(grid.getGridCellSize());
                    aPane.setStyle(
                            "-fx-background-color: " + ThemeUtils.getSelectedTheme().getColorGrid()
                                    + "; -fx-border-width: 2; -fx-border-radius: 3; -fx-border-insets: -2");

                    this.grid.add(aPane);
                    this.gridPane.add(aPane, columnIndex, rowIndex);
                }
            }
        }

    }

    /**
     * Method to set the final preferences size of panels.
     */
    public void setPanelsPrefSizes() {
        this.upLeftSpawn.setPrefSize(GameView.spawnPanelsWidth, GameView.spawnPanelsWidth);
        this.downLeftSpawn.setPrefSize(GameView.spawnPanelsWidth, GameView.spawnPanelsWidth);
        this.upRightSpawn.setPrefSize(GameView.spawnPanelsWidth, GameView.spawnPanelsWidth);
        this.downRightSpawn.setPrefSize(GameView.spawnPanelsWidth, GameView.spawnPanelsWidth);
    }

    /**
     * Method to set the style of main panel.
     */
    public void setPanelsStyle() {

        String spawnPanlesStyle = "-fx-border-width: 5; -fx-border-radius: 10";

        this.upLeftSpawn.setStyle(spawnPanlesStyle);
        this.downLeftSpawn.setStyle(spawnPanlesStyle);
        this.upRightSpawn.setStyle(spawnPanlesStyle);
        this.downRightSpawn.setStyle(spawnPanlesStyle);

        this.mainPane.setStyle("-fx-background: " + ThemeUtils.getSelectedTheme().getColorBackground());

        this.gridPane.setStyle(
                "-fx-vgap: " + GAP_GRID_PANE + "; -fx-hgap: " + GAP_GRID_PANE
                        + "; -fx-border-insets: 5; -fx-border-width: 5;");
    }

    /**
     * Method to set position on the view of various panels,
     * labels and buttons.
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

        this.titleCoin.relocate(this.mainPane.getPrefWidth() / 3 - (this.titleCoin.getWidth() / 2),
                TITLE_LABEL_LAYOUTY);

        this.labelCoin.relocate(this.mainPane.getPrefWidth() / 3 - (this.labelCoin.getWidth() / 2),
                LABEL_VALUE_LAYOUTY);

        this.titleScore.relocate(((this.mainPane.getPrefWidth() / 3) * 2) - (this.titleScore.getWidth() / 2),
                TITLE_LABEL_LAYOUTY);

        this.labelScore.relocate(((this.mainPane.getPrefWidth() / 3) * 2) - (this.labelScore.getWidth() / 2),
                LABEL_VALUE_LAYOUTY);

        this.gridPane.relocate(this.mainPane.getPrefWidth() / 2 - (this.gridPane.getWidth() / 2),
                this.mainPane.getPrefHeight() / 2 - (this.gridPane.getHeight() / 2));

        this.imagePause.relocate(this.mainPane.getPrefWidth() - this.imagePause.getFitHeight() - SPACE_PAUSE_BUTTON,
                SPACE_PAUSE_BUTTON);
    }

    /**
     * Method to get the GridPane size.
     * 
     * @return GridPane width.
     */
    public int getGridWidth() {
        int gridWidth = (grid.getGridSize() * grid.getGridCellSize()) + (grid.getGridSize() - 1) * GAP_GRID_PANE;
        return gridWidth;
    }

    /**
     * This method switch to pause pane.
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

    /**
     * Method to create new random placeable item.
     */
    public void createNewPuzzles() {

        ShapeBlock block;
        BlockType type;

        for (int x = 1; x <= 4; x++) {

            type = RandomUtils.getRandomPuzzle();

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
