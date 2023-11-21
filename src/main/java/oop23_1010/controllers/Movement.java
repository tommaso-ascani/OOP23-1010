package oop23_1010.controllers;

import javafx.geometry.Bounds;
import javafx.scene.Node;

public class Movement {

    private static double startX;
    private static double startY;

    public static void makeDraggable(Node node) {

        node.setOnMousePressed(e -> {
            startX = e.getSceneX() - node.getTranslateX();
            startY = e.getSceneY() - node.getTranslateY();

            System.out.println(node.getScene().getRoot().getChildrenUnmodifiable().get(0));
        });

        node.setOnMouseDragged(e -> {
            node.setTranslateX(e.getSceneX() - startX);
            node.setTranslateY(e.getSceneY() - startY);
        });

        node.setOnMouseReleased(e -> {
            Bounds boundsInScene = node.localToScene(node.getBoundsInLocal());
            System.out.println("Min x: " + boundsInScene.getMinX());
            System.out.println("Min y: " + boundsInScene.getMinY());
            System.out.println("Max x: " + boundsInScene.getMaxX());
            System.out.println("Max y: " + boundsInScene.getMaxY());
            System.out.println("  ");
        });
    }
}
