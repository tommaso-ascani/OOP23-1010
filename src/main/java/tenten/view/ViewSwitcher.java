package tenten.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tenten.model.types.ViewType;

import java.util.logging.Logger;
import java.io.IOException;

/**
 * Class that implements methods to switch a view.
 */
public class ViewSwitcher {

    /**
     * Inner class used to maintain the instance of ViewSwitcher.
     */
    static class ViewSwitcherInner {

        /**
         * ViewSwitcher istance.
         */
        static final ViewSwitcher INSTANCE = new ViewSwitcher();
    }

    /**
     * Method to get the current ViewSwitcher instance.
     * It use the singleton pattern.
     * 
     * @return the current ViewSwitcher instance.
     */
    public static ViewSwitcher getInstance() {
        return ViewSwitcherInner.INSTANCE;
    }

    /**
     * Method to load the view style.
     * It loads the fxml file and set the new scene.
     * 
     * @param stage    the stage to set the new scene.
     * @param viewType the view type to switch to.
     * @return the loaded view.
     */
    private void loadStyle(final Stage stage, final ViewType viewType) {
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(viewType.getPath()));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException exc) {
            final Logger log = Logger.getLogger(ViewSwitcher.class.getName());
            log.fine("View Switcher - Error on FXML loading!");
        }

        final Scene newScene = new Scene(root, ViewImpl.WINDOW_WIDTH, ViewImpl.WINDOW_HEIGHT);

        stage.setScene(newScene);
        stage.getScene().getStylesheets().clear();
        final ViewImpl view = loader.getController();

        stage.setWidth(ViewImpl.WINDOW_WIDTH);
        stage.setHeight(ViewImpl.WINDOW_HEIGHT);
        stage.setResizable(false);
        stage.sizeToScene();

        view.setStage(stage);
        stage.setScene(newScene);
        
        view.init();
    }

    /**
     * Method to switch between views.
     * 
     * @param stage the stage to set the new scene.
     * @param type  the view type to switch to.
     */
    public void switchView(final Stage stage, final ViewType type) {
        this.loadStyle(stage, type);
        stage.show();
    }
}
