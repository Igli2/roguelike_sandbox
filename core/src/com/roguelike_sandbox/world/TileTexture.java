package com.roguelike_sandbox.world;

public enum TileTexture {
    GROUND(32, 32, "ground.png"),
    ROCKS(32, 32, "rocks.png"),
    SAND(32, 32, "sand.png"),
    LAKE(32, 32, "lake.png");

    public final int width;
    public final int height;
    public final String filename;

    TileTexture(int width, int height, String filename) {
        this.width = width;
        this.height = height;
        this.filename = filename;
    }
}
