package oop23_1010.sound;

public enum SoundType {

    GAME_OVER("GameOverSound"),

    WRONG_BLOCK_POSITION("BlockWrongpositionSound"),

    LINE_COMPLETED("LineCompletedSound"),

    RIGHT_BLOCK_POSITION("BlockRightPositionSound"),

    BACKGROUND_01("BackgroundMusic");

    private static final String PREFIX = "/music/";
    private static final String EXTENSION = ".wav";
    private String file;

    SoundType(final String string) {
        this.file = string;
    }

    public String getPath() {
        return PREFIX + this.file + EXTENSION;
    }
}
