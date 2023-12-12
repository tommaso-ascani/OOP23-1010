package tenten.view;

import javafx.stage.Stage;
import java.awt.*;

/**
 * Class that implements a View object.
 */
public abstract class View {

    /**
     * Current stage on which work.
     */
    private Stage currentStage;

    /**
     * Window width.
     */
    public static final int WINDOW_WIDTH = ((Double) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 6 * 5))
            .intValue();

    /**
     * Window height.
     */
    public static final int WINDOW_HEIGHT = ((Double) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 6 * 5))
            .intValue();

    /**
     * Method to get the current stage.
     * 
     * @return the current stage
     */
    public Stage getStage() {
        return this.currentStage;
    }

    /**
     * Method to set the current stage.
     * 
     * @param stageToSet the stage to set
     */
    public void setStage(final Stage stageToSet) {
        stageToSet.setOnCloseRequest(event -> {
            System.exit(0);
        });
        this.currentStage = stageToSet;
    }

    /**
     * Method executed every view load before the stage show.
     */
    public abstract void init();
}
