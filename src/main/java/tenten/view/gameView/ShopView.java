package tenten.view.gameView;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import tenten.Items.ShopTheme;
import tenten.language.GameLanguageSystem;
import tenten.types.ViewType;
import tenten.utils.JsonUtils;
import tenten.utils.ThemeUtils;
import tenten.view.View;
import tenten.view.ViewSwitcher;

/**
 * Class that implements all methods to use the shop view.
 */
public class ShopView extends View {

    /**
     * Vbox lateral space at right and left.
     */
    private static final Integer VBOX_LATERAL_SPACE = 125;

    /**
     * Vbox top and bottom space.
     */
    private static final Integer TOP_BOTTOM_SPACE = 20;

    /**
     * List of shop item.
     */
    private ArrayList<ShopTheme> shopList = new ArrayList<ShopTheme>();

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button  buttonBackToHome, 
                    buttonConfirm,
                    buttonBack;

    @FXML
    private Pane purchasePane;

    @FXML
    private Label   titleLabel, 
                    questionLabel, 
                    labelAlert;

    @FXML
    private VBox verticalBox;

    @Override
    public void init() {

        this.titleLabel.setText(GameLanguageSystem.getInstance().getLanguageType().getShop());
        this.buttonBackToHome.setText(GameLanguageSystem.getInstance().getLanguageType().getBack());
        this.labelAlert.setText(GameLanguageSystem.getInstance().getLanguageType().getAlertLabel());
        this.buttonBack.setText(GameLanguageSystem.getInstance().getLanguageType().getBack());

        // PrefSize

        this.mainPane.setPrefSize(View.WINDOW_WIDTH, View.WINDOW_HEIGHT);
        this.verticalBox.setPrefSize(this.mainPane.getPrefWidth() - ShopView.VBOX_LATERAL_SPACE,
                this.mainPane.getPrefHeight() - (ShopView.VBOX_LATERAL_SPACE * 2));

        // Relocate

        this.verticalBox.relocate((this.mainPane.getPrefWidth() / 2) - (this.verticalBox.getPrefWidth() / 2),
                (this.mainPane.getPrefHeight() / 2) - (this.verticalBox.getPrefHeight() / 2));
        this.titleLabel.relocate((this.mainPane.getPrefWidth() / 2) - (this.titleLabel.getPrefWidth() / 2),
                ShopView.TOP_BOTTOM_SPACE);
        this.buttonBackToHome.relocate((this.mainPane.getPrefWidth() / 2) - (this.buttonBackToHome.getPrefWidth() / 2),
                this.mainPane.getPrefHeight() - this.buttonBackToHome.getPrefHeight()
                        - (ShopView.TOP_BOTTOM_SPACE * 3));

        // Style

        this.mainPane.setStyle("-fx-background: " + ThemeUtils.getSelectedTheme().getColor_background());

        this.loadThemes();
        this.createPurchasePane();

    }

    /**
     * Load shop themes in the shop view.
     */
    public void loadThemes() {
        this.verticalBox.getChildren().clear();

        try {
            JSONArray a = JsonUtils.loadDataArray(JsonUtils.THEMES, JsonUtils.GAME_DATA_FILE);

            for (int i = 0; i < a.length(); i++) {
                ShopTheme temp = new ShopTheme(a.getJSONObject(i).getString("name"),
                        (Boolean) a.getJSONObject(i).get("purchased"),
                        this.mainPane.getPrefWidth());
                if (temp.getPurchased()) {
                    if (!temp.getTheme().name().equals(ThemeUtils.getSelectedTheme().name())) {
                        this.setListenerIfShopThemeItemPurchased(true, temp);
                        temp.getCostLabel()
                                .setText(GameLanguageSystem.getInstance().getLanguageType().getPurchasedNotSelected());
                    } else {
                        temp.getCostLabel()
                                .setText(GameLanguageSystem.getInstance().getLanguageType().getPurchasedSelected());
                    }
                } else {
                    temp.getCostLabel().setText(temp.getTheme().getCost().toString());
                    this.setListenerIfShopThemeItemPurchased(false, temp);
                }
                this.verticalBox.getChildren().add(temp);
                shopList.add(temp);
            }
        } catch (IOException exc) {
            System.err.println("Shop View - Error on themes loading!");
        }
    }

