package oop23_1010;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javafx.scene.Node;

public class MovementTest {
    @Test
    public void testMakeDraggable() {
        Node node = new Node(){};
        Movement.makeDraggable(node);
        Assertions.assertNotNull(node.getOnMousePressed());
    }
}
