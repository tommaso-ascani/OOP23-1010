package oop23_1010.view.gameView;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
    private GridPane gridPane;

    @FXML
    private Pane rightPane;

    @FXML
    private Pane bottomPane;

    @FXML
    private Pane upperPane;

    @Override
    public void init() {
        this.gridSize = HomeView.getGridSize();
        if (this.gridSize > 15) {
            this.gridCellSize = 20;
        } else {
            this.gridCellSize = 25;
        }

        this.gridPane.setStyle("-fx-vgap: " + GAP_GRID_PANE + "; -fx-hgap: " + GAP_GRID_PANE);
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
        ObservableList<Node> nodi = this.gridPane.getChildren();
        this.gridPaneWidthHeight = (this.gridSize * gridCellSize) + (this.gridSize - 1) * GAP_GRID_PANE;
        this.upperPane.setPrefSize(1280, ((720 - this.gridPaneWidthHeight) / 2));
        this.upperPane.setStyle("-fx-background-color: green");
        this.bottomPane.setPrefSize(1280, ((720 - this.gridPaneWidthHeight) / 2));
        this.bottomPane.setStyle("-fx-background-color: blue");
        this.leftPane.setPrefSize(((1280 - this.gridPaneWidthHeight) / 2), 720);
        this.leftPane.setStyle("-fx-background-color: yellow");
        this.rightPane.setPrefSize(((1280 - this.gridPaneWidthHeight) / 2), 720);
        this.rightPane.setStyle("-fx-background-color: red");

        this.gridPane.relocate((((960 - this.gridPaneWidthHeight) / 2)),
                (((540 - this.gridPaneWidthHeight) / 2)));

        System.out.println(nodi.size());
        System.out.println(this.gridPaneWidthHeight);
    }
}
