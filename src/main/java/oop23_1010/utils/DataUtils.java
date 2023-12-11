package oop23_1010.utils;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import javafx.util.Pair;
import oop23_1010.Items.GameGrid;
import oop23_1010.Items.GridBlock;
import oop23_1010.view.gameView.HomeView;

/**
 * Class that manage saving/loading saved match data from/to json file.
 */
public class DataUtils {

    /**
     * Method to save all match data (score, grid) in the json file.
     * 
     * @param score to be saved.
     * @param grid  to be saved.
     */
    public static void saveMatchData(Integer score, GameGrid<GridBlock> grid) {
        try {
            JsonUtils.flushJson(JsonUtils.MATCH_FILE);

            JsonUtils.addElement(new Pair<String, Object>(JsonUtils.MATCH_SCORE, score), JsonUtils.MATCH_FILE);
            JsonUtils.addElement(new Pair<String, Object>(JsonUtils.MATCH_ON_GOING, true), JsonUtils.MATCH_FILE);
            JsonUtils.addElement(new Pair<String, Object>(JsonUtils.GRID_SIZE, grid.getGridSize()),
                    JsonUtils.MATCH_FILE);

            if (JsonUtils.ifDataExist(String.valueOf(grid.getGridSize()), JsonUtils.BEST_SCORE_FILE)) {
                Integer best_score = (Integer) JsonUtils.loadData(String.valueOf(grid.getGridSize()),
                        JsonUtils.BEST_SCORE_FILE);
                if (best_score < score) {
                    JsonUtils.addElement(new Pair<String, Object>(String.valueOf(grid.getGridSize()), score),
                            JsonUtils.BEST_SCORE_FILE);
                }
            } else {
                if (score > 0) {
                    JsonUtils.addElement(new Pair<String, Object>(String.valueOf(grid.getGridSize()), score),
                            JsonUtils.BEST_SCORE_FILE);
                }
            }

            JSONArray blocksArray = new JSONArray();

            for (GridBlock gridBlock : grid) {
                JSONObject block = new JSONObject();
                block.put("X", gridBlock.getGridX());
                block.put("Y", gridBlock.getGridY());
                if (gridBlock.getFill() != null) {
                    block.put("color", gridBlock.getFill());
                } else {
                    block.put("color", "null");
                }

                blocksArray.put(block);
            }

            JsonUtils.addElement(new Pair<String, Object>(JsonUtils.GRID_COMPOSITION, blocksArray),
                    JsonUtils.MATCH_FILE);
        } catch (IOException exc) {
            System.err.println("Error on match data saving!");
        }
    }

    /**
     * Method to save the best score in this specified grid size, in the json file.
     * 
     * @param score to be saved.
     * @param size  to be saved.
     */
    public static void saveBestScore(Integer score, String size) {
        try {
            if (JsonUtils.ifDataExist(size, JsonUtils.BEST_SCORE_FILE)) {
                Integer best_score = (Integer) JsonUtils.loadData(size, JsonUtils.BEST_SCORE_FILE);
                if (best_score < score) {
                    JsonUtils.addElement(new Pair<String, Object>(size, score), JsonUtils.BEST_SCORE_FILE);
                }
            } else {
                if (score > 0) {
                    JsonUtils.addElement(new Pair<String, Object>(size, score), JsonUtils.BEST_SCORE_FILE);
                }
            }
        } catch (IOException exc) {
            System.err.println("Error on best score saving!");
        }
    }

    /**
     * Method to save in the json file the amount of coins gained.
     * 
     * @param coins to be saved.
     */
    public static void saveCoins(Integer coins) {
        try {
            JsonUtils.addElement(new Pair<String, Object>(JsonUtils.COINS, coins), JsonUtils.GAME_DATA_FILE);
        } catch (IOException exc) {
            System.err.println("Error on coins saving!");
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
            System.err.println("Error on coins loading!");
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
            System.err.println("Error on score loading!");
        }
        return 0;
    }

    /**
     * Method to load the grid for thew pending match from the json file.
     * 
     * @return GameGrid
     */
    public static GameGrid<GridBlock> loadGrid() {

        GameGrid<GridBlock> grid;

        try {
            if (JsonUtils.ifDataExist(JsonUtils.GRID_COMPOSITION, JsonUtils.MATCH_FILE)) {

                grid = new GameGrid<>((Integer) JsonUtils.loadData(JsonUtils.GRID_SIZE, JsonUtils.MATCH_FILE));

                switch (grid.getGridSize()) {
                    case 5:
                        grid.setGridCellSize(45);
                        break;

                    case 10:
                        grid.setGridCellSize(35);
                        break;

                    case 15:
                        grid.setGridCellSize(30);
                        break;

                    case 20:
                        grid.setGridCellSize(25);
                        break;

                    default:
                        System.err.println("Error on sizing cell grid");
                        break;
                }

                JSONArray a = JsonUtils.loadDataArray(JsonUtils.GRID_COMPOSITION, JsonUtils.MATCH_FILE);

                for (int i = 0; i < a.length(); i++) {

                    String color;

                    if (a.getJSONObject(i).get("color").equals("null")) {
                        color = null;
                    } else {
                        color = (String) a.getJSONObject(i).get("color");
                    }

                    GridBlock aPane = new GridBlock((Integer) a.getJSONObject(i).get("X"),
                            (Integer) a.getJSONObject(i).get("Y"),
                            color, ThemeUtils.getSelectedTheme().getColor_grid());

                    grid.add(aPane);
                }

                return grid;
            }
        } catch (IOException exc) {
            System.err.println("Error on grid loading!");
        }

        grid = new GameGrid<>(HomeView.getGridSize());

        switch (grid.getGridSize()) {
            case 5:
                grid.setGridCellSize(45);
                break;

            case 10:
                grid.setGridCellSize(35);
                break;

            case 15:
                grid.setGridCellSize(30);
                break;

            case 20:
                grid.setGridCellSize(25);
                break;

            default:
                System.err.println("Error on sizing cell grid");
                break;
        }

        return grid;
    }
}
