package oop23_1010.view.gameView;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Pair;
import oop23_1010.sound.GameSoundSystem;
import oop23_1010.sound.SoundType;
import oop23_1010.types.BlockType;
import oop23_1010.types.ColorType;
import oop23_1010.utils.BlocksAvailable;
import oop23_1010.utils.GameGrid;
import oop23_1010.utils.GridBlock;
import oop23_1010.utils.JsonUtils;
import oop23_1010.utils.Randomizer;
import oop23_1010.utils.ShapeBlock;
import oop23_1010.view.ViewImpl;
import oop23_1010.view.ViewSwitcher;
import oop23_1010.view.ViewType;

public class GameView extends ViewImpl {

    private GameGrid<GridBlock> grid;
    private BlocksAvailable<ShapeBlock> blocksAvalaible = new BlocksAvailable<>();

    private Integer score = 0;

    private static final Integer GAP_GRID_PANE = 5;
    private static Double spawnPanelsWidth;
    private static final Integer GAP_BETWEEN_SPAWN_PANELS = 40;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Pane leftPane, upLeftSpawn, downLeftSpawn;

    @FXML
    private GridPane gridPane;

    @FXML
    private Pane rightPane, upRightSpawn, downRightSpawn;

    @FXML
    private Pane bottomPane, upperPane, pausePane, gameOverPane;

    @FXML
    private Label labelCoin, labelScore;

    @FXML
    private ImageView imagePause;

