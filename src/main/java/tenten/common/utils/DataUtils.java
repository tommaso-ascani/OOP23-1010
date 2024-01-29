package tenten.common.utils;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import javafx.util.Pair;
import tenten.model.items.GameGrid;
import tenten.model.items.GridBlock;

import java.util.logging.Logger;

/**
 * Class that manage saving/loading saved match data from/to json file.
 */
public final class DataUtils {

    private static final Integer GRID_CELL_SIZE_IF_NUMBER_OF_CELL_5 = 45;

    private static final Integer GRID_CELL_SIZE_IF_NUMBER_OF_CELL_10 = 35;

    private static final Integer GRID_CELL_SIZE_IF_NUMBER_OF_CELL_15 = 30;

    private static final Integer GRID_CELL_SIZE_IF_NUMBER_OF_CELL_20 = 25;

    private static final Integer GRID_CELL_SIZE_IS_5 = 5;

    private static final Integer GRID_CELL_SIZE_IS_10 = 10;

    private static final Integer GRID_CELL_SIZE_IS_15 = 15;

    private static final Integer GRID_CELL_SIZE_IS_20 = 20;

    private static final String COLOR_STRING_KEY_JSON = "color";

    /**
     * Game grid size.
     */
    private static Integer gridSize;

    /**
     * Deafult constructor.
     */
    private DataUtils() {
    }

    /**
     * Method to save all match data (score, grid) in the json file.
     * 
     * @param score to be saved.
     * @param grid  to be saved.
     */
    public static void saveMatchData(final Integer score, final GameGrid<GridBlock> grid) {
        try {
            JsonUtils.flushJson(JsonUtils.MATCH_FILE);

            JsonUtils.addElement(new Pair<String, Object>(JsonUtils.MATCH_SCORE, score), JsonUtils.MATCH_FILE);
            JsonUtils.addElement(new Pair<String, Object>(JsonUtils.GRID_SIZE, grid.getGridSize()),
                    JsonUtils.MATCH_FILE);

            if (JsonUtils.ifDataExist(String.valueOf(grid.getGridSize()), JsonUtils.BEST_SCORE_FILE)) {
                final Integer bestScore = (Integer) JsonUtils.loadData(String.valueOf(grid.getGridSize()),
                        JsonUtils.BEST_SCORE_FILE);
                if (bestScore < score) {
                    JsonUtils.addElement(new Pair<String, Object>(String.valueOf(grid.getGridSize()), score),
                            JsonUtils.BEST_SCORE_FILE);
                }
            } else {
                if (score > 0) {
                    JsonUtils.addElement(new Pair<String, Object>(String.valueOf(grid.getGridSize()), score),
                            JsonUtils.BEST_SCORE_FILE);
                }
            }

            final JSONArray blocksArray = new JSONArray();

            for (final GridBlock gridBlock : grid) {
                final JSONObject block = new JSONObject();
                block.put("X", gridBlock.getGridX());
                block.put("Y", gridBlock.getGridY());
                if (gridBlock.getBackgroundColor() != ThemeUtils.getSelectedTheme().getColorGrid()) {
                    block.put(DataUtils.COLOR_STRING_KEY_JSON, gridBlock.getBackgroundColor());
                } else {
                    block.put(DataUtils.COLOR_STRING_KEY_JSON, "null");
                }

                blocksArray.put(block);
            }

            JsonUtils.addElement(new Pair<String, Object>(JsonUtils.GRID_COMPOSITION, blocksArray),
                    JsonUtils.MATCH_FILE);
        } catch (IOException exc) {
            final Logger log = Logger.getLogger(DataUtils.class.getName());
            log.fine("Error on match data saving!");
        }
    }

    /**
     * Method to save the best score in this specified grid size, in the json file.
     * 
     * @param score to be saved.
     * @param size  to be saved.
     */
    public static void saveBestScore(final Integer score, final String size) {
        try {
            if (JsonUtils.ifDataExist(size, JsonUtils.BEST_SCORE_FILE)) {
                final Integer bestScore = (Integer) JsonUtils.loadData(size, JsonUtils.BEST_SCORE_FILE);
                if (bestScore < score) {
                    JsonUtils.addElement(new Pair<String, Object>(size, score), JsonUtils.BEST_SCORE_FILE);
                }
            } else {
                if (score > 0) {
                    JsonUtils.addElement(new Pair<String, Object>(size, score), JsonUtils.BEST_SCORE_FILE);
                }
            }
        } catch (IOException exc) {
            final Logger log = Logger.getLogger(DataUtils.class.getName());
            log.fine("Error on best score saving!");
        }
    }

