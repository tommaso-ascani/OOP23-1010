package oop23_1010;

import oop23_1010.core.GameEngineImpl;

public class App {

    public static void main(String[] args) {
        GameEngineImpl engine = new GameEngineImpl();
        engine.setup();
        engine.mainLoop();
    }
}
