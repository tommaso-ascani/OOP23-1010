package oop23_1010;

import oop23_1010.controllers.Startup;
import oop23_1010.utils.BlockGenerator;
import oop23_1010.utils.BlockType;
import javafx.application.Application;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        Application.launch(Startup.class, args);
        // BlockGenerator.generateBlock(BlockType.BLOCK_1x5);
    }
}