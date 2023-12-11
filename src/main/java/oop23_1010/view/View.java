package oop23_1010.view;

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
     * Window width.
     */
    public static final int WINDOW_WIDTH = 1600;

    /**
     * Window height.
     */
    public static final int WINDOW_HEIGHT = 900; 

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
