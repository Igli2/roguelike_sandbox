package com.roguelike_sandbox.world;

public enum TileTexture {
    DIRT(0, 0, 32, 32),
    LAVA(32, 0, 32, 32),
    LAVA_NESW(64, 0, 32, 32),
    LAVA_ES(96, 0, 32, 32),
    LAVA_SW(128, 0, 32, 32),
    LAVA_NE(160, 0, 32, 32),
    LAVA_NW(192, 0, 32, 32);

    public final int x;
    public final int y;
    public final int width;
    public final int height;

    TileTexture(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
