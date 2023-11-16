package oop23_1010.view.gameView;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import oop23_1010.view.ViewImpl;

public class GameView extends ViewImpl {

    private int gridSize;

    @FXML
    private VBox underVBox;

    @FXML
    private AnchorPane upAPane;

    @FXML
    private AnchorPane leftAPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private AnchorPane rightAPane;

    @FXML
    private AnchorPane bottomAPane;

    @FXML
    private HBox hbox;

    @Override
    public void init() {
        this.gridSize = HomeView.getGridSize();
        this.underVBox.setStyle("-fx-background-color: black");
        this.gridPane.setStyle("-fx-vgap: 5; -fx-hgap: 5");
        for (int ColumnIndex = 0; ColumnIndex < this.gridSize; ColumnIndex++) {
            for (int RowIndex = 0; RowIndex < this.gridSize; RowIndex++) {
                AnchorPane aPane = new AnchorPane();
                aPane.setPrefHeight(25);
                aPane.setPrefWidth(25);
                aPane.setStyle(
                        "-fx-background-color: white; -fx-border-width: 2; -fx-border-color: black; -fx-border-radius: 3; -fx-border-insets: -2");

                this.gridPane.add(aPane, ColumnIndex, RowIndex);
            }
        }
        AnchorPane.setTopAnchor(this.underVBox, 20.0);
        ObservableList<Node> nodi = this.gridPane.getChildren();
        System.out.println(nodi.size());
        // for (Node node : nodi) {
        // System.out.println(GridPane.getRowIndex(node));
        // System.out.println(GridPane.getColumnIndex(node));
        // node.setStyle("-fx-background-color: brown;");
        // }
    }
}
