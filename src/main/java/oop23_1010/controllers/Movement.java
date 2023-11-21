package oop23_1010.controllers;

import javafx.scene.Node;

public class Movement {

    private static double startX;
    private static double startY;

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
