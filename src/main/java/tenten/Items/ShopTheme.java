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

    private Label costLabel;

    private static final Integer HEIGHT = 200;

    private static final Integer VIEW_PANE_HEIGHT = 120;

    private static final Integer SPACING_HBOX = 5;

    private static final Integer FONT_SIZE_LABEL = 20;

    private static final Integer NUMBER_OF_BLOCK_TYPE_COLOR = 9;

    private static final Integer WIDTH_PANE_COLORS = 155;

    private static final String STATIC_STYLE_ATTRIBUTES = "-fx-border-color: black; -fx-background-color: ";

    /**
     * Initialize new ShopThemeItem object.
     * 
     * @param theme
     * @param purchased
     */
    public ShopTheme(final String theme, final Boolean purchased) {

        for (final ThemeType themeType : ThemeType.values()) {
            if (themeType.name().equals(theme)) {
                this.theme = themeType;
            }
        }
        this.setPrefHeight(ShopTheme.HEIGHT);
        this.setFillWidth(true);

        final HBox viewPane = new HBox();
        viewPane.setPrefSize(View.WINDOW_WIDTH - ShopTheme.HEIGHT, ShopTheme.VIEW_PANE_HEIGHT);
        viewPane.setSpacing(ShopTheme.SPACING_HBOX);
        viewPane.setAlignment(Pos.CENTER);
        viewPane.setStyle("-fx-padding: 15");

        this.costLabel = new Label();
        this.costLabel.setFont(new Font(ShopTheme.FONT_SIZE_LABEL));
        this.costLabel.setPrefSize(View.WINDOW_WIDTH, ShopTheme.VIEW_PANE_HEIGHT / 3);
        this.costLabel.setAlignment(Pos.CENTER);
        this.costLabel.setStyle("-fx-padding: 4,4,4,4");

        final Label descriptionLabel = new Label(this.theme.getName());
        descriptionLabel.setPrefSize(View.WINDOW_WIDTH, ShopTheme.VIEW_PANE_HEIGHT / 3);
        descriptionLabel.setFont(new Font(ShopTheme.FONT_SIZE_LABEL));
        descriptionLabel.setAlignment(Pos.CENTER);

        for (int i = 1; i <= ShopTheme.NUMBER_OF_BLOCK_TYPE_COLOR; i++) {
            final Pane pane = new Pane();
            switch (i) {
                case 1:
                    pane.setStyle(
                            ShopTheme.STATIC_STYLE_ATTRIBUTES + this.theme.getColorBackground());
                    break;
                case 2:
                    pane.setStyle(ShopTheme.STATIC_STYLE_ATTRIBUTES + this.theme.getColorGrid());
                    break;
                case 3:
                    pane.setStyle(ShopTheme.STATIC_STYLE_ATTRIBUTES + this.theme.getColor1x1());
                    break;
                case 4:
                    pane.setStyle(ShopTheme.STATIC_STYLE_ATTRIBUTES + this.theme.getColor2x2());
                    break;
                case 5:
                    pane.setStyle(ShopTheme.STATIC_STYLE_ATTRIBUTES + this.theme.getColor3x3());
                    break;
                case 6:
                    pane.setStyle(
                            ShopTheme.STATIC_STYLE_ATTRIBUTES + this.theme.getColor2x1or1x2());
                    break;
                case 7:
                    pane.setStyle(
                            ShopTheme.STATIC_STYLE_ATTRIBUTES + this.theme.getColor3x1or1x3());
                    break;
                case 8:
                    pane.setStyle(
                            ShopTheme.STATIC_STYLE_ATTRIBUTES + this.theme.getColor4x1or1x4());
                    break;
                case 9:
                    pane.setStyle(
                            ShopTheme.STATIC_STYLE_ATTRIBUTES + this.theme.getColor5x1or1x5());
                    break;
                default:
                    break;
            }
            pane.setPrefWidth(ShopTheme.WIDTH_PANE_COLORS);
            viewPane.getChildren().add(pane);
        }
        this.purchased = purchased;

        this.setStyle("-fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 10 10 10 10;");

        this.getChildren().addAll(descriptionLabel, viewPane, this.costLabel);

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
