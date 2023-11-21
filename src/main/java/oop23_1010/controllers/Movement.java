package oop23_1010.controllers;

import javafx.geometry.Bounds;
import javafx.scene.Node;

public class Movement {

    private static double startX;
    private static double startY;

    private static double lastMinX;
    private static double lastMaxX;
    private static double lastMinY;
    private static double lastMaxY;

    private static boolean onGrid = false;

    public static void makeDraggable(Node node) {

        node.setOnMousePressed(e -> {
            startX = e.getSceneX() - node.getTranslateX();
            startY = e.getSceneY() - node.getTranslateY();
        });

        node.setOnMouseDragged(e -> {
            node.setTranslateX(e.getSceneX() - startX);
            node.setTranslateY(e.getSceneY() - startY);
        });

        node.setOnMouseReleased(e -> {
            Bounds boundsInScene = node.localToScene(node.getBoundsInLocal());
            Movement.lastMinX = boundsInScene.getMinX();
            System.out.println("Min x: " + boundsInScene.getMinX());
            Movement.lastMinX = boundsInScene.getMinY();
            System.out.println("Min y: " + boundsInScene.getMinY());
            Movement.lastMinX = boundsInScene.getMaxX();
            System.out.println("Max x: " + boundsInScene.getMaxX());
            Movement.lastMinX = boundsInScene.getMaxY();
            System.out.println("Max y: " + boundsInScene.getMaxY());
            System.out.println("  ");
            if (onGrid) {
                node.setVisible(false);
                node.setDisable(true);
            }

        });
    }

    public static void setOnGrid() {
        onGrid = true;

    }
}
