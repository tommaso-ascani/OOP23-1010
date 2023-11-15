package oop23_1010.view;

import javafx.stage.Stage;
import oop23_1010.controllers.Controller;

public interface View {

    /**
     * This method initializes the view.
     */
    void init();

    /**
     * This method gets the current attached controller.
     * 
     * @return the current attached Controller object.
     */
    Controller getController();

    /**
     * This method sets the view's controller to the one passed as parameter.
     * 
     * @param controllerToAttach a Controller object which will be attached to the
     *                           current view.
     */
    void setController(Controller controllerToAttach);

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
