package tenten.model.types;

/**
 * Class used to hold all the sound's path.
 */
public enum SoundType {

    /**
     * item containing the file name string of game over sound.
     */
    GAME_OVER("GameOverSound"),

    /**
     * item containing the file name string of wrong block positioning sound.
     */
    WRONG_BLOCK_POSITION("BlockWrongpositionSound"),

    /**
     * item containing the file name string of line completed sound.
     */
    LINE_COMPLETED("LineCompletedSound"),

    /**
     * item containing the file name string of right block positioning sound.
     */
    RIGHT_BLOCK_POSITION("BlockRightPositionSound"),

    /**
     * item containing the file name string of backgrouond1 sound.
     */
    BACKGROUND_01("BackgroundMusic");

    private static final String PREFIX = "/music/";
    private static final String EXTENSION = ".wav";
    private String file;

    SoundType(final String string) {
        this.file = string;
    }

    /**
     * Method to return the sound's path.
     * 
     * @return String of the path
     */
    public String getPath() {
        return PREFIX + this.file + EXTENSION;
    }
}
