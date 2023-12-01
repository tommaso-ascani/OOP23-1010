package oop23_1010.utils;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import oop23_1010.types.SkinType;
import oop23_1010.view.ViewSwitcher;

public class ShopSkinItem extends VBox {

    private SkinType skin;

    private Integer pos;

    private Boolean purchased;

    private Pane descriptionPane;

    private Label descriptionLabel;

    private HBox viewPane;

    private Pane costPane;

    private Label costLabel;

    public ShopSkinItem(String skin, Integer pos, Boolean purchased) {

        for (SkinType skinType : SkinType.values()) {
            if (skinType.name().equals(skin)) {
                this.skin = skinType;
            }
        }
        this.setPrefHeight(150);
        this.setFillWidth(true);

        // TODO VISUALIZE COLORS
        this.descriptionPane = new Pane();
        this.costPane = new Pane();
        this.viewPane = new HBox();
        this.viewPane.setPrefSize(ViewSwitcher.getWindowWidth() - 200, 90);
        // this.viewPane.relocate(ViewSwitcher.getWindowWidth() - 200,
        // BASELINE_OFFSET_SAME_AS_HEIGHT);

        this.costLabel = new Label();
        this.descriptionLabel = new Label(this.skin.getDescription());

        this.viewPane.setSpacing(5);
        for (int i = 1; i <= 9; i++) {
            Pane pane = new Pane();
            switch (i) {
                case 1:
                    pane.setStyle(
                            "-fx-background-color: " + this.skin.getColor_background() + "; -fx-border-color: black");
                    break;
                case 2:
                    pane.setStyle("-fx-background-color: " + this.skin.getColor_grid());
                    break;
                case 3:
                    pane.setStyle("-fx-background-color: " + this.skin.getColor_1x1());
                    break;
                case 4:
                    pane.setStyle("-fx-background-color: " + this.skin.getColor_2x2());
                    break;
                case 5:
                    pane.setStyle("-fx-background-color: " + this.skin.getColor_3x3());
                    break;
                case 6:
                    pane.setStyle("-fx-background-color: " + this.skin.getColor_2x1_1x2());
                    break;
                case 7:
                    pane.setStyle("-fx-background-color: " + this.skin.getColor_3x1_1x3());
                    break;
                case 8:
                    pane.setStyle("-fx-background-color: " + this.skin.getColor_4x1_1x4());
                    break;
                case 9:
                    pane.setStyle("-fx-background-color: " + this.skin.getColor_5x1_1x5());
                    break;
                default:
                    break;
            }
            pane.setPrefWidth(this.viewPane.getPrefWidth() / 9);
            this.viewPane.getChildren().add(pane);
        }
        this.pos = pos;
        this.purchased = purchased;

        this.setStyle(
                "-fx-border-width: 2; -fx-border-color: yellow");

        this.descriptionPane.getChildren().add(descriptionLabel);
        this.costPane.getChildren().add(costLabel);
        this.getChildren().addAll(this.descriptionPane, this.viewPane, this.costPane);

        Pane a = (Pane) this.getChildren().get(1);
    }

    public Boolean getPurchased() {
        return purchased;
    }

    public void setPurchased(Boolean purchased) {
        this.purchased = purchased;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public Label getCostLabel() {
        return costLabel;
    }

    public void setCostLabel(Label costLabel) {
        this.costLabel = costLabel;
    }

    public SkinType getSkin() {
        return skin;
    }

    public void setSkin(SkinType skin) {
        this.skin = skin;
    }
}
