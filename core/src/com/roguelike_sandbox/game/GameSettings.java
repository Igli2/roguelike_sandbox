package com.roguelike_sandbox.game;

public class GameSettings {

    private float soundVolume;
    private float musicVolume;

    public GameSettings(float soundVolume, float musicVolume) {
        this.soundVolume = soundVolume;
        this.musicVolume = musicVolume;
    }

    public void setSoundVolume(float soundVolume) {
        this.soundVolume = soundVolume;
    }

    public float getSoundVolume() {
        return soundVolume;
    }

    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
    }

    public float getMusicVolume() {
        return musicVolume;
    }
}