    /**
     * Switch to home view.
     */
    public void switchToHomeView() {
        ViewSwitcher.getInstance().switchView(getStage(), ViewType.HOME);
    }

    /**
     * Create a pop up panel asking for purchasing.
     */
    public void createPurchasePane() {
        this.purchasePane.setStyle("-fx-border-width: 2; -fx-border-color: black; -fx-background-color: "
                + ThemeUtils.getSelectedTheme().getColor_background());
        this.purchasePane.relocate((this.mainPane.getPrefWidth() - this.purchasePane.getPrefWidth()) / 2,
                (this.mainPane.getPrefHeight() - this.purchasePane.getPrefHeight()) / 2);
    }

    /**
     * Set listener on each shop item.
     * If purchased set the listener for asking to set it,
     * if not purchased se the listener for asking to purchase it.
     * 
     * @param isPurchased   if item is purchased.
     * @param shopThemeItem on which set the listener.
     */
    public void setListenerIfShopThemeItemPurchased(Boolean isPurchased, ShopTheme shopThemeItem) {
        if (isPurchased) {
            shopThemeItem.setOnMouseClicked(e -> {
                this.questionLabel
                        .setText(GameLanguageSystem.getInstance().getLanguageType().getShopQuestionSetTheme());

                this.buttonConfirm.setText(GameLanguageSystem.getInstance().getLanguageType().getSet());
                // this.buttonBack.setText("Back");

                this.buttonBack.setOnMouseClicked(b -> {
                    this.purchasePane.setVisible(false);
                });

                buttonConfirm.setOnMouseClicked(b -> {
                    ThemeUtils.setSelectedTheme(shopThemeItem.getTheme());
                    ThemeUtils.saveSelectedTheme();
                    this.purchasePane.setVisible(false);
                    ViewSwitcher.getInstance().switchView(getStage(), ViewType.SHOP);
                });
                this.purchasePane.setVisible(true);
            });
        } else {
            shopThemeItem.setOnMouseClicked(e -> {
                this.questionLabel.setText(GameLanguageSystem.getInstance().getLanguageType().getShopQuestionBuyItem() +
                        shopThemeItem.getTheme().getCost() + " "
                        + GameLanguageSystem.getInstance().getLanguageType().getCoins() + "?");
                this.questionLabel.setPrefSize(this.purchasePane.getPrefWidth(), 80);

                this.buttonConfirm.setText(GameLanguageSystem.getInstance().getLanguageType().getBuy());

                this.purchasePane.setVisible(true);

                this.buttonBack.setOnMouseClicked(b -> {
                    this.labelAlert.setVisible(false);
                    this.purchasePane.setVisible(false);
                });

                this.buttonConfirm.setOnMouseClicked(b -> {
                    Integer coinAmount;
                    try {
                        coinAmount = (Integer) JsonUtils.loadData(JsonUtils.COINS, JsonUtils.GAME_DATA_FILE);
                        if (coinAmount >= shopThemeItem.getTheme().getCost()) {
                            coinAmount = coinAmount - shopThemeItem.getTheme().getCost();
                            shopThemeItem.getTheme().setPurchased(true);
                            JsonUtils.addElement(new Pair<String, Object>(JsonUtils.COINS, coinAmount),
                                    JsonUtils.GAME_DATA_FILE);
                            ThemeUtils.saveThemes();
                            this.loadThemes();
                            this.purchasePane.setVisible(false);
                        } else {
                            this.labelAlert.setVisible(true);
                        }
                    } catch (IOException exc) {
                        System.err.println("Shop View - Error on theme purchase!");
                    }

                });
            });
        }
    }
}
