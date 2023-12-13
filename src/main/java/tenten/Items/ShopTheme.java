package tenten.items;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import tenten.types.ThemeType;
import tenten.view.View;

/**
 * Class that extend VBox, used to represent a theme item in the shop.
 */
public class ShopTheme extends VBox {

    private ThemeType theme;

    private Boolean purchased;

    private Label descriptionLabel;

    private HBox viewPane;

    private Label costLabel;

    private static final Integer HEIGHT = 200;

    private static final Integer VIEW_PANE_HEIGHT = 120;

    private static final Integer SPACING_HBOX = 5;

    private static final Integer FONT_SIZE_LABEL = 20;

    private static final Integer NUMBER_OF_BLOCK_TYPE_COLOR = 9;

    private static final Integer WIDTH_PANE_COLORS = 155;

    /**
     * Initialize new ShopThemeItem object.
     * 
     * @param theme
     * @param purchased
     * @param windowWidth
     */
    public ShopTheme(final String theme, final Boolean purchased, final Double windowWidth) {

        for (ThemeType themeType : ThemeType.values()) {
            if (themeType.name().equals(theme)) {
                this.theme = themeType;
            }
        }
        this.setPrefHeight(ShopTheme.HEIGHT);
        this.setFillWidth(true);

        this.viewPane = new HBox();
        this.viewPane.setPrefSize(View.WINDOW_WIDTH - ShopTheme.HEIGHT, ShopTheme.VIEW_PANE_HEIGHT);
        this.viewPane.setSpacing(ShopTheme.SPACING_HBOX);
        this.viewPane.setAlignment(Pos.CENTER);
        this.viewPane.setStyle("-fx-padding: 15");

        this.costLabel = new Label();
        this.costLabel.setFont(new Font(ShopTheme.FONT_SIZE_LABEL));
        this.costLabel.setPrefSize(View.WINDOW_WIDTH, ShopTheme.VIEW_PANE_HEIGHT / 3);
        this.costLabel.setAlignment(Pos.CENTER);
        this.costLabel.setStyle("-fx-padding: 4,4,4,4");

        this.descriptionLabel = new Label(this.theme.getName());
        this.descriptionLabel.setPrefSize(View.WINDOW_WIDTH, ShopTheme.VIEW_PANE_HEIGHT / 3);
        this.descriptionLabel.setFont(new Font(ShopTheme.FONT_SIZE_LABEL));
        this.descriptionLabel.setAlignment(Pos.CENTER);

        for (int i = 1; i <= ShopTheme.NUMBER_OF_BLOCK_TYPE_COLOR; i++) {
            Pane pane = new Pane();
            switch (i) {
                case 1:
                    pane.setStyle(
                            "-fx-background-color: " + this.theme.getColorBackground() + "; -fx-border-color: black");
                    break;
                case 2:
                    pane.setStyle("-fx-background-color: " + this.theme.getColorGrid() + "; -fx-border-color: black");
                    break;
                case 3:
                    pane.setStyle("-fx-background-color: " + this.theme.getColor1x1() + "; -fx-border-color: black");
                    break;
                case 4:
                    pane.setStyle("-fx-background-color: " + this.theme.getColor2x2() + "; -fx-border-color: black");
                    break;
                case 5:
                    pane.setStyle("-fx-background-color: " + this.theme.getColor3x3() + "; -fx-border-color: black");
                    break;
                case 6:
                    pane.setStyle(
                            "-fx-background-color: " + this.theme.getColor2x1or1x2() + "; -fx-border-color: black");
                    break;
                case 7:
                    pane.setStyle(
                            "-fx-background-color: " + this.theme.getColor3x1or1x3() + "; -fx-border-color: black");
                    break;
                case 8:
                    pane.setStyle(
                            "-fx-background-color: " + this.theme.getColor4x1or1x4() + "; -fx-border-color: black");
                    break;
                case 9:
                    pane.setStyle(
                            "-fx-background-color: " + this.theme.getColor5x1or1x5() + "; -fx-border-color: black");
                    break;
                default:
                    break;
            }
            pane.setPrefWidth(ShopTheme.WIDTH_PANE_COLORS);
            this.viewPane.getChildren().add(pane);
        }
        this.purchased = purchased;

        this.setStyle("-fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 10 10 10 10;");

        this.getChildren().addAll(this.descriptionLabel, this.viewPane, this.costLabel);

    }

    /**
     * Method to get if the item is purchased.
     * 
     * @return If purchased.
     */
    public Boolean getPurchased() {
        return purchased;
    }

    /**
     * Method to set if the is purchased.
     * 
     * @param purchased
     */
    public void setPurchased(final Boolean purchased) {
        this.purchased = purchased;
    }

    /**
     * Method to get the cost amount.
     * 
     * @return Cost amount.
     */
    public Label getCostLabel() {
        return costLabel;
    }

    /**
     * Method to set the cost amount.
     * 
     * @param costLabel
     */
    public void setCostLabel(final Label costLabel) {
        this.costLabel = costLabel;
    }

    /**
     * Method to get the theme.
     * 
     * @return Theme.
     */
    public ThemeType getTheme() {
        return theme;
    }

    /**
     * Method to set the theme.
     * 
     * @param theme
     */
    public void setTheme(final ThemeType theme) {
        this.theme = theme;
    }
}
