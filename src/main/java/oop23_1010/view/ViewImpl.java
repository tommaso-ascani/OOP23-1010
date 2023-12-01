package oop23_1010.view;

import javafx.stage.Stage;

public abstract class ViewImpl implements View {
    private Stage currentStage;

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
     * {@inheritDoc}}
     */
    @Override
    public abstract void init();
}
