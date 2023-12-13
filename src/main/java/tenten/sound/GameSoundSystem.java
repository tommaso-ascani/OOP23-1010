package tenten.sound;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.util.Pair;
import tenten.language.GameLanguageSystem;
import tenten.types.SoundType;
import tenten.utils.JsonUtils;
import java.util.logging.Logger;
import java.io.IOException;

/**
 * Class that represent the game sound system.
 */
public final class GameSoundSystem {

    private AudioClip aClip;

    private MediaPlayer player;

    private Double volume;

    private static final Double MIN_VOLUME = 0.0;

    private static final Double MAX_VOLUME = 1.0;

    /**
     * Inner class used to maintain the instance of GameSoundSystem.
     */
    static class InnerGameSoundSystem {

        /**
         * GameSoundSystem istance.
         */
        static final GameSoundSystem INSTANCE = new GameSoundSystem();
    }

    /**
     * Method to get the instace of the class. It used the Singleton
     * pattern.
     * 
     * @return GameSoundSystem
     */
    public static GameSoundSystem getInstance() {
        return InnerGameSoundSystem.INSTANCE;
    }

    /**
     * Method that control and set the filed volume if there is a saved data of it
     * or set it by default at MAX_VOLUME.
     */
    public void checkSoundData() {
        try {
            if (JsonUtils.ifDataExist(JsonUtils.VOLUME, JsonUtils.GAME_DATA_FILE)) {
                this.volume = (Double) (((Integer) JsonUtils.loadData(JsonUtils.VOLUME,
                        JsonUtils.GAME_DATA_FILE)).doubleValue() / 100.0);
            } else {
                JsonUtils.addElement(new Pair<String, Object>(JsonUtils.VOLUME, GameSoundSystem.MAX_VOLUME * 100.0),
                        JsonUtils.GAME_DATA_FILE);
                this.volume = GameSoundSystem.MAX_VOLUME;
            }
        } catch (IOException exc) {
            final Logger log = Logger.getLogger(GameLanguageSystem.class.getName());
            log.fine("Game Sound System - Error on volume loading!");
        }
    }

    /**
     * Method to set the resource to play, put it in an AudioClip and set the
     * volume.
     * 
     * @param sound
     */
    public void setAudioClip(final SoundType sound) {
        this.aClip = new AudioClip(getClass().getResource(sound.getPath()).toExternalForm());
        this.aClip.setVolume(this.volume.doubleValue());

    }

    /**
     * Method to play the audio clip.
     */
    public void playAudioClip() {
        this.aClip.play();
    }

    /**
     * Method to get the audio clip.
     * 
     * @return GameSoundSystem.aClip
     */
    public AudioClip getAudioClip() {
        return this.aClip;
    }

    /**
     * Method to stop the audio clip.
     */
    public void stopAudioClip() {
        this.aClip.stop();
    }

    /**
     * Method to to set the resource to play, put it in a Media, then in a
     * MediaPlayer and set the
     * volume.
     * 
     * @param music
     */
    public void setMediaPlayer(final SoundType music) {
        this.player = new MediaPlayer(new Media(getClass().getResource(music.getPath()).toExternalForm()));
        this.player.setOnEndOfMedia(new Runnable() {

            @Override
            public void run() {
                GameSoundSystem.getInstance().getMediaPlayer().seek(Duration.ZERO);
            }

        });
        this.player.setVolume(this.volume.doubleValue());
    }

    /**
     * Method to get the media player.
     * 
     * @return GameSoundSystem.player
     */
    public MediaPlayer getMediaPlayer() {
        return this.player;
    }

    /**
     * Method to play the MediaPlayer.
     */
    public void playMediaPlayer() {
        this.player.play();
    }

    /**
     * Method to pause the MediaPlayer.
     */
    public void pauseMedia() {
        this.player.pause();
    }

    /**
     * Method to resume the MediaPlayer.
     */
    public void resumeMedia() {
        this.player.play();
    }

    /**
     * Method to stop the MediaPlayer.
     */
    public void stopMedia() {
        this.player.stop();
    }

    /**
     * Method to get the volume.
     * 
     * @return GameSoundSystem.volume
     */
    public Double getVolume() {
        return this.volume.doubleValue() * 100.0;
    }

    /**
     * Method to get the volume's max value.
     * 
     * @return GameSoundSystem.MAX_VOLUME
     */
    public Double getMaxVolume() {
        return GameSoundSystem.MAX_VOLUME;
    }

    /**
     * Method to get the volume's min value.
     * 
     * @return GameSoundSystem.MIN_VOLUME
     */
    public Double getMinVolume() {
        return GameSoundSystem.MIN_VOLUME;
    }
}
