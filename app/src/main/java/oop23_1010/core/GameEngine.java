package oop23_1010.core;

public interface GameEngine {

	void mainLoop();

	/**
	 * Take a little nap to synch with the frame rate
	 * 
	 * @param current
	 */
	void waitForNextFrame(long cycleStartTime);

	/**
	 * Processing the input of the game got in a cycle.
	 */
	void processInput();

	/**
	 * Update the state of the game, given the amount of time elapsed wrt previous
	 * cycle.
	 * 
	 * @param elapsed
	 */
	void updateGame(long elapsed);

	/**
	 * Rendering of the game in a cycle.
	 */
	void render();

}