    /**
     * Method to save in the json file the amount of coins gained.
     * 
     * @param coins to be saved.
     */
    public static void saveCoins(final Integer coins) {
        try {
            JsonUtils.addElement(new Pair<String, Object>(JsonUtils.COINS, coins), JsonUtils.GAME_DATA_FILE);
        } catch (IOException exc) {
            final Logger log = Logger.getLogger(DataUtils.class.getName());
            log.fine("Error on coins saving!");
        }
    }

    /**
     * Method to load the amount of coins from the json file.
     * 
     * @return Integer.
     */
    public static Integer loadCoins() {
        try {
            if (JsonUtils.ifDataExist(JsonUtils.COINS, JsonUtils.GAME_DATA_FILE)) {
                return (Integer) JsonUtils.loadData(JsonUtils.COINS, JsonUtils.GAME_DATA_FILE);
            }
        } catch (IOException exc) {
            final Logger log = Logger.getLogger(DataUtils.class.getName());
            log.fine("Error on coins loading!");
        }
        return 0;
    }

    /**
     * Method to load the score for the pending match from the json file.
     * 
     * @return Integer
     */
    public static Integer loadScore() {
        try {
            if (JsonUtils.ifDataExist(JsonUtils.MATCH_SCORE, JsonUtils.MATCH_FILE)) {
                return (Integer) JsonUtils.loadData(JsonUtils.MATCH_SCORE, JsonUtils.MATCH_FILE);
            }
        } catch (IOException exc) {
            final Logger log = Logger.getLogger(DataUtils.class.getName());
            log.fine("Error on score loading!");
        }
        return 0;
    }

    /**
     * Method to load the grid for thew pending match from the json file.
     * 
     * @return GameGrid
     */
    public static GameGrid<GridBlock> loadGrid() {

        GameGrid<GridBlock> grid = new GameGrid<>(gridSize);

        try {
            if (JsonUtils.ifDataExist(JsonUtils.GRID_COMPOSITION, JsonUtils.MATCH_FILE)) {

                grid = new GameGrid<>((Integer) JsonUtils.loadData(JsonUtils.GRID_SIZE, JsonUtils.MATCH_FILE));

                DataUtils.setGridCellSizeInner(grid);

                final JSONArray a = JsonUtils.loadDataArray(JsonUtils.GRID_COMPOSITION, JsonUtils.MATCH_FILE);

                for (int i = 0; i < a.length(); i++) {

                    String color;

                    if ("null".equals(a.getJSONObject(i).get(DataUtils.COLOR_STRING_KEY_JSON))) {
                        color = ThemeUtils.getSelectedTheme().getColorGrid();
                    } else {
                        color = (String) a.getJSONObject(i).get(DataUtils.COLOR_STRING_KEY_JSON);
                    }

                    final GridBlock aPane = new GridBlock((Integer) a.getJSONObject(i).get("X"),
                            (Integer) a.getJSONObject(i).get("Y"),
                            color);

                    grid.add(aPane);
                }
            } else {
                DataUtils.setGridCellSizeInner(grid);
            }
        } catch (IOException exc) {
            final Logger log = Logger.getLogger(DataUtils.class.getName());
            log.fine("Error on grid loading!");
        }

        return grid;
    }

    private static void setGridCellSizeInner(final GameGrid<GridBlock> grid) {
        if (grid.getGridSize().equals(DataUtils.GRID_CELL_SIZE_IS_5)) {
            grid.setGridCellSize(DataUtils.GRID_CELL_SIZE_IF_NUMBER_OF_CELL_5);
        } else if (grid.getGridSize().equals(DataUtils.GRID_CELL_SIZE_IS_10)) {
            grid.setGridCellSize(DataUtils.GRID_CELL_SIZE_IF_NUMBER_OF_CELL_10);
        } else if (grid.getGridSize().equals(DataUtils.GRID_CELL_SIZE_IS_15)) {
            grid.setGridCellSize(DataUtils.GRID_CELL_SIZE_IF_NUMBER_OF_CELL_15);
        } else if (grid.getGridSize().equals(DataUtils.GRID_CELL_SIZE_IS_20)) {
            grid.setGridCellSize(DataUtils.GRID_CELL_SIZE_IF_NUMBER_OF_CELL_20);
        } else {
            final Logger log = Logger.getLogger(DataUtils.class.getName());
            log.fine("Error on sizing cell grid");
        }
    }

    /**
     * Method to get gridSize.
     * 
     * @return gridSize
     */
    public static Integer getGridSize() {
        return gridSize;
    }

    /**
     * Method to set gridSize.
     * 
     * @param gridSize gridSize
     */
    public static void setGridSize(final Integer gridSize) {
        DataUtils.gridSize = gridSize;
    }
}
