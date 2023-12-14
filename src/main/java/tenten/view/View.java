package tenten.view;

import javafx.stage.Stage;

/**
 * View interface.
 */
public interface View {

    /**
     * Method that returns the current stage.
     * 
     * @return stage.
     */
    Stage getStage();

    /**
     * Method to set the new current stage.
     * 
     * @param stageToSet new stage that have to be set.
     */
    void setStage(Stage stageToSet);

    /**
     * This method is called at the view startup.
     */
    void start();
}
