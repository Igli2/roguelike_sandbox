package com.roguelike_sandbox.world;

public enum TileTexture {
    DIRT(32, 32, "images/world/dirt.png"),
    PATH(32, 32, "images/world/path_1.png");

    public final int width;
    public final int height;
    public final String filename;

    TileTexture(int width, int height, String filename) {
        this.width = width;
        this.height = height;
        this.filename = filename;
    }
}
