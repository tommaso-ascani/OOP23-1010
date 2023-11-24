package oop23_1010.view;

import javafx.stage.Stage;

public interface View {

    /**
     * This method initializes the view.
     */
    void init();

    /**
     * This method returns the current stage.
     * 
     * @return a stage object
     */
    Stage getStage();

    /**
     * This method sets the view's stage to the one passed as parameter.
     * 
     * @param stageToSet a Stage object which will be attached to the current view.
     */
    void setStage(Stage stageToSet);

}
