package com.roguelike_sandbox.character;

public enum EntityTexture {

    PLAYER("images/character/rogue like attack.png");

    public final String texture;

    EntityTexture(String name) {
        this.texture = name;
    }
}