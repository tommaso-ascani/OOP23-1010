package tenten;

import javafx.application.Application;
import java.io.IOException;

/**
 * Class that launch the application.
 */
public final class App {

    /**
     * Deafult constructor.
     */
    private App() { }

    /**
     * Application main.
     * 
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        Application.launch(Startup.class, args);
    }
}
