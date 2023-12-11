package oop23_1010;

import javafx.scene.Node;

public class Movement {

    private static double startX;
    private static double startY;

    /**
     * This method is used to make a Node draggable, assign to it listers to move it
     * around the screen.
     * 
     * @param node The node to make draggable.
     */
    public static void makeDraggable(Node node) {

        node.setOnMousePressed(e -> {
            startX = e.getSceneX() - node.getTranslateX();
            startY = e.getSceneY() - node.getTranslateY();
        });

        node.setOnMouseDragged(e -> {
            node.setTranslateX(e.getSceneX() - startX);
            node.setTranslateY(e.getSceneY() - startY);
        });
    }
}
