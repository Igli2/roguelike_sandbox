package com.roguelike_sandbox.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundPlayer {

    private final long soundID;
    private final Sound sound;

    public SoundPlayer(SoundEffect soundEffect, float volume) {
        sound = Gdx.audio.newSound(Gdx.files.internal(soundEffect.NAME));
        soundID = sound.play();
        if (volume < 0f || volume > 1f) {
            throw new IllegalArgumentException("Volume not valid: " + volume);
        }
        sound.setVolume(soundID, volume);
    }

    public void setVolume(float volume) {
        sound.setVolume(soundID, (float) Math.log10(volume));
    }

    public void play() {
        sound.play();
    }

    public void stop() {
        sound.stop();
        sound.dispose();
    }
}
