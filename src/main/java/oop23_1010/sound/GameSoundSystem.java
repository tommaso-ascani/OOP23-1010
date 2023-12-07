package oop23_1010.sound;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Pair;
import oop23_1010.types.SoundType;
import oop23_1010.utils.JsonUtils;
import java.io.IOException;

/**
 * Class that represents the game sound system.
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

    public void setAudioClip(SoundType sound) {
        GameSoundSystem.aClip = new AudioClip(getClass().getResource(sound.getPath()).toExternalForm());
        GameSoundSystem.aClip.setVolume(GameSoundSystem.volume.doubleValue());

    }

    public void playAudioClip() {
        GameSoundSystem.aClip.play();
    }

    public void stopAudioClip() {
        GameSoundSystem.aClip.stop();
    }

    public void setMediaPlayer(SoundType music) {
        GameSoundSystem.media = new Media(getClass().getResource(music.getPath()).toExternalForm());
        GameSoundSystem.player = new MediaPlayer(media);
        GameSoundSystem.player.setVolume(GameSoundSystem.volume.doubleValue());
    }

    public void playMediaPlayer() {
        GameSoundSystem.player.play();
    }

    public void pauseMedia() {
        GameSoundSystem.player.pause();
    }

    public void resumeMedia() {
        GameSoundSystem.player.play();
    }

    public Double getVolume() {
        return GameSoundSystem.volume.doubleValue() * 100.0;
    }

    public Double getMaxVolume() {
        return GameSoundSystem.MAX_VOLUME;
    }

    public Double getMinVolume() {
        return GameSoundSystem.MIN_VOLUME;
    }
}