    @Override
    public void init() {

        GameSoundSystem.getInstance().setMediaPlayer(SoundType.BACKGROUND_01,
                GameSoundSystem.getInstance().getVolumeObject().getVolumeValue());
        GameSoundSystem.getInstance().playMediaPlayer();

        try {
            if (JsonUtils.jsonExist(JsonUtils.MATCH_FILE)) {

                grid = new GameGrid<>((Integer) JsonUtils.loadData(JsonUtils.GRID_SIZE, JsonUtils.MATCH_FILE));

                if (grid.getGridSize() == 5) {
                    grid.setGridCellSize(45);
                }
                if (grid.getGridSize() == 10) {
                    grid.setGridCellSize(35);
                }
                if (grid.getGridSize() == 15) {
                    grid.setGridCellSize(30);
                }
                if (grid.getGridSize() == 20) {
                    grid.setGridCellSize(25);
                }

                GameView.spawnPanelsWidth = 6.0 * grid.getGridCellSize();

                this.score = (Integer) JsonUtils.loadData(JsonUtils.MATCH_SCORE,JsonUtils.MATCH_FILE);

                JSONArray a = JsonUtils.loadGrid(JsonUtils.GRID_COMPOSITION, JsonUtils.MATCH_FILE);

                for (int i = 0; i < a.length(); i++) {
                    ColorType color;
                    if (a.getJSONObject(i).get("color").equals("null")) {
                        color = null;
                    } else {
                        color = ColorType.get((String) a.getJSONObject(i).get("color"));
                    }
                    GridBlock aPane = new GridBlock((Integer) a.getJSONObject(i).get("X"),
                            (Integer) a.getJSONObject(i).get("Y"),
                            color);

                    grid.add(aPane);
                }
                JsonUtils.flushJson(JsonUtils.MATCH_FILE);
            } else {
                grid = new GameGrid<>(HomeView.getGridSize());

                if (grid.getGridSize() == 5) {
                    grid.setGridCellSize(45);
                }
                if (grid.getGridSize() == 10) {
                    grid.setGridCellSize(35);
                }
                if (grid.getGridSize() == 15) {
                    grid.setGridCellSize(30);
                }
                if (grid.getGridSize() == 20) {
                    grid.setGridCellSize(25);
                }

                GameView.spawnPanelsWidth = 6.0 * grid.getGridCellSize();
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        this.createGridCells();

        this.setPanelsPrefSizes();

        this.setPanelsStyle();

        this.setObjectLocation();

        this.labelCoin.setFont(new Font(null, 30));
        this.labelCoin.setText("Coin: TO-DO");

        this.labelScore.setFont(new Font(null, 30));
        this.labelScore.setText("Score: " + this.score);

        this.labelScore.relocate(100, this.upperPane.getBoundsInLocal().getMaxY());

        this.createNewPuzzles();

        this.createPausePane();

        Group gruppo = new Group(this.mainPane, this.upLeftSpawn, this.downLeftSpawn, this.upRightSpawn,
                this.downRightSpawn, this.pausePane, this.gameOverPane);

        this.getStage().setScene(new Scene(gruppo));
        this.getStage().show();
    }

    /*
     * Instantiate the pause pane and all the sub-object, like button and pane, and
     * set size, style, position and text of all
     */
    public void createPausePane() {
        this.pausePane.setVisible(false);
        this.pausePane.setPrefSize(ViewSwitcher.getWindowWidth() / 1.5, ViewSwitcher.getWindowHeight() / 1.5);
        this.pausePane.setStyle(
                "-fx-background-color: white; -fx-border-width: 2; -fx-border-color: black");
        this.pausePane.relocate(((ViewSwitcher.getWindowWidth() -
                this.pausePane.getPrefWidth()) / 2),
                ((ViewSwitcher.getWindowHeight() - this.pausePane.getPrefHeight()) / 2));

        Button buttonRiprendi = new Button("Resume");
        Button buttonRicomincia = new Button("Restart");
        Button buttonMenu = new Button("Menu");
        Pane dialogPaneRestart = new Pane();
        Button dialogRestartYes = new Button("Yes");
        Button dialogRestartNo = new Button("No");
        Label dialogRestartLabel1 = new Label("Restart?");
        Label dialogRestartLabel2 = new Label("(The current game will be overwritten)");
        Pane dialogPaneMenu = new Pane();
        Button dialogMenuYes = new Button("Save and quit");
        Button dialogMenuNo = new Button("Quit");
        Button dialogMenuBack = new Button("Back");
        Label dialogMenuLabel1 = new Label("Quit and go to main menu?");
        Label dialogMenuLabel2 = new Label("(Choose whether to save the game)");

        buttonRiprendi.setPrefSize(80, 40);
        buttonRicomincia.setPrefSize(80, 40);
        buttonMenu.setPrefSize(80, 40);
        dialogPaneRestart.setPrefSize(300, 200);
        dialogRestartYes.setPrefSize(90, 30);
        dialogRestartNo.setPrefSize(90, 30);
        dialogRestartLabel1.setPrefSize(80, 30);
        dialogRestartLabel2.setPrefSize(300, 30);
        dialogRestartLabel1.setAlignment(Pos.BASELINE_CENTER);
        dialogRestartLabel2.setAlignment(Pos.BASELINE_CENTER);
        dialogPaneMenu.setPrefSize(300, 200);
        dialogMenuYes.setPrefSize(90, 30);
        dialogMenuNo.setPrefSize(90, 30);
        dialogMenuBack.setPrefSize(90, 30);
        dialogMenuLabel1.setPrefSize(300, 30);
        dialogMenuLabel2.setPrefSize(300, 30);
        dialogMenuLabel1.setAlignment(Pos.BASELINE_CENTER);
        dialogMenuLabel2.setAlignment(Pos.BASELINE_CENTER);

        buttonRiprendi.relocate((((this.pausePane.getPrefWidth() - 80) / 2) - 80) / 2,
                (this.pausePane.getPrefHeight() - 40) / 2);
        buttonRicomincia.relocate(((this.pausePane.getPrefWidth() - 80) / 2),
                (this.pausePane.getPrefHeight() - 40) / 2);
        buttonMenu.relocate(
                this.pausePane.getPrefWidth() - 80 - ((((this.pausePane.getPrefWidth() - 80) / 2) - 80) / 2),
                (this.pausePane.getPrefHeight() - 40) / 2);

        dialogPaneRestart.relocate((this.pausePane.getPrefWidth() - dialogPaneRestart.getPrefWidth()) / 2,
                (this.pausePane.getPrefHeight() - dialogPaneRestart.getPrefHeight()) / 2);

        dialogRestartYes.relocate(
                (dialogPaneRestart.getPrefWidth() - dialogRestartYes.getPrefWidth() - dialogRestartNo.getPrefWidth())
                        / 3,
                (dialogPaneRestart.getPrefHeight() - dialogRestartYes.getPrefHeight()) / 1.4);
        dialogRestartNo.relocate(
                (dialogPaneRestart.getPrefWidth() - dialogRestartNo.getPrefWidth()
                        - ((dialogPaneRestart.getPrefWidth() - dialogRestartYes.getPrefWidth()
                                - dialogRestartNo.getPrefWidth() - 40)) / 2),
                (dialogPaneRestart.getPrefHeight() - dialogRestartYes.getPrefHeight()) / 1.4);

        dialogRestartLabel1.relocate((dialogPaneRestart.getPrefWidth() - dialogRestartLabel1.getPrefWidth()) / 2,
                (dialogPaneRestart.getPrefHeight() - dialogRestartNo.getPrefHeight()) / 5);

        dialogRestartLabel2.relocate((dialogPaneRestart.getPrefWidth() - dialogRestartLabel2.getPrefWidth()) / 2, 60);

        dialogPaneMenu.relocate((this.pausePane.getPrefWidth() - dialogPaneRestart.getPrefWidth()) / 2,
                (this.pausePane.getPrefHeight() - dialogPaneRestart.getPrefHeight()) / 2);

        dialogMenuYes.relocate(
                (dialogPaneRestart.getPrefWidth() - dialogRestartYes.getPrefWidth() - dialogRestartNo.getPrefWidth())
                        / 3,
                (dialogPaneRestart.getPrefHeight() - dialogRestartYes.getPrefHeight()) / 1.5);

        dialogMenuNo.relocate(
                (dialogPaneRestart.getPrefWidth() - dialogRestartNo.getPrefWidth()
                        - ((dialogPaneRestart.getPrefWidth() - dialogRestartYes.getPrefWidth()
                                - dialogRestartNo.getPrefWidth() - 40)) / 2),
                (dialogPaneRestart.getPrefHeight() - dialogRestartYes.getPrefHeight()) / 1.5);

        dialogMenuBack
                .relocate(
                        (dialogPaneMenu.getPrefWidth() - dialogMenuBack.getPrefWidth()) / 2,
                        (dialogPaneMenu.getPrefHeight() - dialogMenuYes.getPrefHeight()) / 1.1);

        dialogMenuLabel1.relocate((dialogPaneMenu.getPrefWidth() - dialogMenuLabel1.getPrefWidth()) / 2,
                (dialogPaneMenu.getPrefHeight() - dialogRestartNo.getPrefHeight()) / 5);

        dialogMenuLabel2.relocate((dialogPaneMenu.getPrefWidth() - dialogMenuLabel2.getPrefWidth()) / 2, 60);

        dialogPaneRestart.setVisible(false);
        dialogPaneMenu.setVisible(false);
        dialogPaneRestart.setStyle("-fx-background-color: white; -fx-border-width: 2; -fx-border-color: black");
        dialogPaneMenu.setStyle("-fx-background-color: white; -fx-border-width: 2; -fx-border-color: black");

        dialogPaneRestart.getChildren().addAll(dialogRestartYes, dialogRestartNo, dialogRestartLabel1,
                dialogRestartLabel2);
        dialogPaneMenu.getChildren().addAll(dialogMenuYes, dialogMenuNo, dialogMenuBack, dialogMenuLabel1,
                dialogMenuLabel2);
        this.pausePane.getChildren().addAll(buttonRiprendi, buttonRicomincia, buttonMenu, dialogPaneRestart,
                dialogPaneMenu);

        this.setListenersPausePane(buttonMenu, buttonRiprendi, buttonRicomincia, dialogRestartYes, dialogRestartNo,
                dialogPaneRestart, dialogMenuBack, dialogMenuYes, dialogMenuNo, dialogPaneMenu);

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
    private void setListenersPausePane(Button btnMenu, Button btnResume, Button btnRestart, Button btnDialogY,
            Button btnDialogN, Pane dialogPane, Button btnMenuBack, Button btnMenuY, Button btnMenuN,
            Pane dialogPaneMenu) {
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
            ViewSwitcher.getInstance().switchView(getStage(), ViewType.HOME);
        });

        btnMenuY.setOnMouseClicked(e -> {
            try {
                JsonUtils.addElement(new Pair<String, Object>(JsonUtils.MATCH_SCORE, this.score), JsonUtils.MATCH_FILE);
                JsonUtils.addElement(new Pair<String, Object>(JsonUtils.MATCH_ON_GOING, true), JsonUtils.MATCH_FILE);
                JsonUtils.addElement(new Pair<String, Object>(JsonUtils.GRID_SIZE, grid.getGridSize()), JsonUtils.MATCH_FILE);

                if(JsonUtils.jsonExist(JsonUtils.BEST_SCORE_FILE)){
                    if (JsonUtils.ifDataExist(String.valueOf(grid.getGridSize()), JsonUtils.BEST_SCORE_FILE)) {
                        Integer best_score = (Integer) JsonUtils.loadData(String.valueOf(grid.getGridSize()), JsonUtils.BEST_SCORE_FILE);
                        if (best_score < this.score) {
                            JsonUtils.addElement(new Pair<String,Object>(String.valueOf(grid.getGridSize()), this.score), JsonUtils.BEST_SCORE_FILE);
                        }
                    } else {
                        JsonUtils.addElement(new Pair<String,Object>(String.valueOf(grid.getGridSize()), this.score), JsonUtils.BEST_SCORE_FILE);
                    }
                } else {
                    JsonUtils.addElement(new Pair<String,Object>(String.valueOf(grid.getGridSize()), this.score), JsonUtils.BEST_SCORE_FILE);
                }

                JSONArray blocksArray = new JSONArray();

                for (GridBlock gridBlock : this.grid) {
                    JSONObject block = new JSONObject();
                    block.put("X", gridBlock.getGridX());
                    block.put("Y", gridBlock.getGridY());
                    if (gridBlock.getFill() != null) {
                        block.put("color", gridBlock.getFill().getColor());
                    } else {
                        block.put("color", "null");
                    }

                    blocksArray.put(block);
                }

                JsonUtils.addElement(new Pair<String, Object>(JsonUtils.GRID_COMPOSITION, blocksArray), JsonUtils.MATCH_FILE);

            } catch (IOException e1) {
                e1.printStackTrace();
            }

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
                    this.score = grid.controlIfLinesCompleted(this.score);
                    /*
                     * TO-DO
                     */
                    GameSoundSystem.getInstance().setAudioClip(SoundType.RIGHT_BLOCK_POSITION,
                            GameSoundSystem.getInstance().getVolumeObject().getVolumeValue());
                    GameSoundSystem.getInstance().playAudioClip();
                    blocksAvalaible.remove(block);

                    this.labelScore.setText("Score: " + this.score);
                } else {
                    GameSoundSystem.getInstance().setAudioClip(SoundType.WRONG_BLOCK_POSITION,
                            GameSoundSystem.getInstance().getVolumeObject().getVolumeValue());
                    GameSoundSystem.getInstance().playAudioClip();
                    block.returnToStart();
                }

            } else {
                GameSoundSystem.getInstance().setAudioClip(SoundType.WRONG_BLOCK_POSITION,
                        GameSoundSystem.getInstance().getVolumeObject().getVolumeValue());
                GameSoundSystem.getInstance().playAudioClip();
                block.returnToStart();
            }
            if (blocksAvalaible.size() == 0) {
                createNewPuzzles();
            }
            if (!blocksAvalaible.checkIfBlocksCanBePlaced(grid, grid.getGridSize())) {
                GameSoundSystem.getInstance().setAudioClip(SoundType.GAME_OVER,
                        GameSoundSystem.getInstance().getVolumeObject().getVolumeValue());
                GameSoundSystem.getInstance().playAudioClip();
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

        this.gameOverPane.setVisible(false);

        this.gameOverPane.setStyle(
                "-fx-background-color: white; -fx-border-width: 2; -fx-border-color: black");

        this.gameOverPane.setPrefSize(400, 300);

        this.gameOverPane.relocate((ViewSwitcher.getWindowWidth() - gameOverPane.getPrefWidth()) / 2,
                (ViewSwitcher.getWindowHeight() - gameOverPane.getPrefHeight()) / 2);

        Button buttonBackToMenu = new Button("Back to menu");
        Label labelScore = new Label(this.labelScore.getText());
        Label labelGameOver = new Label("GAME OVER");

        labelScore.setFont(new Font(null, 30));
        labelScore.setPrefSize(gameOverPane.getPrefWidth(), 40);
        labelScore.setAlignment(Pos.BASELINE_CENTER);

        labelGameOver.setFont(new Font(null, 50));
        labelGameOver.setPrefSize(gameOverPane.getPrefWidth(), 50);
        labelGameOver.setAlignment(Pos.BASELINE_CENTER);

        labelScore.relocate(0, (gameOverPane.getPrefHeight() - labelScore.getPrefHeight()) / 2.7);
        labelGameOver.relocate(0, (gameOverPane.getPrefHeight() - labelScore.getPrefHeight()) / 7);
        buttonBackToMenu.setPrefSize(100, 40);
        buttonBackToMenu.relocate((gameOverPane.getPrefWidth() - buttonBackToMenu.getPrefWidth()) / 2,
                (gameOverPane.getPrefHeight() - buttonBackToMenu.getPrefHeight()) / 1.5);

        this.gameOverPane.getChildren().addAll(buttonBackToMenu, labelScore, labelGameOver);

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
                            "-fx-background-color: white; -fx-border-width: 2; -fx-border-color: black; -fx-border-radius: 3; -fx-border-insets: -2");
                } else {
                    gridBlock.setStyle(
                            "-fx-background-color: " + gridBlock.getFill().getColor()
                                    + "; -fx-border-width: 2; -fx-border-color: black; -fx-border-radius: 3; -fx-border-insets: -2");
                }

                this.gridPane.add(gridBlock, gridBlock.getGridX(), gridBlock.getGridY());
            }
            // then the grid is empty so create panes
        } else {
            for (int RowIndex = 0; RowIndex < grid.getGridSize(); RowIndex++) {
                for (int ColumnIndex = 0; ColumnIndex < grid.getGridSize(); ColumnIndex++) {
                    GridBlock aPane = new GridBlock(ColumnIndex, RowIndex, null);

                    aPane.setPrefHeight(grid.getGridCellSize());
                    aPane.setPrefWidth(grid.getGridCellSize());
                    aPane.setStyle(
                            "-fx-background-color: white; -fx-border-width: 2; -fx-border-color: black; -fx-border-radius: 3; -fx-border-insets: -2");

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
        this.upperPane.setPrefSize(ViewSwitcher.getWindowWidth(),
                ((ViewSwitcher.getWindowHeight() - this.getGridWidth()) / 2));
        this.bottomPane.setPrefSize(ViewSwitcher.getWindowWidth(),
                ((ViewSwitcher.getWindowHeight() - this.getGridWidth()) / 2));
        this.leftPane.setPrefSize(((ViewSwitcher.getWindowWidth() - this.getGridWidth()) / 2),
                ViewSwitcher.getWindowHeight());
        this.rightPane.setPrefSize(((ViewSwitcher.getWindowWidth() - this.getGridWidth()) / 2),
                ViewSwitcher.getWindowHeight());
        this.upLeftSpawn.setPrefSize(GameView.spawnPanelsWidth, GameView.spawnPanelsWidth);
        this.downLeftSpawn.setPrefSize(GameView.spawnPanelsWidth, GameView.spawnPanelsWidth);
        this.upRightSpawn.setPrefSize(GameView.spawnPanelsWidth, GameView.spawnPanelsWidth);
        this.downRightSpawn.setPrefSize(GameView.spawnPanelsWidth, GameView.spawnPanelsWidth);
    }

    /**
     * This method is used to set the style of the main panels
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
                (((ViewSwitcher.getWindowWidth() - this.getGridWidth()) / 2) - GameView.spawnPanelsWidth) / 2,
                (ViewSwitcher.getWindowHeight() - GameView.spawnPanelsWidth - GameView.spawnPanelsWidth
                        - GameView.GAP_BETWEEN_SPAWN_PANELS) / 2);
        this.downLeftSpawn.relocate(
                (((ViewSwitcher.getWindowWidth() - this.getGridWidth()) / 2) - GameView.spawnPanelsWidth) / 2,
                ViewSwitcher.getWindowHeight() - GameView.spawnPanelsWidth - (ViewSwitcher.getWindowHeight()
                        - GameView.spawnPanelsWidth - GameView.spawnPanelsWidth - GameView.GAP_BETWEEN_SPAWN_PANELS)
                        / 2);
        this.upRightSpawn.relocate(
                (ViewSwitcher.getWindowWidth() - GameView.spawnPanelsWidth)
                        - (((ViewSwitcher.getWindowWidth() - this.getGridWidth()) / 2) - GameView.spawnPanelsWidth)
                                / 2,
                (ViewSwitcher.getWindowHeight() - GameView.spawnPanelsWidth - GameView.spawnPanelsWidth
                        - GameView.GAP_BETWEEN_SPAWN_PANELS) / 2);
        this.downRightSpawn.relocate(
                (ViewSwitcher.getWindowWidth() - GameView.spawnPanelsWidth)
                        - (((ViewSwitcher.getWindowWidth() - this.getGridWidth()) / 2) - GameView.spawnPanelsWidth)
                                / 2,
                ViewSwitcher.getWindowHeight() - GameView.spawnPanelsWidth - (ViewSwitcher.getWindowHeight()
                        - GameView.spawnPanelsWidth - GameView.spawnPanelsWidth - GameView.GAP_BETWEEN_SPAWN_PANELS)
                        / 2);

        this.labelCoin.relocate((((ViewSwitcher.getWindowWidth() - this.getGridWidth()) / 2)),
                ((ViewSwitcher.getWindowHeight() - GameView.spawnPanelsWidth - GameView.spawnPanelsWidth
                        - GameView.GAP_BETWEEN_SPAWN_PANELS) / 2)
                        - 40);
        this.labelScore.relocate(700,
                ((ViewSwitcher.getWindowHeight() - GameView.spawnPanelsWidth - GameView.spawnPanelsWidth
                        - GameView.GAP_BETWEEN_SPAWN_PANELS) / 2)
                        - 40);
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