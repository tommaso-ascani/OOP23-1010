package oop23_1010.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop23_1010.types.ViewType;
import java.io.IOException;

/**
 * Class that implements methods to switch a view.
 */
public class ViewSwitcher {

    /**
     * ViewSwitcher istance.
     */
    private static ViewSwitcher instance;
    
    /**
     * Current view on which work.
     */
    private View currentView;

    /**
     * Method to get the current ViewSwitcher instance.
     * It use the singleton pattern.
     * 
     * @return the current ViewSwitcher instance.
     */
    public static ViewSwitcher getInstance() {
        if (instance == null) {
            instance = new ViewSwitcher();
        }
        return instance;
    }

    /**
     * Method to load the view style.
     * It loads the fxml file and set the new scene.
     * 
     * @param stage the stage to set the new scene.
     * @param viewType the view type to switch to.
     * @return the loaded view.
     */
    private View loadStyle(final Stage stage, final ViewType viewType) {
        FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(viewType.getPath()));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException exc) {
            System.err.println("View Switcher - Error on FXML loading!");
        }

        Scene newScene = new Scene(root, View.WINDOW_WIDTH, View.WINDOW_HEIGHT);

        stage.setScene(newScene);
        stage.getScene().getStylesheets().clear();
        View view = loader.getController();

        stage.setWidth(View.WINDOW_WIDTH);
        stage.setHeight(View.WINDOW_HEIGHT);
        stage.setResizable(false);
        stage.sizeToScene();

        view.setStage(stage);
        stage.setScene(newScene);
        return view;
    }

    /**
     * Method to switch between views.
     * 
     * @param stage the stage to set the new scene.
     * @param type the view type to switch to.
     */
    public void switchView(final Stage stage, final ViewType type) {
        currentView = this.loadStyle(stage, type);
        currentView.setStage(stage);
        currentView.init();
        stage.show();
    }
}
