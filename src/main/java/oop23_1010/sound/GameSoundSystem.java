package oop23_1010.sound;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.util.Pair;
import oop23_1010.types.SoundType;
import oop23_1010.utils.JsonUtils;
import java.io.IOException;

/**
 * Class that represent the game sound system.
 */
public final class GameSoundSystem {

    private static GameSoundSystem instance;
    private static AudioClip aClip;
    private static Media media;
    private static MediaPlayer player;
    private static Double volume;
    private static final Double MIN_VOLUME = 0.0;
    private static final Double MAX_VOLUME = 1.0;

    /**
     * Method to get the instace of the class. It used the Singleton
     * pattern.
     * 
     * @return GameSoundSystem
     */
    public static GameSoundSystem getInstance() {
        if (GameSoundSystem.instance == null) {
            GameSoundSystem.instance = new GameSoundSystem();
        }
        try {
            if (JsonUtils.ifDataExist(JsonUtils.VOLUME, JsonUtils.GAME_DATA_FILE)) {
                GameSoundSystem.volume = (Double) (((Integer) JsonUtils.loadData(JsonUtils.VOLUME,
                        JsonUtils.GAME_DATA_FILE)).doubleValue() / 100.0);
            } else {
                JsonUtils.addElement(new Pair<String, Object>(JsonUtils.VOLUME, (GameSoundSystem.MAX_VOLUME * 100.0)),
                        JsonUtils.GAME_DATA_FILE);
                GameSoundSystem.volume = GameSoundSystem.MAX_VOLUME;
            }
        } catch (IOException exc) {
            System.err.println("Game Sound System - Error on volume loading!");
        }
        return instance;
    }

    /**
     * Method to set the resource to play, put it in an AudioClip and set the
     * volume.
     * 
     * @param sound
     */
    public void setAudioClip(SoundType sound) {
        GameSoundSystem.aClip = new AudioClip(getClass().getResource(sound.getPath()).toExternalForm());
        GameSoundSystem.aClip.setVolume(GameSoundSystem.volume.doubleValue());

    }

    /**
     * Method to play the audio clip.
     */
    public void playAudioClip() {
        GameSoundSystem.aClip.play();
    }

    /**
     * Method to get the audio clip.
     */
    public AudioClip getAudioClip() {
        return GameSoundSystem.aClip;
    }

    /**
     * Method to stop the audio clip.
     */
    public void stopAudioClip() {
        GameSoundSystem.aClip.stop();
    }

    /**
     * Method to to set the resource to play, put it in a Media, then in a
     * MediaPlayer and set the
     * volume.
     * 
     * @param music
     */
    public void setMediaPlayer(SoundType music) {
        GameSoundSystem.media = new Media(getClass().getResource(music.getPath()).toExternalForm());
        GameSoundSystem.player = new MediaPlayer(media);
        GameSoundSystem.player.setOnEndOfMedia(new Runnable() {

            @Override
            public void run() {
                GameSoundSystem.player.seek(Duration.ZERO);
            }

        });
        GameSoundSystem.player.setVolume(GameSoundSystem.volume.doubleValue());
    }

    /**
     * Method to play the MediaPlayer.
     */
    public void playMediaPlayer() {
        GameSoundSystem.player.play();
    }

    /**
     * Method to pause the MediaPlayer.
     */
    public void pauseMedia() {
        GameSoundSystem.player.pause();
    }

    /**
     * Method to resume the MediaPlayer.
     */
    public void resumeMedia() {
        GameSoundSystem.player.play();
    }

    /**
     * Method to stop the MediaPlayer.
     */
    public void stopMedia() {
        GameSoundSystem.player.stop();
    }

    /**
     * Method to get the volume.
     */
    public Double getVolume() {
        return GameSoundSystem.volume.doubleValue() * 100.0;
    }

    /**
     * Method to get the volume's max value.
     */
    public Double getMaxVolume() {
        return GameSoundSystem.MAX_VOLUME;
    }

    /**
     * Method to get the volume's min value.
     */
    public Double getMinVolume() {
        return GameSoundSystem.MIN_VOLUME;
    }
}
