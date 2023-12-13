package tenten.view;

import java.awt.Toolkit;
import javafx.stage.Stage;

/**
 * Class that implements a View object.
 */
public abstract class View {

    /**
     * Current stage on which work.
     */
    private Stage currentStage;

    /**
     * Screen resolution proportion of width.
     */
    private static final int WIDTH_PROPORTION = 6;

    /**
     * Screen resolution proportion of height.
     */
    private static final int HEIGHT_PROPORTION = 5;

    /**
     * Window width.
     */
    public static final int WINDOW_WIDTH = ((Double) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()
            / WIDTH_PROPORTION * HEIGHT_PROPORTION)).intValue();

    /**
     * Window height.
     */
    public static final int WINDOW_HEIGHT = ((Double) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()
            / WIDTH_PROPORTION * HEIGHT_PROPORTION)).intValue();

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
        this.currentStage = stageToSet;
        this.currentStage.setOnCloseRequest(event -> {
            Runtime.getRuntime().exit(0);
        });
    }

    /**
     * Method executed by every view load before the stage show.
     */
    public abstract void init();
}
