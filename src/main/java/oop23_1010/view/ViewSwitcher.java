package oop23_1010.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ViewSwitcher {

    private static ViewSwitcher instance;
    private View currentView;

    private static final int WINDOW_WIDTH = 1280;
    private static final int WINDOW_HEIGHT = 720;

    /**
     * This method is used to get the current instance of the ViewSwitcher.
     * It use the singleton pattern.
     * 
     * @return the current instance of the ViewSwitcher
     */
    public static ViewSwitcher getInstance() {
        if (instance == null) {
            instance = new ViewSwitcher();
        }
        return instance;
    }

    /**
     * This method is used to load the style of the view.
     * It loads the fxml file and set the new scene.
     * 
     * @param stage    the stage to set the new scene
     * @param viewType the type of the view to switch to
     * @return the view loaded
     */
    private View loadStyle(final Stage stage, final ViewType viewType) {
        FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(viewType.getPath()));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene newScene = new Scene(root);

        stage.setScene(newScene);
        stage.getScene().getStylesheets().clear();
        View view = loader.getController();

        stage.setHeight(WINDOW_HEIGHT);
        stage.setWidth(WINDOW_WIDTH);
        stage.setResizable(false);

        view.setStage(stage);
        stage.setScene(newScene);
        return view;
    }

    /**
     * This method is used to switch between views.
     * 
     * @param stage the stage to set the new scene
     * @param type  the type of the view to switch to
     */
    public void switchView(final Stage stage, final ViewType type) {
        currentView = this.loadStyle(stage, type);
        currentView.setStage(stage);
        currentView.init();
        stage.show();
    }

    public static int getWindowWidth() {
        return WINDOW_WIDTH;
    }

    public static int getWindowHeight() {
        return WINDOW_HEIGHT;
    }
}
