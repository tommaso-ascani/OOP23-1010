package oop23_1010.sound;

public class Volume {

    private static Volume instance;
    private Double volumeValue;
    private final static Double DEFAULT_VALUE = 1.0;

    public static Volume getInstance() {
        if (instance == null) {
            instance = new Volume();
        }
        return instance;
    }

    public void setVolumeValue(Double value) {
        this.volumeValue = value;
    }

    public Double getVolumeValue() {
        if (this.volumeValue == null) {
            this.volumeValue = Volume.DEFAULT_VALUE;
        }
        return this.volumeValue;
    }
}
