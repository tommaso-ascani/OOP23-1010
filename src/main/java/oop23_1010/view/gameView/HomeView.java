package oop23_1010.view.gameView;

import java.io.IOException;

import org.json.JSONArray;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import oop23_1010.types.ColorType;
import oop23_1010.utils.GameGrid;
import oop23_1010.utils.GridBlock;
import oop23_1010.utils.JsonUtils;
import oop23_1010.view.ViewImpl;
import oop23_1010.view.ViewSwitcher;
import oop23_1010.view.ViewType;

public class HomeView extends ViewImpl {

    private static int gridSize;
    private static Boolean mathcOnGoing;
    private GameGrid<GridBlock> gridLoaded = new GameGrid<>(gridSize);

    @FXML
    private ImageView imageSettings;

    @FXML
    private ImageView imageShop;

    @FXML
    private ImageView imageQuit;

    @FXML
    private Slider sliderGridWidth;

    @FXML
    private AnchorPane mainPane;

    @Override
    public void init() {
        this.sliderGridWidth.setValue(10);
        this.sliderGridWidth.setMin(5);
        this.sliderGridWidth.setMax(20);
        this.sliderGridWidth.setShowTickMarks(false);
        this.sliderGridWidth.setShowTickLabels(true);
        this.sliderGridWidth.setMajorTickUnit(5);
        this.sliderGridWidth.setMinorTickCount(0);
        this.sliderGridWidth.setSnapToTicks(true);

        // In this try catch we control if there is a saved match and if true, load the
        // saved grid in GridLoaded
        try {
            HomeView.mathcOnGoing = (Boolean) JsonUtils.loadData(JsonUtils.MATCH_ON_GOING);
            if (HomeView.mathcOnGoing) {
                JSONArray a = JsonUtils.loadGriglia(JsonUtils.GRID_COMPOSITION);
                for (int i = 0; i < a.length(); i++) {
                    ColorType color;
                    if (a.getJSONObject(i).get("color").equals("null")) {
                        color = null;
                    } else {
                        System.out.println("." + a.getJSONObject(i).get("color") + ".");
                        color = ColorType.get((String) a.getJSONObject(i).get("color"));
                    }
                    GridBlock aPane = new GridBlock((Integer) a.getJSONObject(i).get("X"),
                            (Integer) a.getJSONObject(i).get("Y"),
                            color);

                    this.gridLoaded.add(aPane);
                }
                GameView.setGrid(gridLoaded);
            }
            if (this.gridLoaded.isEmpty()) {
                System.out.println("griglia salavata nulla");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToSettingsView() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.SETTINGS);
    }

    public void switchToShopView() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.SHOP);
    }

    public void switchToPlayView() {
        HomeView.gridSize = (int) this.sliderGridWidth.getValue();
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.GAME);
    }

    public static int getGridSize() {
        return gridSize;
    }
}