package com.roguelike_sandbox.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicPlayer {

    private final float startPosition;
    private final float endPosition;
    private final MusicEffect musicEffect;
    private Music music;

    public MusicPlayer(MusicEffect musicEffect, float volume) {
        this.musicEffect = musicEffect;
        this.startPosition = 0;
        this.endPosition = 0;
        initialise(volume, true);

    }

    public MusicPlayer(MusicEffect musicEffect, float volume, boolean startFromZero) {
        this.musicEffect = musicEffect;
        this.startPosition = musicEffect.START;
        this.endPosition = musicEffect.STOP;

        initialise(volume, startFromZero);
    }

    private void initialise(float volume, boolean startFromZero) {
        if (music != null) {
            if (music.isPlaying()) {
                music.stop();
            }
            music.dispose();
        }

        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/" + musicEffect.NAME + ".ogg"));
        if (!startFromZero) {
            setPosition(startPosition, volume);
        }
        music.setVolume(volume);
        music.play();
    }

    public void pause() {
        if (music != null) {
            if (!music.isPlaying()) {
                music.pause();
            }
        }
    }

    public void setVolume(float volume) {
        music.setVolume(volume);
    }

    public void play() {
        if (music != null) {
            if (!music.isPlaying()) {
                music.play();
            }
        }
    }

    public void stop() {
        if (music != null) {
            if (music.isPlaying()) {
                music.stop();
            }
        }
    }

    private void setPosition(float position, float oldVolume) {
        music.setVolume(0f);
        music.play();
        music.setPosition(position);
        music.pause();
        music.setVolume(oldVolume);
    }

    public void loopInSegments() {
        if (startPosition != 0 || endPosition != 0) {
            if (music.getPosition() >= endPosition) {
                music.setPosition(startPosition);
            }
        }
    }
}
