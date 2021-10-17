package com.roguelike_sandbox.audio;

public enum MusicEffect {

    WATER_THEME("water_theme_edit", 33.65f, 100.5f);

    public final String NAME;
    public final float START, STOP;

    MusicEffect(String NAME, float START, float STOP) {
        this.NAME = NAME;
        this.START = START;
        this.STOP = STOP;
    }
}
