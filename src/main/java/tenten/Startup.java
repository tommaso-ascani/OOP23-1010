package tenten;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import tenten.types.ViewType;
import tenten.view.ViewSwitcher;

/**
 * Class used to effectively start the application by setting the icon, the
 * title and call ViewSwitcher to set the scene and show it.
 */
public final class Startup extends Application {

    @Override
    public void start(final Stage stage) throws Exception {

        stage.getIcons().add(new Image(ClassLoader.getSystemResourceAsStream("img/icon.jpg")));
        stage.setTitle("1010!");

        ViewSwitcher.getInstance().switchView(stage, ViewType.HOME);
    }
}
