package tenten.common;

import javafx.scene.Node;

/**
 * Class that allow object to be dragged on view.
 */
public final class Movement {

    /**
     * Start position of the object.
     */
    private static double startX;
    private static double startY;

    /**
     * This method is used to make a Node draggable, assign to it listers to move it
     * around the screen.
     * 
     * @param node The node to make draggable.
     */
    public static void makeDraggable(final Node node) {

        node.setOnMousePressed(e -> {
            startX = e.getSceneX() - node.getTranslateX();
            startY = e.getSceneY() - node.getTranslateY();
        });

        node.setOnMouseDragged(e -> {
            node.setTranslateX(e.getSceneX() - startX);
            node.setTranslateY(e.getSceneY() - startY);
        });
    }

    /**
     * Default constructor.
     */
    private Movement() {
    }
}
