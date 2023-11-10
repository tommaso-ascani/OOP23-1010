package oop23_1010.core;

import java.util.logging.*;

import oop23_1010.graphics.Scene;

public class GameEngineImpl {

    private long period = 20; /* 20 ms = 50 frames per sec */

    private Logger logger = Logger.getLogger("GameEngine");

    private Scene view;

    public GameEngineImpl() {
    }

    public void setup() {
        // world = new World();
        // world.setBall(new Ball(new P2d(-1,-1), new V2d(1,1)));
        // world.addPickUp(new PickUpObj(new P2d(0,1)));
        // world.addPickUp(new PickUpObj(new P2d(2,0)));
        view = new Scene(600, 600);

    }

    public void mainLoop() {
        long previousCycleStartTime = System.currentTimeMillis();
        while (true) {
            long currentCycleStartTime = System.currentTimeMillis();
            long elapsed = currentCycleStartTime - previousCycleStartTime;
            processInput();
            /* move on the game state of elapsed time */
            updateGame(elapsed);
            render();
            waitForNextFrame(currentCycleStartTime);
            previousCycleStartTime = currentCycleStartTime;
        }
    }

    protected void waitForNextFrame(long cycleStartTime) {
        long dt = System.currentTimeMillis() - cycleStartTime;
        if (dt < period) {
            try {
                Thread.sleep(period - dt);
            } catch (Exception ex) {
            }
        }
    }

    protected void processInput() {
        logger.log(Level.INFO, "..process input..");
    }

    protected void updateGame(long elapsed) {
        logger.log(Level.INFO, "..update game: elapsed " + elapsed);
    }

    protected void render() {
        logger.log(Level.INFO, "..render..");
    }

}
