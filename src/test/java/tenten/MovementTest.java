package tenten;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import javafx.scene.Node;

/**
 * Class that test the java class Movement.
 */
public class MovementTest {

    /**
     * Method that test if the function makeDraggable is working.
     */
    @Test
    public void testMakeDraggable() {
        Node node = new Node() {
        };
        Movement.makeDraggable(node);
        Assertions.assertNotNull(node.getOnMousePressed());
    }
}
